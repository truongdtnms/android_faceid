package vn.com.nms.api;

import android.content.Context;

import vn.com.nms.DialogLoading;
import vn.com.nms.tool.JsonForm;
import vn.com.nms.service.RequestAsyncTask;
import vn.com.nms.tool.StringImage;

public class APITraCuu {
    private byte[] imageData;
    private String data = null;
    private String valueMonth = "";
    private String valueId = "";
    private final String KEY_IMAGE = "image";
    public static final String API = "tracuu";
    private final String KEY_MONTH = "month";
    private final String KEY_ID = "id";
    private Context context;
    private final String URL = "http://ec2-3-18-221-136.us-east-2.compute.amazonaws.com:5000/tracuu";
    private DialogLoading dialogLoading;
    public APITraCuu(Context context, byte[] imageData, String month, String id, DialogLoading dialogLoading) {
        this.context = context;
        this.valueMonth = month;
        this.valueId = id;
        this.imageData = imageData;
        this.dialogLoading = dialogLoading;
    }

    public void traCuu(){
        String stringImage = StringImage.convertBytesToString(this.imageData);
        JsonForm form = new JsonForm();
        form.setValue(KEY_IMAGE, stringImage);
        form.setValue(KEY_MONTH, this.valueMonth);
        form.setValue(KEY_ID, this.valueId);
        data = form.getJsonString();
        RequestAsyncTask asyncTask = new RequestAsyncTask(this.context);
        asyncTask.setDialogLoading(this.dialogLoading);
        asyncTask.execute(new String[]{URL, data, API});
    }
}
