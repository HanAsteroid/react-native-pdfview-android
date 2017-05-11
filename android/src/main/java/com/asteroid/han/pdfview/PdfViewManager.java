package com.asteroid.han.pdfview;

import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by guhaikuan on 2017/3/13.
 */

public class PdfViewManager extends ReactContextBaseJavaModule {
    private String fileName;
    public PdfViewManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "PdfView";
    }
    @ReactMethod
    public void getFilePath(){
        String path = "";
        if (isSdCardExist()){
            path = getSdCardPath();
        }
    }
    @ReactMethod
    public void getFileCallback(Callback success,Callback fail){
        String path = "";
        if (isSdCardExist()){
            path = getSdCardPath();
        }
        if (!TextUtils.isEmpty(path)){
            success.invoke(path);
        }else {
            fail.invoke(path);
        }
    }
    @ReactMethod
    public void showPdf(String url,String fileName){
        this.fileName = fileName;
        if (!TextUtils.isEmpty(url)){
            Intent intent = new Intent(getCurrentActivity(),PdfViewActivity.class);
            intent.putExtra("URL",url);
            intent.putExtra("FILENAME",fileName);
            getCurrentActivity().startActivity(intent);
        }
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("FilePath",getSdCardPath() + "/" + this.fileName);
        return constants;
    }

    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    private boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    private String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "";
        }
        return sdpath;

    }
}
