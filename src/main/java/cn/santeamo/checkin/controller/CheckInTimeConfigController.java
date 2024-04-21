package cn.santeamo.checkin.controller;

import cn.santeamo.checkin.entity.CheckInTimeConfig;
import cn.santeamo.checkin.service.ICheckInTimeConfigService;
import cn.santeamo.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 打卡时间配置 前端控制器
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Api(value = "打卡时间配置", tags = "打卡时间配置接口")
@RestController
@RequestMapping("/check-in/check-in-time-config")
@RequiredArgsConstructor
public class CheckInTimeConfigController {
    private final ICheckInTimeConfigService service;

    /**
     * 获取今天打卡时间配置
     *
     * @return 打卡时间配置
     */
    @GetMapping("/today-check-in-time-config")
    @ApiOperation(value = "获取今天打卡时间配置", notes = "传入CheckInTimeConfig")
    public Result<CheckInTimeConfig> todayCheckInTimeConfig() {
        return Result.data(service.todayCheckInTimeConfig());
    }

}
