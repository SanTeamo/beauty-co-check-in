package cn.santeamo.checkin.service.impl;

import cn.santeamo.checkin.constant.CheckInRecordConstant;
import cn.santeamo.checkin.dto.CheckInRecordSearchDTO;
import cn.santeamo.checkin.entity.CheckInRecord;
import cn.santeamo.checkin.entity.CheckInTimeConfig;
import cn.santeamo.checkin.mapper.CheckInRecordMapper;
import cn.santeamo.checkin.service.ICheckInRecordService;
import cn.santeamo.checkin.service.ICheckInTimeConfigService;
import cn.santeamo.checkin.vo.CheckInRecordVO;
import cn.santeamo.common.result.Result;
import cn.santeamo.common.util.JsonUtil;
import cn.santeamo.common.util.ParamUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Service
@RequiredArgsConstructor
public class CheckInRecordServiceImpl
    extends ServiceImpl<CheckInRecordMapper, CheckInRecord>
    implements ICheckInRecordService {

    private final ICheckInTimeConfigService checkInTimeConfigService;

    @Override
    public Result<CheckInRecordVO> getDetail(CheckInRecordSearchDTO searchDTO) {
        if (searchDTO.getId() == null) {
            return Result.fail("ID不能为空");
        }
        final List<CheckInRecordVO> list = baseMapper.selectVOPage(null, searchDTO);
        if (ParamUtil.isEmpty(list)) {
            return Result.data(null);
        }
        final CheckInRecordVO vo = list.get(0);
        return Result.data(vo);
    }

    @Override
    public IPage<CheckInRecordVO> getPage(IPage<CheckInRecordVO> page, CheckInRecordSearchDTO searchDTO) {
        return page.setRecords(baseMapper.selectVOPage(page, searchDTO));
    }

    @Override
    public Result<?> submit(CheckInRecord entity) {
        return Result.status(saveOrUpdate(entity));
    }

    @Override
    public Result<?> checkIn() {
        CheckInTimeConfig checkInTimeConfig = checkInTimeConfigService.todayCheckInTimeConfig();
        if (checkInTimeConfig == null) {
            return Result.fail("今天无需打卡哦");
        }
        final LocalDateTime now = LocalDateTime.now();
        final LocalDate today = now.toLocalDate();
        final LocalTime nowTime = LocalTime.now();
        CheckInRecord checkInRecord = getOne(
            Wrappers.lambdaQuery(CheckInRecord.class)
                .eq(CheckInRecord::getDate, today)
        );
        if (checkInRecord == null) {
            checkInRecord = new CheckInRecord();
            checkInRecord.setDate(today);
            checkInRecord.setWorkInState(CheckInRecordConstant.WorkInState.NOT_CHECK_IN);
            checkInRecord.setWorkOutState(CheckInRecordConstant.WorkOutState.NOT_CHECK_IN);
        }
        Integer workInState = checkInRecord.getWorkInState();
        final LocalTime workInTime = checkInTimeConfig.getWorkInTime();
        final LocalTime workOutTime = checkInTimeConfig.getWorkOutTime();
        if (nowTime.isBefore(workInTime)) {
            // 早于上班打卡时间
            if (CheckInRecordConstant.WorkInState.NOT_CHECK_IN.equals(workInState)) {
                checkInRecord.setWorkInState(CheckInRecordConstant.WorkInState.NORMAL);
                checkInRecord.setWorkInTime(now);
            } else {
                return Result.fail("请勿重复打卡");
            }
        } else if (nowTime.isBefore(workOutTime)){
            // 早于下班打卡时间
            if (CheckInRecordConstant.WorkInState.NOT_CHECK_IN.equals(workInState)) {
                checkInRecord.setWorkInState(CheckInRecordConstant.WorkInState.LATE);
                checkInRecord.setWorkInTime(now);
            } else {
                return Result.fail("还未到下班时间，无法打下班卡");
            }
        } else {
            checkInRecord.setWorkOutState(CheckInRecordConstant.WorkOutState.NORMAL);
            checkInRecord.setWorkOutTime(now);
        }
        boolean saveOrUpdate = saveOrUpdate(checkInRecord);
        if (saveOrUpdate) {
            return Result.success("打卡成功");
        }
        return Result.success("打卡失败");
    }

    @Override
    public Result<?> uploadRecord(Map<String, ObjectNode> uploadRecord) {
        final Set<String> dateSet = uploadRecord.keySet();
        CheckInRecord checkInRecord, recordInDb;
        final List<CheckInRecord> updateList = Lists.newArrayList();
        for (String date : dateSet) {
            checkInRecord = JsonUtil.treeToValue(uploadRecord.get(date), CheckInRecord.class);
            if (checkInRecord == null) {
                throw new RuntimeException("上传数据解析失败");
            }
            recordInDb = getOne(Wrappers.lambdaQuery(CheckInRecord.class)
                .eq(CheckInRecord::getDate, date)
            );
            if (recordInDb != null) {
                checkInRecord.setId(recordInDb.getId());
                // 以服务器为准
                if (recordInDb.getWorkInTime() != null) {
                    checkInRecord.setWorkInTime(null);
                    checkInRecord.setWorkInState(null);
                }
            }
            updateList.add(checkInRecord);
        }
        if (updateList.size() > 0) {
            return Result.status(saveOrUpdateBatch(updateList));
        }
        return Result.status(true);
    }
}
