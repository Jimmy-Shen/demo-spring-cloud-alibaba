package com.demo.service.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zt3000.capital.service.CapitalService;
import com.zt3000.core.bean.BeanUtil;
import com.demo.service.dao.entity.GpsInfo;
import com.demo.service.feign.FeignService;
import com.zt3000.log.Log;
import com.zt3000.log.LogFactory;
import com.demo.service.model.response.GpsInfoResp;
import com.demo.service.service.GpsService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//import io.seata.spring.annotation.GlobalTransactional;

/**
 * gps api
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
@Api(value = "v1版本", tags = "20200501原生创建-v1版本")
@RestController
@RequestMapping("/v1/order/gps")
public class GpsController {

    public static final Log log = LogFactory.get();

    @Autowired
    private GpsService gpsService;

    @Resource
    private CapitalService capitalService;

    @Autowired
    private FeignService feignService;

    /**
     * 获取总数
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "获取GPS信息总数", tags = {"20200501升级版本-v1.1版本", "20200501升级版本-v1.2版本"}, notes = "获取GPS信息总数,无参数,返回状态码:200-正常,404-无数据")
    @ApiImplicitParams({})
    @ApiOperationSupport(
            responses = @DynamicResponseParameters(properties = {
                    @DynamicParameter(value = "状态", name = "id", dataTypeClass = Integer.class),
                    @DynamicParameter(value = "描述", name = "name", dataTypeClass = String.class)
            })
    )
    public ResponseEntity getGps() {
        int count = gpsService.count();
        if (count == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(count);
    }

    /**
     * 获取dubbo api信息
     * @return
     */
    @GetMapping(path = "/Capitals")
    @ApiOperation(value = "测试信息", notes = "测试信息,返回nacos配置信息.,返回状态码:200-正常")
    @ApiImplicitParams({})
    public ResponseEntity getGoodSources() {
        Map result = new HashMap<String, String>(2);
        result.put("result", "success");
        result.put("version", "v1");

        Object queryCapitalAccount = capitalService.queryCapitalAccount();
        result.put("queryCapitalAccount", JSON.toJSONString(queryCapitalAccount));

        return ResponseEntity.ok().body(result);
    }

    /**
     * 根据id获取信息
     * @return
     */
    @GetMapping(path = "/id")
    @ApiOperation(value = "根据ID获取GPS信息", tags = "20200504升级版本-v1.3版本", notes = "根据ID获取GPS信息,无参数,返回状态码:200-正常,404-无数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "ID", dataType = "long", defaultValue = "405562")
    })
    @ApiOperationSupport(
            params = @DynamicParameters(
                    properties = {
                            @DynamicParameter(value = "gps主键", name = "id", dataTypeClass = Integer.class)
                    }
            ),
            responses = @DynamicResponseParameters(properties = {
                    @DynamicParameter(value = "gps主键", name = "id", dataTypeClass = Integer.class),
                    @DynamicParameter(value = "手机号", name = "mobile", dataTypeClass = String.class),
                    @DynamicParameter(value = "车牌号", name = "carNum", dataTypeClass = String.class),
                    @DynamicParameter(value = "地址", name = "addr", dataTypeClass = String.class),
                    @DynamicParameter(value = "经度(百度坐标)", name = "x", dataTypeClass = Double.class),
                    @DynamicParameter(value = "纬度(百度坐标)", name = "y", dataTypeClass = Double.class),
            })
    )
    public ResponseEntity<GpsInfoResp> getGpsById(@RequestParam("id") int id) {
        GpsInfo gpsInfo = gpsService.queryById(id);
        if (gpsInfo == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        GpsInfoResp gpsInfoResp = BeanUtil.toBean(gpsInfo, GpsInfoResp.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gpsInfoResp);
    }

    /**
     * 根据id修改source信息
     * @param id
     * @param source
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "根据ID修改GPS信息", notes = "根据ID修改GPS信息,有参数,返回状态码:200-正常,404-无数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "ID", dataType = "long", defaultValue = "405562"),
            @ApiImplicitParam(paramType = "query", name = "source", value = "来源", dataType = "int", defaultValue = "1"),
    })
    @GlobalTransactional
    public ResponseEntity updateOneGps2ById(@RequestParam("id") int id, @RequestParam("source") int source) {

//        ResponseEntity<List<Test>> feignServiceTest = feignService.getTest();
//        log.info("feignService.getTest()返回结果:{}", JSON.toJSONString(feignServiceTest.getBody()));

        GpsInfo gpsInfo = new GpsInfo();
        gpsInfo.setSource((short) source);

        UpdateWrapper<GpsInfo> wrapper = new UpdateWrapper();
        wrapper.eq("id", id);

        boolean update = gpsService.update(gpsInfo, wrapper);
        if (!update) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
        }

        // 需要调用远程服务
        ResponseEntity responseEntity = feignService.insertTest(source, source);

        log.info("事务执行结果,update:{},insert:{}", update, responseEntity.getStatusCode() == HttpStatus.OK);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(update);
    }
}
