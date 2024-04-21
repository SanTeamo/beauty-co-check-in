package cn.santeamo.checkin.vo;

import cn.santeamo.checkin.entity.CheckInTimeConfig;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 打卡时间配置VO
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "CheckInTimeConfigVO对象", description = "打卡时间配置")
public class CheckInTimeConfigVO extends CheckInTimeConfig {

    private static final long serialVersionUID = 1L;
}
