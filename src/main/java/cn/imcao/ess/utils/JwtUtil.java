package cn.imcao.ess.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

/**
 * @author ImCaO
 * @description JWT 工具类
 * @date Created at 2021/11/16 15:58
 */
public class JwtUtil {

    public static final String TOKEN_USERNAME = "username";
    public static final String TOKEN_ENTERPRISE_ID = "enterpriseId";
    public static final String TOKEN_SUCCESS = "success:";
    public static final String TOKEN_FAIL = "fail:";

    // 过期时间1天
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    // 加密
    private static final String TOKEN_SECRET = "ESS";

    /**
     * @param username 用户名
     * @return Token
     */
    public static String sign(String username, int enterpriseId) {

        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        // 设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        // 附带username生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim(TOKEN_USERNAME, username)
                .withClaim(TOKEN_ENTERPRISE_ID, String.valueOf(enterpriseId))
                .withExpiresAt(date).sign(algorithm);
    }

    /**
     * @param token Token
     * @return 验证结果
     */
    public static String verity(String token) {
        String result = TOKEN_SUCCESS;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            result += jwt.getClaims().get(TOKEN_USERNAME).asString();
            result += ",";
            result += jwt.getClaims().get(TOKEN_ENTERPRISE_ID).asString();
            return result;
        } catch (Exception e) {
            return TOKEN_FAIL + e.getMessage();
        }
    }
}
