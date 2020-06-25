package vn.com.nms.tool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonForm {
    private ArrayList<String> keys;
    private ArrayList<String> values;
    private Map<String, String> map;
    public JsonForm() {
        map = new HashMap<>();
    }
    public void setValue(String key, String value){
        map.put(key, value);
    }
    public String getJsonString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            sb.append("\"");
            sb.append(key);
            sb.append("\"");
            sb.append(":");
            sb.append("\"");
            sb.append(map.get(key));
            sb.append("\"");
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("}");
        return sb.toString();
    }
}
