package com.popi.morseencoder;

import java.util.HashMap;
import java.util.Map;

class MorseDictionary {

    private static MorseDictionary instance;
    private Map<Character, String> mMorseDictionary;

    private MorseDictionary(){
        mMorseDictionary = new HashMap<>();
        mMorseDictionary.put('a', ".-");
        mMorseDictionary.put('b', "-...");
        mMorseDictionary.put('c', "-.-.");
        mMorseDictionary.put('d', "-..");
        mMorseDictionary.put('e', ".");
        mMorseDictionary.put('f', ".-..");
        mMorseDictionary.put('g', "--.");
        mMorseDictionary.put('h', "....");
        mMorseDictionary.put('i', "..");
        mMorseDictionary.put('j', ".---");
        mMorseDictionary.put('k', "-.-");
        mMorseDictionary.put('l', "..-.");
        mMorseDictionary.put('m', "--");
        mMorseDictionary.put('n', "-.");
        mMorseDictionary.put('o', "---");
        mMorseDictionary.put('p', ".--.");
        mMorseDictionary.put('q', "--.-");
        mMorseDictionary.put('r', ".-.");
        mMorseDictionary.put('s', "...");
        mMorseDictionary.put('t', "-");
        mMorseDictionary.put('u', "..-");
        mMorseDictionary.put('v', "...-");
        mMorseDictionary.put('w', ".--");
        mMorseDictionary.put('x', "-..-");
        mMorseDictionary.put('y', "-.--");
        mMorseDictionary.put('z', "--..");
        mMorseDictionary.put(' ', "/");
    }

    static String getValue(char c){
        if (instance == null)
            instance = new MorseDictionary();
        return instance.mMorseDictionary.get(c);
    }
}
