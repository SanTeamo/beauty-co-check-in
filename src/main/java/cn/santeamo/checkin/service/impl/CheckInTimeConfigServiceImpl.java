package cn.santeamo.checkin.service.impl;

import cn.santeamo.checkin.dto.CheckInTimeConfigSearchDTO;
import cn.santeamo.checkin.entity.CheckInTimeConfig;
import cn.santeamo.checkin.mapper.CheckInTimeConfigMapper;
import cn.santeamo.checkin.service.ICheckInTimeConfigService;
import cn.santeamo.checkin.vo.CheckInTimeConfigVO;
import cn.santeamo.common.result.Result;
import cn.santeamo.common.util.ParamUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 打卡时间配置 服务实现类
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Service
public class CheckInTimeConfigServiceImpl
    extends ServiceImpl<CheckInTimeConfigMapper, CheckInTimeConfig>
    implements ICheckInTimeConfigService {

    @Override
    public Result<CheckInTimeConfigVO> getDetail(CheckInTimeConfigSearchDTO searchDTO) {
        if (searchDTO.getId() == null) {
            return Result.fail("ID不能为空");
        }
        final List<CheckInTimeConfigVO> list = baseMapper.selectVOPage(null, searchDTO);
        if (ParamUtil.isEmpty(list)) {
            return Result.data(null);
        }
        final CheckInTimeConfigVO vo = list.get(0);
        return Result.data(vo);
    }

    @Override
    public IPage<CheckInTimeConfigVO> getPage(IPage<CheckInTimeConfigVO> page, CheckInTimeConfigSearchDTO searchDTO) {
        return page.setRecords(baseMapper.selectVOPage(page, searchDTO));
    }

    @Override
    public Result<?> submit(CheckInTimeConfig entity) {
        return Result.status(saveOrUpdate(entity));
    }

    @Override
    public CheckInTimeConfig todayCheckInTimeConfig() {
        LocalDate today = LocalDate.now();
        int weekday = today.getDayOfWeek().getValue();
        return getOne(
            Wrappers.lambdaQuery(CheckInTimeConfig.class)
                .eq(CheckInTimeConfig::getWeekday, weekday)
        );
    }
}
