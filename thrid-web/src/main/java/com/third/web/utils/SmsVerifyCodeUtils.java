package com.third.web.utils;

import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;

import com.third.service.media.SMSService;
import com.third.service.user.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsVerifyCodeUtils {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SMSService smsService;

    private final static String VCODE = "vcode";

    public boolean verifyVcode(final String inputVCode)
    {
        String vcode = (String) sessionService.get(VCODE);
        System.out.println("vcode is " + vcode);
        return inputVCode.equals(vcode);
    }

    public void generateVCode(final String cellphone)
    {
        final String vcode = this.getRandomVerCode();
        System.out.println("generatevcode"+vcode);
        sessionService.save(VCODE, vcode);
        try
        {
            smsService.sendBindSMS(cellphone, vcode);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    String getRandomVerCode()
    {

        int max = 9999;
        int min = 1000;
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;

        return String.valueOf(s);
    }
}
