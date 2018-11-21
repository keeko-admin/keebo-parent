package com.keeko.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeChatUtil {
    //URL验证时使用的token
    public static final String TOKEN = "keeko";
    //appid
    public static final String APPID = "wx24d5adf7c43af7e9";
    //secret
    public static final String SECRET = "6f428f071208519097f8e5ddfaf27db7";
    //创建菜单接口地址
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //获取access_token的接口地址
    public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //缓存的access_token
    private static String accessToken;
    //access_token的失效时间
    private static long expiresTime;

    /**
     * 获取accessToken
     */
    public static String getAccessToken() {
        //判断accessToken是否已经过期，如果过期需要重新获取
        if (accessToken == null || expiresTime < System.currentTimeMillis()) {
            //发起请求获取accessToken
            String result = HttpUtil.get(GET_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", SECRET));
            //把json字符串转换为json对象
            JSONObject json = JSON.parseObject(result);
            //缓存accessToken
            accessToken = json.getString("access_token");
            //设置accessToken的失效时间
            long expires_in = json.getLong("expires_in");
            //失效时间 = 当前时间 + 有效期(提前一分钟)
            expiresTime = System.currentTimeMillis() + (expires_in - 60) * 1000;
        }
        return accessToken;
    }

    /**
     * 创建菜单
     */
    public static void createMenu(String menuJson) {
        //发起请求到指定的接口 并且带上菜单json数据
        String result = HttpUtil.post(CREATE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken()), menuJson);
        System.out.println(result);

    }

    public static void main(String[] args) {
        createMenu(" {\n" +
                "     \"button\":[\n" +
                "     {    \n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"搜索\",\n" +
                "               \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "                 \"type\":\"miniprogram\",\n" +
                "                 \"name\":\"wxa\",\n" +
                "                 \"url\":\"http://mp.weixin.qq.com\",\n" +
                "                 \"appid\":\"wx286b93c14bbf93aa\",\n" +
                "                 \"pagepath\":\"pages/lunar/index\"\n" +
                "             },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }");
    }

}
