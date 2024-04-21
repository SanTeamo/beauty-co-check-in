package cn.santeamo.checkin.mapper;

import cn.santeamo.checkin.dto.CheckInTimeConfigSearchDTO;
import cn.santeamo.checkin.entity.CheckInTimeConfig;
import cn.santeamo.checkin.vo.CheckInTimeConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 打卡时间配置 Mapper 接口
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
public interface CheckInTimeConfigMapper extends BaseMapper<CheckInTimeConfig> {
    /**
     * 自定义分页
     *
     * @param page
     * @param searchDTO
     * @return
     */
    List<CheckInTimeConfigVO> selectVOPage(
        IPage<?> page,
        @Param("searchDTO") CheckInTimeConfigSearchDTO searchDTO
    );
}
