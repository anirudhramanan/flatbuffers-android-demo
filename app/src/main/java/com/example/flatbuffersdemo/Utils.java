package com.example.flatbuffersdemo;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Utils {

    static String readJSONFile(Context context) {
        InputStream raw = context.getResources().openRawResource(R.raw.event_json);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = raw.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }

    static byte[] readFlatBuffFile(Context context) {
        InputStream raw = context.getResources().openRawResource(R.raw.event_flat);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = raw.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}