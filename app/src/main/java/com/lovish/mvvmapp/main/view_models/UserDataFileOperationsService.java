package com.lovish.mvvmapp.main.view_models;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataFileOperationsService extends IntentService {

    private static final String TAG = "UserDataFileOpsService";
    private static final String FILE_NAME = "UserData.txt";

    public UserDataFileOperationsService() {
        super("UserDataFileOperationsService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) return;
        int operationType = intent.getIntExtra("operationType", -1);

        switch (operationType) {
            case 1:
                Log.e(TAG, "data " + startReadOperations());
                break;
            case 2:
                //todo : Need data to be written on the file here
                startWriteOperation();
                break;
            case 3:
                //todo : Need data to be deleted from the file here
                startDeleteOperation();
                break;
        }
    }

    public String startReadOperations() {
        File root = Environment.getExternalStorageDirectory();
        File dataFile = new File(root, "lovish/" + FILE_NAME);

        StringBuilder stringBuilder = new StringBuilder();

        if (dataFile.exists()) {
            try {
                FileReader fileReader = new FileReader(dataFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    stringBuilder.append(data);
                    stringBuilder.append("\n");
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (stringBuilder.toString().isEmpty()) {
                return "";
            } else {
                return stringBuilder.toString();
            }

        } else {
            Log.e(TAG, "File doesn't exist");
            return "";
        }
    }

    public void startWriteOperation() {
        File root = new File(Environment.getExternalStorageDirectory(), "lovish");
        if (!root.exists()) root.mkdir();

        try {
            File dataFile = new File(root, FILE_NAME);
            FileWriter fileWriter = new FileWriter(dataFile);
            fileWriter.write("data");
            fileWriter.flush();
            fileWriter.close();
            Log.e(TAG, "Write operation succeeded");

        } catch (IOException e) {
            e.printStackTrace();
        }
        stopSelf();
    }

    public void startDeleteOperation() {

    }
}
