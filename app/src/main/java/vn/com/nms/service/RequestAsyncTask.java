package vn.com.nms.service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import vn.com.nms.CameraActivity;
import vn.com.nms.DialogError;
import vn.com.nms.DialogLoading;
import vn.com.nms.TraCuuActivity;
import vn.com.nms.api.APIChamCong;
import vn.com.nms.api.APIThemAnh;
import vn.com.nms.api.APITraCuu;
import vn.com.nms.service.HttpRequest;

public class RequestAsyncTask extends AsyncTask<String, JSONObject, JSONObject> {
    private String typeApi;
    private DialogLoading dialogLoading;
    private Context context;

    public RequestAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String url = strings[0];
        String jsonString = strings[1];
        typeApi = strings[2];
        JSONObject response = null;
        try {
            HttpRequest httpRequest = new HttpRequest(new URL(url), new JSONObject(jsonString));
            response = httpRequest.getResponse();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        if (jsonObject == null) {
            if (this.dialogLoading != null) {
                this.dialogLoading.cancel();
            }
            Toast.makeText(context, "Không kết nối được server", Toast.LENGTH_LONG).show();
            return;
        }
        if (this.dialogLoading != null) {
            dialogLoading.cancel();
        }
        if (typeApi == APIChamCong.API){
            processChamCong();
        } else if (typeApi == APITraCuu.API){
            processTraCuu();
        } else if (typeApi == APIThemAnh.API){
            processThemAnh();
        }
    }

    public void setDialogLoading(DialogLoading dialogLoading) {
        this.dialogLoading = dialogLoading;
    }

    public void processChamCong(){

        if (true){
            DialogError dialogError = new DialogError(this.context);
            dialogError.show();
        }
    }
    public void processTraCuu(){
        if (true){
            Intent intent = new Intent(this.context, TraCuuActivity.class);
            this.context.startActivity(intent);
        }
    }
    public void processThemAnh(){
        Toast.makeText(context, "Thành công!", Toast.LENGTH_LONG).show();
    }
}
