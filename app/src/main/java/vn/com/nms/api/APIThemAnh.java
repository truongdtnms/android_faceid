package vn.com.nms.api;

import android.content.Context;
import android.util.Log;

import vn.com.nms.CameraActivity;
import vn.com.nms.DialogLoading;
import vn.com.nms.tool.JsonForm;
import vn.com.nms.service.RequestAsyncTask;
import vn.com.nms.tool.StringImage;

public class APIThemAnh {
    private byte[] imageData;
    private String data;
    private final String KEY_IMAGE = "image";
    private final String KEY_ID_EMPLOYEE = "id_employee";
    public static final String API = "them_anh";
    private final String URL = "http://192.168.88.43:5000/themanh";
    private String idEmployee;

    private DialogLoading dialogLoading;
    private Context context;
    public APIThemAnh(Context context, byte[] imageData, String idEmployee, DialogLoading dialogLoading) {
        this.idEmployee = idEmployee;
        this.dialogLoading = dialogLoading;
        this.imageData = imageData;
        this.context = context;
    }

    public void themAnh(){
        String stringImage = StringImage.convertBytesToString(this.imageData);
        JsonForm form = new JsonForm();
        form.setValue(KEY_IMAGE, stringImage);
        form.setValue(KEY_ID_EMPLOYEE, this.idEmployee);
        data = form.getJsonString();
        RequestAsyncTask asyncTask = new RequestAsyncTask(context);
        asyncTask.setDialogLoading(this.dialogLoading);
        asyncTask.execute(new String[]{URL, data, API});
    }
}
