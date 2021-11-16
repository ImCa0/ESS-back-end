package cn.imcao.ess.entity.response;

import cn.imcao.ess.utils.Constants;

/**
 * @author ImCaO
 * @description 失败响应请求
 * @date Created at 2021/11/16 17:02
 */
public class FailResponse extends Response{

    public FailResponse(int code, String failReason) {
        super(code, Constants.MESSAGE_FAIL + failReason, null);
    }
}
