package org.d3ifcool.finpro.core.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;

public class FileHelper {

    private Context context;
    private Activity activity;

    public FileHelper(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public MultipartBody.Part getFile(Uri docUri){
        File file = new File(docUri.getPath());
        String mimeType = context.getContentResolver().getType(docUri);
        Log.e("TAG", "onActivityResult: "+mimeType+": "+file.getName());
        File outputDir = context.getCacheDir(); // context being the Activity pointer
        File outputFile = null;
        try {
            outputFile = File.createTempFile("form","xlsx", outputDir);
            File fileCopy = copyToTempFile(docUri, outputFile);
            RequestBody requestBody = RequestBody.create(MediaType.parse(mimeType),fileCopy);
            String fileName = "form_plotting_pembimbing."+mimeType;
            MultipartBody.Part part = MultipartBody.Part.createFormData("file",fileName,requestBody);
            return part;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File copyToTempFile(Uri uri, File tempFile) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        if (inputStream == null) {
            throw new IOException("Unable to obtain input stream from URI");
        }
        FileUtils.copyInputStreamToFile(inputStream, tempFile);
        return tempFile;
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String filename) {
        try {
            // todo change the file location/name according to your needs
//            File form = new File(Environment.getExternalStorageDirectory() +File.separator + "Download" + File.separator + filename);
            File form = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + filename);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[2048000];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(form);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                Log.e("TAG", "Error: "+e.getLocalizedMessage());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public void openFile(ResponseBody body, String filename){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + filename);
        if (!file.exists()){
            boolean writtenToDisk = writeResponseBodyToDisk(body,filename);
            if (writtenToDisk)
                Log.e("TAG", "onGetBody: Hasil Download");
        }
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(FileProvider.getUriForFile(activity,context.getPackageName()+".fileprovider",file),FILE_TYPE_XLS);
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        activity.startActivity(Intent.createChooser(target,"Open File"));
    }

    public boolean permissionCheck(){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                return true;
            }
        }
        return false;
    }
    
}
