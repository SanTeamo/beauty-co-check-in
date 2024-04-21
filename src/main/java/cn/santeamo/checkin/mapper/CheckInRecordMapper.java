package cn.santeamo.checkin.mapper;

import cn.santeamo.checkin.dto.CheckInRecordSearchDTO;
import cn.santeamo.checkin.entity.CheckInRecord;
import cn.santeamo.checkin.vo.CheckInRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
public interface CheckInRecordMapper extends BaseMapper<CheckInRecord> {
    /**
     * 自定义分页
     */
    List<CheckInRecordVO> selectVOPage(
        IPage<?> page,
        @Param("searchDTO") CheckInRecordSearchDTO searchDTO
    );
}
