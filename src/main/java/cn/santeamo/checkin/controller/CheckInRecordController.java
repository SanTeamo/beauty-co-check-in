package cn.santeamo.checkin.controller;

import cn.santeamo.checkin.dto.CheckInRecordSearchDTO;
import cn.santeamo.checkin.service.ICheckInRecordService;
import cn.santeamo.checkin.vo.CheckInRecordVO;
import cn.santeamo.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Api(value = "", tags = "接口")
@RestController
@RequestMapping("/check-in/check-in-record")
@RequiredArgsConstructor
public class CheckInRecordController {
    private final ICheckInRecordService service;

    /**
     * 获取
     *
     * @param searchDTO 查询参数
     */
    @GetMapping("/detail")
    @ApiOperation(value = "获取", notes = "传入CheckInRecord")
    public Result<CheckInRecordVO> getDetail(CheckInRecordSearchDTO searchDTO) {
        return service.getDetail(searchDTO);
    }

    /**
     * 分页获取
     *
     * @param page      分页参数
     * @param searchDTO 查询参数
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页获取", notes = "传入CheckInRecord")
    public Result<IPage<CheckInRecordVO>> getPage(Page<CheckInRecordVO> page, CheckInRecordSearchDTO searchDTO) {
        return Result.data(service.getPage(page, searchDTO));
    }

    /**
     * 分页获取
     */
    @PostMapping("/check-in")
    @ApiOperation(value = "打卡", notes = "打卡")
    public Result<?> checkIn() {
        return service.checkIn();
    }

    /**
     * 分页获取
     */
    @PostMapping("/upload-record")
    @ApiOperation(value = "打卡", notes = "打卡")
    public Result<?> uploadRecord(@RequestBody Map<String, ObjectNode> uploadRecord) {
        return service.uploadRecord(uploadRecord);
    }
}
