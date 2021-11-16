package cn.imcao.ess.entity.response;

import cn.imcao.ess.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 成功响应模型
 * @date Created at 2021/11/16 16:59
 */
public class SuccessResponse extends Response{

    public SuccessResponse() {
        super();
    }

    public SuccessResponse(Object data) {
        super(Constants.STATUS_OK, Constants.MESSAGE_OK, data);
    }
}
