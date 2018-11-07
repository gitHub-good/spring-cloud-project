package com.xu.springcloud.shop.common.base.utils;
import java.security.MessageDigest;

/**
 * @author 徐亮亮
 * @date 2018/10/30 20:46
 * @Description: MD5加密工具类
 */
public final class MD5Util {
    /**
     * @author 徐亮亮
     * @date 2018/10/31 18:14
     * @Description: MD5二次加密操作（加盐）
     */
    public static String md5PassSalt(String ...args){
        String[] arrayPass = args;
        String arrayStr = "";
        for (int i=0; i<arrayPass.length; i++){
            arrayStr += arrayPass[i];
        }
        String newPass = MD5Util.MD5(arrayStr);
        return newPass;
    }
    private  static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
        try {  
            byte[] btInput = s.getBytes();  
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(btInput);  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    public static void main(String[] args) {
        String[] strings = {"张三","123456aa","adsd132"};
        System.out.println(MD5Util.md5PassSalt(strings));

        String s = "asda";
        StringBuffer sf = new StringBuffer();
        StringBuilder sb = new StringBuilder();
    }
}  