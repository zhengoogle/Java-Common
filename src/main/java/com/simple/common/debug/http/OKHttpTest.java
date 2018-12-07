package com.simple.common.debug.http;

import com.simple.common.Main;
import com.simple.common.utils.HttpUtils;

/**
 * OKHttp utils test
 */
public class OKHttpTest {
    public static void testOkHttp(){
        String getUrl = "https://postman-echo.com/get?test=123";
        String getRes = HttpUtils.getSync(getUrl);
        Main.logger.info(getRes);
        String postUrl = "https://postman-echo.com/post";
        String body = "{\n\"args\": {\n\"test\": \"123\"\n}\n}";
        HttpUtils.postAsync(postUrl, body, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                Main.logger.info(result);
            }

            @Override
            public void onFail(String msg) {
                Main.logger.error(msg);
            }
        });
    }
}
