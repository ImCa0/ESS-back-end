package cn.imcao.ess;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EssApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {

    }

    private String encrypt(String originPassword) {
        return stringEncryptor.encrypt(originPassword);
    }

    private String decrypt(String encryptedPassword) {
        return stringEncryptor.decrypt(encryptedPassword);
    }
}
