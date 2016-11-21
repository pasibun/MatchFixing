package com.matchfixing.minor.matchfixing;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LaptopPasibun on 10-11-2016.
 */

public class KnltbLogin {
    String data = "studKnNr=login&asmKodas=password";
    String loginUrl = "https://www.mijnknltb.nl/CookieAuth.dll?GetLogon?curl=Z2F&reason=0&formdir=12";
    String someUrl;
    String Login = POST_req(loginUrl, data, 10000); /*last parameter is a limit of page content length*/

    //And adter succcess login you can send second request:
    String pageContent = POST_req(someUrl, "", 10000);


    //Methods for sending requests and saving cookie:
//(this no needs for changing, can only past to you project)
    public String POST_req(String url, String post_data, int len) {
        URL addr = null;
        try {
            addr = new URL(url);
        } catch (MalformedURLException e) {
            return "Некорректный URL";
        }
        StringBuffer data = new StringBuffer();
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) addr.openConnection();
        } catch (IOException e) {
            return "Open connection error";
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Accept-Language", "ru,en-GB;q=0.8,en;q=0.6");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Cookie", "");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //conn.setInstanceFollowRedirects(true);
        set_cookie(conn);

        //POST data:
        String post_str = post_data;
        data.append(post_str);
        try {
            conn.connect();
        } catch (IOException e) {
            return "Connecting error";
        }
        DataOutputStream dataOS = null;
        try {
            dataOS = new DataOutputStream(conn.getOutputStream());
        } catch (IOException e2) {
            return "Out stream error";
        }
        try {
            ((DataOutputStream) dataOS).writeBytes(data.toString());
        } catch (IOException e) {
            return "Out stream error 1";
        }

        /*If redirect: */
        int status;
        try {
            status = conn.getResponseCode();
        } catch (IOException e2) {
            return "Response error";
        }
        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM ||
                status == HttpURLConnection.HTTP_SEE_OTHER) {
            String new_url = conn.getHeaderField("Location");
            String cookies = conn.getHeaderField("Set-Cookie");
            URL red_url;
            try {
                red_url = new URL(new_url);
            } catch (MalformedURLException e) {
                return "Redirect error";
            }
            try {
                conn = (HttpURLConnection) red_url.openConnection();
            } catch (IOException e) {
                return "Redirect connection error";
            }
            //conn.setRequestProperty("Content-type", "text/html");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Accept-Language", "ru,en-GB;q=0.8,en;q=0.6");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Cookie", cookies);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //conn.setInstanceFollowRedirects(true);
        }

        java.io.InputStream in = null;
        try {
            in = (java.io.InputStream) conn.getInputStream();
        } catch (IOException e) {
            return "In stream error";
        }
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "In stream error";
        }
        char[] buf = new char[len];
        try {
            reader.read(buf);
        } catch (IOException e) {
            return "In stream error";
        }
        get_cookie(conn);

        return (new String(buf));
    }
    public void get_cookie(HttpURLConnection conn) {
        SharedPreferences sh_pref_cookie = getSharedPreferences("cookies", Context.MODE_PRIVATE);
        String cook_new;
        String COOKIES_HEADER;
        if (conn.getHeaderField("Set-Cookie") != null) {
            COOKIES_HEADER = "Set-Cookie";
        }
        else {
            COOKIES_HEADER = "Cookie";
        }
        cook_new = conn.getHeaderField(COOKIES_HEADER);
        if (cook_new.indexOf("sid", 0) >= 0) {
            SharedPreferences.Editor editor = sh_pref_cookie.edit();
            editor.putString("Cookie", cook_new);
            editor.commit();
        }
    }
    public void set_cookie(HttpURLConnection conn) {
        SharedPreferences sh_pref_cookie = getSharedPreferences("cookies", Context.MODE_PRIVATE);
        String COOKIES_HEADER = "Cookie";
        String cook = sh_pref_cookie.getString(COOKIES_HEADER, "no_cookie");
        if (!cook.equals("no_cookie")) {
            conn.setRequestProperty(COOKIES_HEADER, cook);
        }
    }

    private SharedPreferences getSharedPreferences(String cookies, int modePrivate) {
        return null;
    }
}


