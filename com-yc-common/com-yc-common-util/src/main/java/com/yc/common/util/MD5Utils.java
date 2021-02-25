package com.yc.common.util;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * MD5生成工具类
 * User: gogxp
 */
@Slf4j
public class MD5Utils {
    /**
     * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            log.info(MD5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 适用于上G大的文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getMD5(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel ch = raf.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        String result = bufferToHex(messagedigest.digest());
        if (raf != null) {
            raf.close();
        }
        return result;
    }

    public static String getMD5(InputStream inputStream) {
        String result = null;
        try {
            byte[] buffer = new byte[8192];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                messagedigest.update(buffer, 0, length);
            }
            result = bufferToHex(messagedigest.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * md5加密
     * @param s
     * @return
     */
    public static String getMD5(String s) {
        try {
            return getMD5String(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 获取微信分享签名
     * @param sKey
     * @return
     */
    public static String getSHA(String sKey){
        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(sKey.toString().getBytes());
            ciphertext = bufferToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext.toLowerCase();
    }

    /**
     * md5解密
     * @param s
     * @param charset
     * @return
     */
    public static String getMD5(String s, String charset) {
        try {
            return getMD5String(s.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 生成32位md5码
     *
     * @param key
     * @return
     */
    public static String md5Password(String key) {
//        char hexDigits[] = {
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
//        };
        try {
            byte[] btInput = key.getBytes();
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
            return null;
        }
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, Object> data, String key, String signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals("sign")) {
                continue;
            }
            if (data.get(k).toString().trim().length() > 0){// 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).toString().trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        System.out.println("------sb----"+sb.toString());
        if ("MD5".equals(signType)) {
            return MD5(sb.toString());
        }
//        else if ("HMACSHA256".equals(signType)) {
//            return HMACSHA256(sb.toString(), key);
//        }
        else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 生成 MD5, 32位
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * md5加密16位
     * @param str
     * @return
     */
    public static String getMD5Str16(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else{
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        //16位加密，从第9位到25位  大写
//        return md5StrBuff.substring(8, 24).toString().toUpperCase();
        //16位加密，从第9位到25位  小写
        return md5StrBuff.substring(8, 24).toString();
    }

    public static void main(String[] args) throws Exception{
        String str = getMD5("password1");
        System.out.println(str);
    }
}


