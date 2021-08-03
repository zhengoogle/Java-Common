package com.simple.common.debug.captcha;

import com.simple.common.Main;
import com.simple.common.utils.HttpUtils;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;

import java.awt.*;

/**
 * OKHttp utils test
 */
public class CaptchaTest {
  public static void captcha() {
    // 三个参数分别为宽、高、位数
    SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
    // 设置字体
    specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
    // 设置类型，纯数字、纯字母、字母数字混合
    specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

    // 验证码存入session
    // specCaptcha.text().toLowerCase()
    System.out.print(specCaptcha.text().toLowerCase());

    // 输出图片流
    // specCaptcha.out(response.getOutputStream());
  }
}
