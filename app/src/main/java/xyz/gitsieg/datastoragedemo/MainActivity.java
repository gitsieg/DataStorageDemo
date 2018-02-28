package xyz.gitsieg.datastoragedemo;

import android.content.Context;
import android.os.Environment;
import android.sax.Element;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String filesDirName = "permanent-storage-test.txt", cacheDirName = "temp-storage-test.txt";

    FileOutputStream outputStream; // Writing to files
    BufferedReader bufferedReader; // Reading from files with FileReader
    File mInternalFile, mCachedFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create file
        mInternalFile = new File(this.getFilesDir(), filesDirName);
        ArrayList<String> fileList = new ArrayList<>(Arrays.asList(fileList()));
        System.out.println("Local app files:");
        for (String s : fileList) {
            System.out.println(s);
        }

        try {
            writeExternalStorage("testFile", "This is the content");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeInternalStorage(String fileName, String fileContent) {
        File file;
        FileOutputStream outputStream = null;
        file = new File(getFilesDir(), fileName);
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    void writeExternalStorage(String fileName, String content) throws FileNotFoundException {
        // If external external storage is not writable.
        if (!isExternalStorageWritable()) {
            throw new FileNotFoundException("Not possible to write to external storage");
        }
        // Pointer to directory
        File path = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), fileName);
        // Pointer to new file
        File file = new File(path, fileName+".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileOutputStream(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readExternalStorageFile(String fileName) {

        return null;
    }
}
