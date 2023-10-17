package com.cf.sqlTest.services.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 钉钉群报警->群机器人获取: 关键字/Webhook
 * 文档地址: https://developers.dingtalk.com/document/robots/message-types-and-data-format
 * <p>
 * dingtalk:
 * keyWord: xxx
 * url: https://oapi.dingtalk.com/robot/send?access_token=xxx
 * </p>
 * <p>
 * # 企业微信报警，群机器人获取: Webhook且添加启用机器人
 * 文档地址: https://work.weixin.qq.com/api/doc/90000/90136/91770
 * workwechat:
 * keyWord: xxx
 * url: https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx
 * </p>
 * notes: 钉钉群报警为第一优先级
 *
 * @return
 */
@Slf4j
public class DingAlarmUtils {

    private final static String MSG_TYPE = "text";

    private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1,
            1L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(10),
            new ThreadPoolExecutor.DiscardPolicy());

    /**
     * 报警
     *
     * @param content
     * @created 2021/9/7
     */
    public static void alarmException(final String content) {
        try {
            Thread.sleep(1000);
            String traceId = MDC.get("traceId");
            Map<String, String> configMap = caseAlarmConfig();
            executorService.execute(() -> {
                alarm(configMap, content, traceId);
            });
        } catch (Exception e) {
            log.error("alarmException", e);
        }

    }

    /**
     * alarmException
     *
     * @param url
     * @param toUser "@所有人","@all"
     * @return void
     * @author tasher
     * @param: content
     * @created 2021/9/10
     */
    public static void alarmExceptionByCustom(String url, String content, String... toUser) {
        try {
            Thread.sleep(1000);
            executorService.execute(() -> {
                postHook(url, content, toUser);
            });
        } catch (Exception e) {
            log.error("alarmExceptionByCustom", e);
        }

    }

    /**
     * alarm
     *
     * @param traceId 可选MDC.traceId
     * @return void
     * @author tasher
     * @param: content
     * @created 2021/9/7
     */
    private static void alarm(Map<String, String> configMap, String content, String traceId, String... toUser) {

        if (null == configMap) {
            return;
        }
        String profile = SpringUtils.getActiveProfile();
        if (StringUtils.isBlank(traceId)) {
            content = configMap.get("keyWord") + "\n" + profile + "\n" + content;
        } else {
            content = configMap.get("keyWord") + "\n" + profile + "\n" + traceId + "\n" + content;
        }
        postHook(configMap.get("url"), content, toUser);
    }

    private static void postHook(String url, String content, String... toUser) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        Map<String, Object> resultMap = new HashMap<>(2);
        Map<String, Object> contentMap = new HashMap<>(1);
        contentMap.put("content", content);
        resultMap.put("text", contentMap);
        resultMap.put("msgtype", MSG_TYPE);
        if (null != toUser) {
            contentMap.put("mentioned_list", toUser);
        }
        String jsonString = JSON.toJSONString(resultMap);
        StringEntity requestEntity = new StringEntity(jsonString, "utf-8");
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse;
        try {
            httpResponse = client.execute(httpPost);
            String talkResult = EntityUtils.toString(httpResponse.getEntity());
            log.info("talkResult:{}", talkResult);
        } catch (IOException e) {
            log.error("调用警告出错", e);
        }
    }

    /**
     * 匹配报警配置
     */
    private static Map<String, String> caseAlarmConfig() {
        String defaultPrefix = "dingtalk";
        String keyWord = SpringUtils.getProperty(defaultPrefix + ".keyWord");
        if (StringUtils.isBlank(keyWord)) {
            log.warn("获取[{}.keyWord]失败", defaultPrefix);
            defaultPrefix = "workwechat";
            keyWord = SpringUtils.getProperty(defaultPrefix + ".keyWord");
            if (StringUtils.isBlank(keyWord)) {
                log.error("获取[{}.keyWord]失败", defaultPrefix);
                return null;
            }
        }
        String url = SpringUtils.getProperty(defaultPrefix + ".url");
        if (StringUtils.isBlank(url)) {
            log.error("配置[{}.url]不存在", defaultPrefix);
            return null;
        }
        return ImmutableMap.of("keyWord", keyWord, "url", url);
    }
//
//    public static void main(String[] args) {
//        String content = "订单异常告警 \n  \n 订单时间: orderDateString \n 订单编号: orderNumber \n 嗨便利事件ID: eventId\n 商品ID列表: [goodsListString]\n 用户手机号: userPhone \n 用户OpenId: userOpenId \n ";
//        content = StringUtils.replaceFirst(content, "orderDateString", JdTimeUtils.getOffsetFormatDateStr(System.currentTimeMillis() / 1000, "yyyy-MM-dd HH:mm:ss"));
//        content = StringUtils.replaceFirst(content, "orderNumber", "1631244158165600");
//        content = StringUtils.replaceFirst(content, "eventId", "1436168264670306306");
//        content = StringUtils.replaceFirst(content, "goodsListString", "11239,112789");
//        content = StringUtils.replaceFirst(content, "userPhone", "18657157520");
//        content = StringUtils.replaceFirst(content, "userOpenId", "o_5Xl5D3A7_ouhV-DA6dFd9zQot0");
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=9c79a551-6dd7-4b4e-86b1-529b070c1039");
//        httpPost.setHeader("Content-Type", "application/json");
//        Map<String, Object> resultMap = new HashMap<>(2);
//        Map<String, Object> contentMap = new HashMap<>(1);
//        contentMap.put("content", content);
//        contentMap.put("mentioned_list", new String[]{"@所有人", "@all"});
//        resultMap.put("text", contentMap);
//        resultMap.put("msgtype", "text");
//        String jsonString = JSON.toJSONString(resultMap);
//        StringEntity requestEntity = new StringEntity(jsonString, "utf-8");
//        httpPost.setEntity(requestEntity);
//        HttpResponse httpResponse;
//        try {
//            httpResponse = client.execute(httpPost);
//            String talkResult = EntityUtils.toString(httpResponse.getEntity());
//            log.info("talkResult:{}", talkResult);
//        } catch (IOException e) {
//            log.error("调用钉钉警告出错", e);
//        }
//
//    }
}
