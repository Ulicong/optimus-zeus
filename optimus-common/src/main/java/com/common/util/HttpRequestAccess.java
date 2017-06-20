package com.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li.huan
 * Create Date 2017-05-12 16:26
 */
public class HttpRequestAccess {

    private static Logger log = LoggerFactory.getLogger(HttpRequestAccess.class);

    private static int API_TIMEOUT = 30000;

    private static DecimalFormat nf = new DecimalFormat("0.#");

    public static void main(String[] args) {

        Double d1 = 31423.33124343d;
        Double d2 = 191800000008d;

        System.out.println(d1.doubleValue());
        System.out.println(nf.format(d2));

        System.out.println(nf.format(1234));
        System.out.println(nf.format(1234.333));
        System.out.println(nf.format(1234.333));
    }

    /**
     * get 请求
     *
     * @param url     地址
     * @param params  参数集
     * @param charset 字符编码
     * @return
     */
    public static String httpGetAccessJDK(String url, Map<String, String> params, String charset) {
        StringBuilder paramSb = new StringBuilder();
        try {
            int i = 0, len = params != null ? params.size() : 0;
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    paramSb.append(URLEncoder.encode(entry.getKey(), charset)).append("=").append(URLEncoder.encode(entry.getValue(), charset));
                    if (i != len - 1) {
                        paramSb.append("&");
                    }
                    i++;
                }
            }

            URL u = new URL(url + "?" + paramSb.toString());
            InputStream is = u.openConnection().getInputStream();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buf = new byte[is.available()];
            int length = -1;
            while ((length = is.read(buf)) != -1) {
                bos.write(buf, 0, length);
            }

            if (charset != null) {
                return new String(bos.toByteArray(), charset);
            } else {
                return new String(bos.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    /**
     * post 请求
     *
     * @param url     地址
     * @param params  参数集
     * @param charset 字符编码
     * @return
     */
    public static String httpPostAccessJDK(String url, Map<String, String> params, String charset) {
        StringBuilder paramSb = new StringBuilder();
        try {
            int i = 0, len = params != null ? params.size() : 0;
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    paramSb.append(URLEncoder.encode(entry.getKey(), charset)).append("=").append(URLEncoder.encode(entry.getValue(), charset));
                    if (i != len - 1) {
                        paramSb.append("&");
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

        return httpPostAccessJDK(url, paramSb.toString(), charset, "POST");
    }


    /**
     * post 请求
     *
     * @param url     地址
     * @param data    请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset 字符编码
     * @return
     */
    public static String httpPostAccessJDK(String url, String data, String charset, String method) {
        StringBuilder content = new StringBuilder();
        try {
            HttpURLConnection conn = null;

            URL getUrl = new URL(url);
            conn = (HttpURLConnection) getUrl.openConnection();
            conn.setConnectTimeout(API_TIMEOUT);
            conn.setReadTimeout(API_TIMEOUT);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);

            byte[] bdata = data.getBytes(charset);
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(bdata);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    public static String httpPostAccess(String url, Map<String, String> params, String charset) {
        String realUrl = url;
        StringBuffer result = new StringBuffer();
        try {
            HttpPost http = new HttpPost(realUrl);
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, charset);
                http.setEntity(formEntity);
            }

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(http);

            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));

            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                result.append(tmp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    public static String httpPostAccess(String url) {
        String realUrl = url;
        Map<String, String> paramMap = new HashMap<String, String>();
        int index = url.indexOf("?");
        if (index != -1) {
            realUrl = url.substring(0, index);
            String paramStr = url.substring(index + 1);
            paramStr = paramStr.replaceAll("&amp;", "&");
            String[] strs = paramStr.split("&");

            for (String string : strs) {
                String[] ss = string.split("=");
                if (ss.length != 2) {
                    continue;
                }
                paramMap.put(ss[0].trim(), ss[1].trim());
            }
        }

        StringBuffer result = new StringBuffer();
        try {

            HttpPost http = new HttpPost(realUrl);
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            if (paramMap.size() > 0) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, "utf-8");
                http.setEntity(formEntity);
            }

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(http);

            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));

            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                result.append(tmp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    /**
     * 简单http请求，适用于以文本形式返回的响应
     *
     * @param url
     * @return
     */
    public static String httpGetAccess(String url) {
        StringBuilder str = new StringBuilder();
        try {

            HttpGet http = new HttpGet(url);

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(http);

            List<String> ss = httpclient.getCookieSpecs().getSpecNames();

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                if (tmp != null) {
                    str.append(tmp);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return str.toString();
    }

    /**
     * 简单http请求，适用于以文本形式返回的响应
     *
     * @param url
     * @return
     */
    public static String httpGetAccessJDK(String url, String charset) {
        try {
            URL u = new URL(url);
            InputStream is = u.openConnection().getInputStream();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buf = new byte[is.available()];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }

            if (charset != null) {
                return new String(bos.toByteArray(), charset);
            } else {
                return new String(bos.toByteArray());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 访问url ，返回cookie信息 , get形式访问
     *
     * @return
     */
    public static List<javax.servlet.http.Cookie> getCookies(String url) {
        try {
            List<javax.servlet.http.Cookie> result = new ArrayList<javax.servlet.http.Cookie>();
            HttpGet http = new HttpGet(url);

            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.execute(http);
            List<Cookie> cs = httpclient.getCookieStore().getCookies();
            for (Cookie c : cs) {
                javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(c.getName(), c.getValue());
                result.add(cookie);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Post请求
     *
     * @param url 请求地址
     * @param data
     * @return
     */
    public static String httpPostAccess(String url, String data) {
        StringBuilder result = new StringBuilder();
        try {

            HttpPost http = new HttpPost(url);
            http.setHeader("Content-Type", "application/json; charset=utf-8");
            http.removeHeaders("Host");
            http.removeHeaders("User-Agent");
            if (data != null && !data.matches("\\s*")) {
                HttpEntity entity = new StringEntity(data, "utf-8");
                http.setEntity(entity);
            }

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(http);

            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));

            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                result.append(tmp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result.toString();

    }
}
