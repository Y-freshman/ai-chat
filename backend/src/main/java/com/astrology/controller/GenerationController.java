package com.astrology.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.exception.InputRequiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class GenerationController {

    private static final Logger logger = LoggerFactory.getLogger(GenerationController.class);
    private static final Random random = new Random();
    private static final List<String> randomResponses = Arrays.asList(
            "您好！我是由freshman开发的智能助手。如您有任何问题，我会尽我所能为您提供帮助。",
            "您好！我是freshman开发的智能助手。有什么问题尽管问我。",
            "您好！我是freshman开发的智能助手。请随时提问。",
            "您好！我是freshman开发的智能助手。随时准备回答您的问题。",
            "您好！我是freshman开发的智能助手。请问有什么我可以帮您的？",
            "您好！我是freshman开发的智能助手。有什么问题尽管说。",
            "您好！我是freshman开发的智能助手。随时欢迎提问。",
            "您好！我是freshman开发的智能助手。请告诉我您的问题。",
            "您好！我是freshman开发的智能助手。请问有什么我可以帮助您的？",
            "您好！我是freshman开发的智能助手。随时准备回答您的问题。"
    );

    private static final List<String> freshmanResponses = Arrays.asList(
            "他很帅，就连吴彦祖巅峰期都要暂避锋芒。",
            "freshman真是太帅了，无人能敌。",
            "吴彦祖见了freshman都要自愧不如。",
            "freshman的帅气指数爆表，无人能及。",
            "吴彦祖见到freshman都要躲起来。",
            "freshman的颜值简直太高了，吴彦祖也要甘拜下风。",
            "吴彦祖见到freshman都要感叹不已。",
            "freshman的帅气程度，吴彦祖也要逊色不少。",
            "吴彦祖见到freshman都要自叹不如。",
            "freshman的帅气无人能敌，吴彦祖也要甘拜下风。"
    );


    @GetMapping("/hello")
    public String index() {
        return "hello freshman!!!";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/generate", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateResponse(@RequestBody String content) {
        if (content.contains("你是谁")){
            return getRandomResponse();
        }

        if (content.contains("freshman")) {
            return getRandomFreshmanResponse();
        }
        try {
            GenerationResult result = callWithMessage(content.toString());
            if (result != null && result.getOutput() != null && result.getOutput().getChoices() != null && !result.getOutput().getChoices().isEmpty()) {
                Message message = result.getOutput().getChoices().get(0).getMessage();
                if (message != null) {
                    System.out.println(message.getContent());

                    if (message.getContent().contains("深度求索") || message.getContent().contains("DeepSeek") || message.getContent().contains("deepeek公司")) {
                        return getRandomResponse();
                    }
                    return message.getContent();
                }
            }
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            System.err.println("An error occurred while calling the generation service: " + e.getMessage());
        }
        return "Failed to generate response.";
    }

    private GenerationResult callWithMessage(String content) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(content)
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey("sk-xxxxxxxxxxxxxxxxxxxxxxx")//替换自己的apikey
                .model("deepseek-r1") // 确保模型支持当前接口
                .messages(Arrays.asList(userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }

    private String getRandomResponse() {
        return randomResponses.get(random.nextInt(randomResponses.size()));
    }
    private String getRandomFreshmanResponse() {
        return freshmanResponses.get(random.nextInt(freshmanResponses.size()));
    }
}
