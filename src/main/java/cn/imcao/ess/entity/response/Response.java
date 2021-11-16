package cn.imcao.ess.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 响应数据格式
 * @date Created at 2021/11/16 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    public int code;
    public String message;
    public Object data;
}
