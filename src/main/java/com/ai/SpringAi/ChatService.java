package com.ai.SpringAi;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;

public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

    public String getResponseOptions(String prompt){
        ChatResponse response = chatModel.call(
            new Prompt(prompt, 
                OpenAiChatOptions.builder()
                .withModel("gpt-4-o")
                .withTemperature(0.4)
                .build()));
        return response.getResult().getOutput().getContent();
    }
}