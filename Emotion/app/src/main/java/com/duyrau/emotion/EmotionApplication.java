package com.duyrau.emotion;

import android.app.Application;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyrau on 3/6/2016.
 */
public class EmotionApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            copyToSDCard();
        } catch (IOException e) {

        }

    }

    private List<String> getAllRawFilesName() {
        List<String> result = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for (int i = 1, len = fields.length; i < len; i++) {
            String fileName = fields[i].getName();
            result.add(fileName);
        }
        return result;
    }

    private void copyToSDCard() throws IOException {
        List<String> fileNames = getAllRawFilesName();

        for (String str : fileNames) {
            int resid = getResources().getIdentifier(str, "raw", this.getPackageName());
            InputStream in = getResources().openRawResource(resid);
            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/"
                    + str + ".mp3");
            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        }
    }
}
