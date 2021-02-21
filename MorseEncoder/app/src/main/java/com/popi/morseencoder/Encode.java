package com.popi.morseencoder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Encode extends AppCompatActivity {
    private CameraManager mCameraManager;
    private String mCameraId;
    private TextView inputEditText;
    private AlertDialog mAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            showNoFlashError();
        }
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        mAlert = new AlertDialog.Builder(this)
                .create();
        mAlert.setTitle("Sending message");
        mAlert.setMessage("Please wait for the message to be sent.");
        mAlert.setCancelable(false);
        inputEditText = findViewById(R.id.et_input);
    }
    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .create();
        alert.setTitle("Error!");
        alert.setMessage("Flash not available in this device...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    public void SendMessage(View view) {
        // show dialog to inform user
        mAlert.show();
        char[] inputText = inputEditText.getText().toString().toLowerCase().toCharArray();
        StringBuilder encodedText = new StringBuilder();

        // convert to standard morse code
        for (char c: inputText) {
            encodedText.append(MorseDictionary.getValue(c));
            if (c != ' ')
                encodedText.append(' ');
        }

        final char[] finalEncodedText = encodedText.toString().toCharArray();

        //delay to ensure alert pops up
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // output via flashlight
                for (char c: finalEncodedText) {
                    if (c == '.')
                        Dit();
                    if (c == '-')
                        Dat();
                    try {
                        if (c == ' ')
                            Thread.sleep(500);
                        if (c == '/')
                            Thread.sleep(1000);

                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // dismiss dialog after message is sent
                mAlert.dismiss();
            }
        }, 100);
    }

    public void switchFlashLight(boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void Dit() {
        switchFlashLight(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switchFlashLight(false);
    }

    public void Dat() {
        switchFlashLight(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switchFlashLight(false);
    }
}
