package vn.com.nms.tool;

import android.util.Base64;

public class StringImage {
    public static String convertBytesToString(byte[] data){
        if (data == null)
            return "";
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

}
