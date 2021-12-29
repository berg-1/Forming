package me.berg.forming.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.berg.forming.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Dashboard")
public class ProjectDashboardController {


    /**
     * 项目收集信息
     */
    @GetMapping("/user/project/report/stats")
    public Result projectReportStats(String projectKey) {
//        //浏览量
//        Long viewCount = redisUtils.hmSize(StrUtil.format(PROJECT_VIEW_IP_LIST, projectKey));
//        //平均完成时间
//        Map<String, Object> ResultJsonUtilMap = ResultJsonUtilService.getMap(Wrappers.<ResultJsonUtilEntity>query().select(" IFNULL(ROUND(AVG(complete_time),0),0) as avgCompleteTime, count(1) as completeCount").eq("project_key", projectKey));
//        ResultJsonUtilMap.put("viewCount", viewCount);
        return Result.success();
    }


    /**
     * 项目收集情况 按周查看
     */
    @GetMapping("/user/project/report/situation")
    public Result projectReportSituation(String projectKey) {
//        return ResultJsonUtil.success(projectDashboardService.projectReportSituation(projectKey));
        return Result.success();
    }


    /**
     * 项目收集位置情况
     */
    @GetMapping("/user/project/report/position")
    public Result projectReportPosition(String projectKey) {
//        return ResultJsonUtil.success(projectDashboardService.projectReportPosition(projectKey));
        return Result.success();
    }


    /**
     * 项目收集设备
     */
    @GetMapping("/user/project/report/device")
    public Result projectReportDevice(String projectKey) {
//        return ResultJsonUtil.success(projectDashboardService.projectReportDevice(projectKey));
        return Result.success();
    }


    /**
     * 项目收集来源
     */
    @GetMapping("/user/project/report/source")
    public Result projectReportSource(String projectKey) {
//        return ResultJsonUtil.success(projectDashboardService.projectReportSource(projectKey));
        return Result.success();
    }

    /**
     * 数据分析
     */
    @GetMapping("/user/project/report/analysis")
    public Result projectReportAnalysis(String projectKey) {
//        return ResultJsonUtil.success(projectDashboardService.projectReportAnalysis(projectKey));
        return Result.success();
    }
}
