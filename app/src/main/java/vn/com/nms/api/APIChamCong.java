package vn.com.nms.api;

import android.content.Context;
import android.util.Log;

import vn.com.nms.CameraActivity;
import vn.com.nms.DialogLoading;
import vn.com.nms.tool.JsonForm;
import vn.com.nms.service.RequestAsyncTask;
import vn.com.nms.tool.StringImage;

public class APIChamCong {
    private byte[] imageData;
    private String data;
    private final String KEY_IMAGE = "image";
    public static final String API = "cham_cong";
    private final String URL = "http://192.168.88.219:5000/faceid/api/v1.0/add_emp_log";

    private DialogLoading dialogLoading;
    private Context context;
    public APIChamCong(Context context, byte[] imageData, DialogLoading dialogLoading) {
        this.dialogLoading = dialogLoading;
        this.imageData = imageData;
        this.context = context;
    }

    public void chamCong(){
        String stringImage = StringImage.convertBytesToString(this.imageData);
        JsonForm form = new JsonForm();
        form.setValue(KEY_IMAGE, stringImage);
        data = form.getJsonString();
        Log.d("---------------", data);
        RequestAsyncTask asyncTask = new RequestAsyncTask(context);
        asyncTask.setDialogLoading(this.dialogLoading);
        asyncTask.execute(new String[]{URL, data, API});
    }
}
