package com.cf.sqlTest.api.aspect;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot20180120.models.QueryDeviceInfoResponseBody;
import com.aliyun.iot20180120.models.RegisterDeviceResponse;
import com.aliyun.iot20180120.models.RegisterDeviceResponseBody;
import com.cf.sqlTest.facade.result.Result;
import com.cf.sqlTest.services.utils.SHAUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

@Aspect
@Component
@Slf4j
public class RequestParamAspect {
    private final String SECRET = "cfmoto@2023sqlTest";

    @Pointcut("@annotation(com.cf.sqlTest.api.annotation.ParamCheck)")
    public void paramCheckPointCut() {}

    @Around("paramCheckPointCut() && args(.., param)")
    public Object processRequestParams(JoinPoint joinPoint, Object param) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 获取请求参数
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("signature");

        // 获取实体类参数
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                if (joinPoint.getArgs()[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) joinPoint.getArgs()[i];
                    params += JSON.toJSONString(file.getOriginalFilename());
                } else {
                    params += JSON.toJSONString(joinPoint.getArgs()[i]);
                }
            }
        }

        // 进行排序
        Map mapTypes = JSON.parseObject(params);
        TreeMap<String,String> sortMapByKey = this.sortMapByKey(mapTypes);

        // 获取参数
        String entityParam = "";
        for (String value : sortMapByKey.values()) {
            entityParam += value;
        }

        log.info("processRequestParams:{}", timestamp+nonce+entityParam);

        // 校验是否合法
        String totalParam = SECRET + timestamp+nonce+entityParam;

        if (!SHAUtil.SHA256(totalParam).equals(sign)) {
            String path = request.getServletPath();
            if (path.indexOf("query") > 0) {
                return new QueryDeviceInfoResponseBody().setCode("sqlTest.server.signException")
                        .setData(null).setSuccess(false).setErrorMessage("sqlTest服务校验签名失败");
            } else if (path.indexOf("register") > 0) {
                return new RegisterDeviceResponse()
                        .setBody(
                                new RegisterDeviceResponseBody().setCode("sqlTest.server.signException")
                                        .setData(null)
                                        .setSuccess(false)
                                        .setErrorMessage("sqlTest服务校验签名失败"));
            } else {
                return Result.buildErrorResult("sqlTest服务校验签名失败");
            }
        }

        return ((ProceedingJoinPoint) joinPoint).proceed();
    }

    private TreeMap<String,String> sortMapByKey(Map<String,String> map){
        if (ObjectUtils.isEmpty(map)||map.isEmpty()){
            return null;
        }
        // 升序排序
        TreeMap<String, String> sortMap = new TreeMap<>(String::compareTo);
        sortMap.putAll(map);
        return sortMap;
    }
}
