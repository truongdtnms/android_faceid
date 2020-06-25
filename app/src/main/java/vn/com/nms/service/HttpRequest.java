package vn.com.nms.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpRequest {
    private final String TAG = "CLASS_HTTPREQUEST";
    private final String METHOD = "POST";
    private URL mUrl;
    private HttpURLConnection mConnection;
    private JSONObject data;
    private DataOutputStream dos;
    private JSONObject response;

    public HttpRequest(URL mUrl, JSONObject jsonObject) {
        this.mUrl = mUrl;
        this.data = jsonObject;
    }

    public void setmUrl(URL mUrl) {
        this.mUrl = mUrl;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public boolean openConnection() {
        try {
            mConnection = (HttpURLConnection) mUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setupConnection(String method) {
        if (mConnection == null) {
            Log.e(TAG, "setup connection fail: HttpURLConnection is null");
            return;
        }
        try {
            mConnection.setRequestMethod(method);
            mConnection.setRequestProperty("Content-Type", "application/json");
            mConnection.setRequestProperty("Accept", "application/json");
            mConnection.setDoInput(true);
            mConnection.setDoOutput(true);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        try {
            mConnection.connect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        if (mConnection != null) {
            mConnection.disconnect();
        }
    }

    public JSONObject getResponse() {
        openConnection();
        setupConnection(METHOD);
        boolean connected =  connect();
        if (!connected){
            return null;
        }
        try {
            dos = new DataOutputStream(mConnection.getOutputStream());
            dos.writeBytes(data.toString());
            dos.flush();
            dos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            String content = sb.toString();
            response = new JSONObject(content);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnect();
        }
    }
}
