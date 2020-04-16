/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.dbutils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hung.user.UserInfo;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author anhndSE130595
 */
public class APIWrapper {
    public static String FACEBOOK_APP_ID = "217661089295170";
    public static String FACEBOOK_APP_SECRET = "3505e8713029d2075f25e71f01519601";
    public static String FACEBOOK_REDIRECT_URL = "http://localhost:8080/Booking_JSP_Servlet/FacebookServlet";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
    private String accessToken;
    private Gson gson;

    public APIWrapper() {
        gson = new Gson();
    }

    public static String getDialogLink() {
        String dialogLink = "https://www.facebook.com/dialog/oauth?client_id=%s&redirect_uri=%s";
        return String.format(dialogLink, FACEBOOK_APP_ID, FACEBOOK_REDIRECT_URL);
    }
//    public String getAccessToken(String code){
//        String accessTokenLink = "https://graph.facebook.com/oauth/access_token?"
//                + "client_id=%s"
//                + "&client_secret=%s"
//                + "&redirect_url=%s"
//                + "&code=%s";
//        accessTokenLink = String.format(accessTokenLink, appID,appSerect,redirectUrl,code);
//        String result = NetUtils.GetResult(accessTokenLink);
//        String token = result.substring(result.indexOf("=") + 1, result.indexOf("&"));
//        return token;
//    }

    public String getAccessToken(String code) throws IOException {
        String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        this.accessToken = accessToken;
        return accessToken;
    }

    public UserInfo getUserInfo() {
        String infoUrl = "https://graph.facebook.com/me?access_token=%s";
        infoUrl = String.format(infoUrl, this.accessToken);
        String result = NetUtils.GetResult(infoUrl);
        UserInfo userInfo = gson.fromJson(result, UserInfo.class);
        return userInfo;
    }

}
