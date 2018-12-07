package com.simple.common.utils;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    public static final MediaType CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static Logger logger = LogManager.getLogger(HttpUtils.class.getName() + ".class");
    private static OkHttpClient client;


    public static OkHttpClient getClientInstance() {
        synchronized (HttpUtils.class) {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .build();
                logger = LogManager.getLogger(HttpUtils.class.getName() + ".class");
            }
            return client;
        }
    }

    public static String getSync(String url) {
        return syncRequest(url, null, "GET");
    }

    public static void getAsync(String url, final HttpCallback callback) {
        asyncRequest(url, null, "GET", callback);
    }

    public static String postSync(String url, String body) {
        return syncRequest(url, body, "POST");
    }

    public static void postAsync(String url, String body, final HttpCallback callback) {
        asyncRequest(url, body, "POST", callback);
    }

    /**
     * 同步提交数据
     *
     * @param url
     * @param json
     * @return
     */
    public static String syncRequest(String url, String json, String method) {
        logger.info(url);
        String result = null;
        try {
            Request request = getRequest(url, method, json);
            if (client == null) {
                client = getClientInstance();
            }
            Response response1 = client.newCall(request).execute();
            ResponseBody responseBody = response1.body();
            if (responseBody != null) {
                result = responseBody.string();
                logger.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 异步提交数据
     *
     * @param url
     * @param json
     * @param callback
     */
    public static void asyncRequest(String url, String json, String method, final HttpCallback callback) {
        logger.info(url);
        Request request;
        try {
            request = getRequest(url, method, json);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }

        if (client == null) {
            client = getClientInstance();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callback.onFail("failed");
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            callback.onSuccess(response.body().string());
                            return;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.info(e.getMessage());
                    }
                    callback.onFail("error");
                }
            }
        });
    }

    private static Request getRequest(String url, String method, String json) {
        Request request;
        if (method == null || method.equals("GET")) {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } else {
            RequestBody body = RequestBody.create(CONTENT_TYPE, json);
            request = new Request.Builder()
                    .url(url)
                    .method(method, body)
                    .build();
        }
        return request;
    }

    public interface HttpCallback {
        void onSuccess(String result);

        void onFail(String msg);
    }
}
