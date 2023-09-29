package com.example.lab_124_counting_android;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CheckWord {
    private Set<String> commonWords;

    public CheckWord(AssetManager assetManager) throws IOException {
        commonWords = new HashSet<>();
        InputStream inputStream = assetManager.open("commonWords.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while ((str = bufferedReader.readLine()) != null){
            commonWords.add(str.toLowerCase());
        }

        System.out.printf(commonWords.toString());
    }

    public boolean isCommon(String str){
        return commonWords.contains(str);
    }
}
