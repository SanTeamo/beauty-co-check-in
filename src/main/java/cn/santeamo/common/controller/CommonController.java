package cn.santeamo.common.controller;

import cn.santeamo.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Common 前端控制器
 * </p>
 *
 * @author shenle
 * @since 2024-04-21
 */
@Api(value = "Common接口", tags = "Common接口")
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * 服务在线
     */
    @GetMapping("/service-online-check")
    @ApiOperation(value = "服务在线")
    public Result<?> serviceOnlineCheck() {
        return Result.status(true);
    }

}
