package com.kcq.coolweather.uitls;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kcq on 2020/5/19
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void writeToFile(String filePath, String writeContent) {
        Log.d(TAG, "writeToFile:" + filePath);

        try {
            FileOutputStream fs = new FileOutputStream(filePath);
            fs.write(writeContent.getBytes());
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
