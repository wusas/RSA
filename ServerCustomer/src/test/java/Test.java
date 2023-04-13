import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weroom.util.*;

import java.security.PrivateKey;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO
 *
 * @author 吴沙
 * @date 2023/4/12 19:32
 */
public class Test {

    public static final String clientPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALM9gyzoliA8R2da" +
            "9tqfh5c7V/Zcn5etZDNmeqv/1YROoe5O4Vo8N4lBSGP+IKSXsSfCm13v3ZDrxqUp" +
            "b6YCBnz17W3DR9TxUNQraB4y286fRPsl6N/OMB8EhhCTKuvHyjqgq8sIY5ymg9PV" +
            "sIetJglVp870hRRDYvJdRaUyf5DpAgMBAAECgYBz+QT69bLNQLNiqHS4mCf/LiKI" +
            "AP4yAbqnl1MgHvRkGwyjSN5+qB+260qPiEpOtOenNj+Y1C6kKinpi5n167GN60Y6" +
            "gM/vVHSUA/lv2v/fR4vDRj9CMYu2vNuKrWV3F3y577/qkuCIWaD+iXU9WsQTsuL3" +
            "UkP5iz3sVgD9K7kwAQJBAOPjKmmKEghwGaCXx05E0BF7UHT4b7BZhCPdCj115PAL" +
            "0xNqqKYyRhYGfndopcB16Jl7fJJLxjj+02V2YsfkW4ECQQDJWgofvt8Qjf4VLUnl" +
            "98rnQ6xF2UbXdSOm9JCILU7rtz92TVC7bWy4R0mqX+S5zFGR5Znnk7f2HAP+kDAn" +
            "3olpAkAkeQIbvB5gvVhMrJ4Yd8j5U0wwPGbbyafteX4fEhRfGvVgYSy21MsE0WtC" +
            "PzxwDi75CZuJaplCG/7HeWgb5/+BAkBtXnkNUVI83cPboOP5BCW0hK+4qRKvybL5" +
            "5vHy5hluM/VGvyxRAlkBp8c9wiStP2w3QW3dugE8r28EATIHFfuxAkAxV4hHGD6B" +
            "bHRiCtSMNOQ3XGOZGdlmR4PfPFabv+jfAyoECHe4QHdkBBqdNKTyVVQUpNqPsJ0E" +
            "sfBTplZlDxTu";

    public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzPYMs6JYgPEdnWvban4eXO1f2" +
            "XJ+XrWQzZnqr/9WETqHuTuFaPDeJQUhj/iCkl7Enwptd792Q68alKW+mAgZ89e1t" +
            "w0fU8VDUK2geMtvOn0T7JejfzjAfBIYQkyrrx8o6oKvLCGOcpoPT1bCHrSYJVafO" +
            "9IUUQ2LyXUWlMn+Q6QIDAQAB";

    public static final String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANG5Xmt9GeApu/na" +
            "xAcDol7LSArS0vIusyyVjA5U7vy5u1M2zgQa/EWipX/eP5agtRmWjOh2kER0mTAc" +
            "luc2GLGLXKKppekQMf4dcYX0d6zkOlz60B/AVBjEfJVwvI04RjPxLT/t/yQ7foAp" +
            "dWba+FXZ+U3N+Bk7auC0ZOmtzYpTAgMBAAECgYARxFJraL24b9Cs6guRTI2E2lPQ" +
            "Fuwn+CzVqhWjYS6d2l40PoBVeLPGcnDy1DEu4Y52DHsDdofiRL51hPaDv+F3gcO/" +
            "JhLaH1s9p6I6SV3fuCE8mVK2gRoyQsGJv/PuZePyOYlarTbkbpaOZroTyCIdbNe9" +
            "W1Xj1EbzXVWvVyBeAQJBAOqOxnuk5+tUCL4OmlYAKWnrb0Zev8y5m23xohoVMnvd" +
            "qY2+ImCDpyWtwzGrupxyHftRuq8MFlKzVvBaH6p34NECQQDk5W0QZs4jJOumN03x" +
            "dOGiGlSA1Pq0H94fxiG/hX5u1hdqRxUib5vRb35iXvO0SvEX9gs7i2eDJdk+1FlI" +
            "smHjAkBcB90U0mU0zmoHuE8SA0o8huXIJJD40LKNdst1lG+UbiqCtOFkIQPKIt19" +
            "dbXogFYHL9AhsopntHoeIB5gklyhAkEAvLaqhKs9qJv37MIL8NZ8cDllEhKF35um" +
            "dmvTxyM4agj4pRaVxh5eSP2zJUdDhZGD1E12VVFJtkauJa7NLbJkIQJAOtwDgkrf" +
            "WrhC8/hPusa51GBVfB/SkhQzxaCb+oFf0qSe25J4fJLdcX20vLLCvF65Es8BH1V9" +
            "NSf8MPqLW0UjrQ==";
    public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRuV5rfRngKbv52sQHA6Jey0gK" +
            "0tLyLrMslYwOVO78ubtTNs4EGvxFoqV/3j+WoLUZlozodpBEdJkwHJbnNhixi1yi" +
            "qaXpEDH+HXGF9Hes5Dpc+tAfwFQYxHyVcLyNOEYz8S0/7f8kO36AKXVm2vhV2flN" +
            "zfgZO2rgtGTprc2KUwIDAQAB";

