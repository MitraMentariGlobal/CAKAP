package co.id.cakap.utils;

import org.json.JSONObject;

import okhttp3.ResponseBody;

public class Utils {
    public static String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("ErrorMessage");
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return "Authentication failed.";
        }
    }
}
