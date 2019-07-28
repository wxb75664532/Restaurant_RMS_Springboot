package com.yzc.shixun.tool.face;



import com.yzc.shixun.tool.Auth;
import com.yzc.shixun.tool.GsonUtils;
import com.yzc.shixun.tool.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class FaceSearch {

    public static String FaceSearch(String image)
    {
        String url="https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image); //图片base64数据
            map.put("liveness_control", "NORMAL");  //活体检测控制一般的
            map.put("group_id_list", "Face_Login");  //指定用户组group 人脸库总已经存在的用户组
            map.put("image_type", "BASE64");     //图片类型，这里转化过的base64
            map.put("quality_control", "LOW");   //图片质量控制

            String param = GsonUtils.toJson(map);
            Auth auth = new Auth();
            String accessToken = auth.getAuth();
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            String results = HttpUtil.post(url, accessToken, "application/json", param);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
