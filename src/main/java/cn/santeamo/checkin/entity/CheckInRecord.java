package cn.santeamo.checkin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Data
@TableName("t_check_in_record")
@ApiModel(value = "CheckInRecord对象", description = "")
public class CheckInRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty("打卡日期")
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate date;

    @ApiModelProperty("上班打卡时间")
    @TableField("work_in_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime workInTime;

    @ApiModelProperty("上班打卡状态")
    @TableField("work_in_state")
    private Integer workInState;

    @ApiModelProperty("下班打卡时间")
    @TableField("work_out_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime workOutTime;

    @ApiModelProperty("下班打卡状态")
    @TableField("work_out_state")
    private Integer workOutState;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