    public static void main(String[] args) throws Exception {
        TreeMap<String, Object> params = Maps.newTreeMap();
        params.put("userid", "152255855");
        params.put("phone", "18965621420");

        client(params);

        server();
    }

    private final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRuV5rfRngKbv52sQHA6Jey0gK" +
            "0tLyLrMslYwOVO78ubtTNs4EGvxFoqV/3j+WoLUZlozodpBEdJkwHJbnNhixi1yi" +
            "qaXpEDH+HXGF9Hes5Dpc+tAfwFQYxHyVcLyNOEYz8S0/7f8kO36AKXVm2vhV2flN" +
            "zfgZO2rgtGTprc2KUwIDAQAB";
    private final static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANG5Xmt9GeApu/na" +
            "xAcDol7LSArS0vIusyyVjA5U7vy5u1M2zgQa/EWipX/eP5agtRmWjOh2kER0mTAc" +
            "luc2GLGLXKKppekQMf4dcYX0d6zkOlz60B/AVBjEfJVwvI04RjPxLT/t/yQ7foAp" +
            "dWba+FXZ+U3N+Bk7auC0ZOmtzYpTAgMBAAECgYARxFJraL24b9Cs6guRTI2E2lPQ" +
            "Fuwn+CzVqhWjYS6d2l40PoBVeLPGcnDy1DEu4Y52DHsDdofiRL51hPaDv+F3gcO/" +
            "JhLaH1s9p6I6SV3fuCE8mVK2gRoyQsGJv/PuZePyOYlarTbkbpaOZroTyCIdbNe9" +
            "W1Xj1EbzXVWvVyBeAQJBAOqOxnuk5+tUCL4OmlYAKWnrb0Zev8y5m23xohoVMnvd" +
            "qY2+ImCDpyWtwzGrupxyHftRuq8MFlKzVvBaH6p34NECQQDk5W0QZs4jJOumN03x" +
            "dOGiGlSA1Pq0H94fxiG/hX5u1hdqRxUib5vRb35iXvO0SvEX9gs7i2eDJdk+1FlI" +
            "smHjAkBcB90U0mU0zmoHuE8SA0o8huXIJJD40LKNdst1lG+UbiqCtOFkIQPKIt19" +
            "dbXogFYHL9AhsopntHoeIB5gklyhAkEAvLaqhKs9qJv37MIL8NZ8cDllEhKF35um" +
            "dmvTxyM4agj4pRaVxh5eSP2zJUdDhZGD1E12VVFJtkauJa7NLbJkIQJAOtwDgkrf" +
            "WrhC8/hPusa51GBVfB/SkhQzxaCb+oFf0qSe25J4fJLdcX20vLLCvF65Es8BH1V9" +
            "NSf8MPqLW0UjrQ==";
    private final static String modulus = "AJRZFbvOwIfU9hNE5bOQEYvZK+irNGJrJMgtlqxdXEtBnsV4dnO1hlcYQ+gtjABk8AMVh99vbW4x7iMNGCzzZcL0LGMAmg6wnU+iOLC79G6nh4FsnIseLVdNN96hja7ANA1wOb/On64GfbR8qsCAOTle5I2Dd/zlaUOyp1DikI0UzIJDKzd1GWGezFSpniITtwBY7c7dsAkHEh1VyYalfMfc0AvlY09n1xmfNs8RRHwJIxj1lVlxaOqHBSA8HlL6y/WYALilOvVZbGtO+tt2E0l51KkrfD+lzNMkbtSDTcwIIUEyulP9ApUXSAUteOeUVcXU2DBodLrTpab3PIFOMAE=";


    @org.junit.Test
    public void test() throws Exception{

        String encrypt = RSA.encrypt("1111", publicKey);
        String decrypt = RSA.decrypt(encrypt, privateKey);

    }


    public static void client(TreeMap<String, Object> params) throws Exception {
        // 生成RSA签名
        String sign = EncryUtil.handleRSA(params, clientPrivateKey);
        params.put("sign", sign);

        String info = JSON.toJSONString(params);
        //随机生成AES密钥
        String aesKey = SecureRandomUtil.getRandom(16);
        //AES加密数据
        String data = AES.encryptToBase64(ConvertUtils.stringToHexString(info), aesKey);

        // 使用RSA算法将商户自己随机生成的AESkey加密
        String encryptkey = RSA.encrypt(aesKey, serverPublicKey);

        Req.data = data;
        Req.encryptkey = encryptkey;

        System.out.println("加密后的请求数据:\n" + new Req());
    }

    public static void server() throws Exception {

        // 验签
        boolean passSign = EncryUtil.checkDecryptAndSign(Req.data,
                Req.encryptkey, clientPublicKey, serverPrivateKey);

        if (passSign) {
            // 验签通过
            String aeskey = RSA.decrypt(Req.encryptkey,
                    serverPrivateKey);
            String data = ConvertUtils.hexStringToString(AES.decryptFromBase64(Req.data,
                    aeskey));

            JSONObject jsonObj = JSONObject.parseObject(data);
            String userid = jsonObj.getString("userid");
            String phone = jsonObj.getString("phone");

            System.out.println("解密后的明文:userid:" + userid + " phone:" + phone);

        } else {
            System.out.println("验签失败");
        }
    }

    static class Req {
        public static String data;
        public static String encryptkey;

        @Override
        public String toString() {
            return "data:" + data + "\nencryptkey:" + encryptkey;
        }
    }
}
