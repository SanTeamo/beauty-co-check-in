package cn.santeamo.checkin.service;

import cn.santeamo.checkin.dto.CheckInRecordSearchDTO;
import cn.santeamo.checkin.entity.CheckInRecord;
import cn.santeamo.checkin.vo.CheckInRecordVO;
import cn.santeamo.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
public interface ICheckInRecordService extends IService<CheckInRecord> {
    /**
     * 获取
     *
     * @param searchDTO 查询参数
     * @return
     */
    Result<CheckInRecordVO> getDetail(CheckInRecordSearchDTO searchDTO);

    /**
     * 分页获取
     *
     * @param page      分页参数
     * @param searchDTO 查询参数
     * @return
     */
    IPage<CheckInRecordVO> getPage(IPage<CheckInRecordVO> page, CheckInRecordSearchDTO searchDTO);

    /**
     * 新增或修改
     *
     * @param entity
     * @return 新增或修改结果
     */
    Result<?> submit(CheckInRecord entity);

    Result<?> checkIn();
}
