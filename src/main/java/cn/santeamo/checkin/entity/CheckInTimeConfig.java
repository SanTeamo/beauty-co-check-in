package cn.santeamo.checkin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 打卡时间配置
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Data
@TableName("t_check_in_time_config")
@ApiModel(value = "CheckInTimeConfig对象", description = "打卡时间配置")
public class CheckInTimeConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty("星期")
    @TableField("weekday")
    private Integer weekday;

    @ApiModelProperty("上班打卡时间")
    @TableField("work_in_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private LocalTime workInTime;

    @ApiModelProperty("下班打卡时间")
    @TableField("work_out_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private LocalTime workOutTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
