package com.demo.service.controller;

import com.zt3000.core.util.StrUtil;
import com.zt3000.log.Log;
import com.zt3000.log.LogFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * redis使用
 *
 * @author shenhongjun
 * @since 2020/4/15
 */
@Api(value = "v1版本", tags = "v1版本")
@RestController
@RequestMapping("/v1/order/redis")
public class RedisController {

    public static final Log log = LogFactory.get();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    @ApiOperation(value = "根据key获取缓存信息", notes = "根据key获取缓存信息,返回key对应的缓存信息,返回状态码:200-正常,,404-无数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "key", dataType = "string", defaultValue = "PRO:USER:UID:18", value = "字符串类型，建议格式:PRO:USER:UID:18或者PRO-USER-UID-18;<详细描述>PRO:项目名或缩写,USER:表名转换为key前缀,UID:区分区key的字段(主键),18:主键值")
    })
    public ResponseEntity getRedis(@RequestParam(name = "key") String key) {
        log.info("Start:根据key={}，获取reids的结果", key);
        String result = redisTemplate.opsForValue().get(key);
        log.info("End:根据key={}，获取reids的结果:{}", key, result);
        if (StrUtil.isBlank(result)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    @ApiOperation(value = "新增缓存信息", notes = "新增缓存信息,返回状态码:202-已接受请求")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "key", dataType = "string", defaultValue = "PRO:USER:UID:18", value = "字符串类型,建议格式:PRO:USER:UID:18或者PRO-USER-UID-18;<详细描述>PRO:项目名或缩写,USER:表名转换为key前缀,UID:区分区key的字段(主键),18:主键值"),
            @ApiImplicitParam(paramType = "query", name = "value", dataType = "string", defaultValue = "test value", value = "字符串类型,缓存值"),
            @ApiImplicitParam(paramType = "query", name = "timeout", dataType = "integer", defaultValue = "1", value = "整数类型,缓存时间(秒)"),
    })
    public ResponseEntity addRedis(@RequestParam(name = "key") String key,
                                   @RequestParam(name = "value") String value,
                                   @RequestParam(name = "timeout") Integer timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(HttpStatus.ACCEPTED.getReasonPhrase());
    }
}
