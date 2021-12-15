package cn.imcao.ess.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 制造任务实体 DO
 * @date Created at 2021/12/15 17:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDO {

    private String orderNumber;
    private String taskNumber;
    private String taskName;
    private String taskDescription;
    private double price;
    private String deliveryDate;
    private String state;
    private int progress;
    private double estimatedTime;
    private String qualifiedRate;
    private String rejectRate;
    private String shippingInfo;
    private int eId;
}
