package cn.santeamo.checkin.vo;

import cn.santeamo.checkin.entity.CheckInRecord;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * VO
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "CheckInRecordVO对象", description = "")
public class CheckInRecordVO extends CheckInRecord {

    private static final long serialVersionUID = 1L;
}
