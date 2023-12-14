package com.cf.sqlTest.api.controller;

import cn.hutool.core.text.UnicodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cf.sqlTest.api.request.BdReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lpy
 * @Date: 2023/08/25
 */
//@RestController
@RestController
@Slf4j
public class BdChatController {
    /**
     * transnal
     */
    @GetMapping("/a/{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }

    @GetMapping("/add")
    public String add() {
        try {

        } catch (Exception e) {
            return "redirect:error";
        }
        return "redirect:ok";
    }

    public static void main(String[] args) {
        // 多线程debug


    }

    private final WebClient webClient;

    public BdChatController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://aip.baidubce.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @PostMapping("/bdchat2")
    public String chat2(@RequestBody List<BdReq> req) {
        return "200";
    }

    @GetMapping(value = "/chat")
    public Flux<String> chatStream(HttpServletResponse response, @RequestParam String str) {
        response.setContentType("text/event-stream;charset=utf-8");
        final String accessToken = "24.35806b93c8906cd916475822dd708532.2592000.1705037831.282335-44888395";
        final String url = "/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + accessToken;
        BdReq bdReq = new BdReq();
        bdReq.setRole("user").setContent(str);
        ArrayList<BdReq> req = new ArrayList<>();
        req.add(bdReq);

        JSONArray msgList = new JSONArray();
        msgList.addAll(req);

        JSONObject body = new JSONObject();
        body.put("messages", msgList);
        body.put("stream", true);


        WebClient.ResponseSpec prePost = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve();

        Flux<String> stringFlux = prePost.bodyToFlux(String.class);
        StringBuilder sb = new StringBuilder();
        return stringFlux.mapNotNull(string->{
            ChatCompletion answer = JSON.parseObject(string, ChatCompletion.class);
            if (answer.isEnd()){
                saveResponseToDatabase(sb.toString());
                return "\n\n\nanswer finished=======";
            }
            String result = answer.getResult();
            sb.append(result);
            return result;
        });

    }

    private void saveResponseToDatabase(String response) {
        // 实现将内容保存到数据库的逻辑
        // 这里是示例，你需要实现具体的数据库保存操作
        System.out.println("\n\n\n\nSaving-to database: " + response);
    }
    public static String extractJSON(String input) {
        int startIndex = input.indexOf("{");
        if (startIndex != -1) {
            return input.substring(startIndex);
        } else {
            return null; // 如果未找到 JSON 数据，返回 null 或者抛出异常，视情况而定
        }
    }
}
