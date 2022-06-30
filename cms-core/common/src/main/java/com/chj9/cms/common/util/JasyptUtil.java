package com.chj9.cms.common.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptUtil {
    public static void main(String[] args) {
        String encrypt = encrypt("9o2zuJPJ4qCreChLgk0L",
                "pMIAdlSqxOK3TIp1B2Zv");
        System.out.println(encrypt);

        String decrypt = decrypt("9o2zuJPJ4qCreChLgk0L",encrypt);
        System.out.println(decrypt);
    }
    public static String encrypt(String secretKey, String message) {
        return stringEncryptor(secretKey, message, true);
    }

    public static String decrypt(String secretKey, String message) {
        return stringEncryptor(secretKey, message, false);
    }
    /**
     * @param secretKey ：密钥。加/解密必须使用同一个密钥
     * @param message   ：加/解密的内容
     * @param isEncrypt ：true 表示加密、false 表示解密
     */
    private static String stringEncryptor(String secretKey, String message, boolean isEncrypt) {
        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        pooledPBEStringEncryptor.setConfig(getSimpleStringPBEConfig(secretKey));
        return isEncrypt ? pooledPBEStringEncryptor.encrypt(message) : pooledPBEStringEncryptor.decrypt(message);
    }
    /**
     * @param secretKey : 密钥。加/解密必须使用同一个密钥
     */
    private static SimpleStringPBEConfig getSimpleStringPBEConfig(String secretKey) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(secretKey);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPoolSize("1");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        return config;
    }


}