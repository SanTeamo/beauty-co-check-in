package cn.santeamo.checkin.service;

import cn.santeamo.checkin.dto.CheckInTimeConfigSearchDTO;
import cn.santeamo.checkin.entity.CheckInTimeConfig;
import cn.santeamo.checkin.vo.CheckInTimeConfigVO;
import cn.santeamo.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 打卡时间配置 服务类
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
public interface ICheckInTimeConfigService extends IService<CheckInTimeConfig> {
    /**
     * 获取打卡时间配置
     *
     * @param searchDTO 查询参数
     * @return 打卡时间配置
     */
    Result<CheckInTimeConfigVO> getDetail(CheckInTimeConfigSearchDTO searchDTO);

    /**
     * 分页获取打卡时间配置
     *
     * @param page      分页参数
     * @param searchDTO 查询参数
     * @return 打卡时间配置
     */
    IPage<CheckInTimeConfigVO> getPage(IPage<CheckInTimeConfigVO> page, CheckInTimeConfigSearchDTO searchDTO);

    /**
     * 新增或修改打卡时间配置
     *
     * @param entity 打卡时间配置
     * @return 新增或修改结果
     */
    Result<?> submit(CheckInTimeConfig entity);

    CheckInTimeConfig todayCheckInTimeConfig();
}
