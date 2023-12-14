package com.cf.sqlTest.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatCompletion {
    private String id;
    private String object;
    private long created;
//    @JsonProperty("sentence_id")
    private int sentenceId;
//    @JsonProperty("is_end")
    private boolean isEnd;
//    @JsonProperty("is_truncated")
    private boolean isTruncated;
    private String result;
//    @JsonProperty("need_clear_history")
    private boolean needClearHistory;
//    @JsonProperty("finish_reason")
    private String finishReason;
    private Usage usage;

    // Getters and Setters
    // 省略了 Getters 和 Setters 方法

    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;

        // Getters and Setters
        // 省略了 Getters 和 Setters 方法
    }
}
