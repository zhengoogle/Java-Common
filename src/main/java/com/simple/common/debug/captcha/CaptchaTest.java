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
    // ���������ֱ�Ϊ���ߡ�λ��
    SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
    // ��������
    specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // ��Ĭ�����壬���Բ�������
    // �������ͣ������֡�����ĸ����ĸ���ֻ��
    specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

    // ��֤�����session
    // specCaptcha.text().toLowerCase()
    System.out.print(specCaptcha.text().toLowerCase());

    // ���ͼƬ��
    // specCaptcha.out(response.getOutputStream());
  }
}
