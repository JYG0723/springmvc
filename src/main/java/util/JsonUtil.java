package util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author Nuc YongGuang Ji.
 * @Date 23:25 2017/9/3.
 * @Descrip
 */
public class JsonUtil {

    public static final int REQUEST_SUCCESS = 1;
    public static final int REQUEST_Fail = 999;

    /**
     * 成功
     *
     * @param code 状态码
     * @return
     */
    public static String getJsonString(int code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        return jsonObject.toJSONString();
    }

    /**
     * 成功
     *
     * @param code 状态码
     * @param map  请求内容
     * @return
     */
    public static String getJsonString(int code, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        return jsonObject.toJSONString();
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  错误信息
     * @return
     */
    public static String getJsonString(int code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }
}
