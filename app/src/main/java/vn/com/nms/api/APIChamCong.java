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
    private final String URL = "http://ec2-3-18-221-136.us-east-2.compute.amazonaws.com:5000/chamcong";

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
        RequestAsyncTask asyncTask = new RequestAsyncTask(context);
        asyncTask.setDialogLoading(this.dialogLoading);
        asyncTask.execute(new String[]{URL, data, API});
    }
}
