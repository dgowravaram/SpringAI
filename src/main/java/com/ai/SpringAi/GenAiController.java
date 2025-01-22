package com.ai.SpringAi;

import java.io.IOException;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

    ChatService chatService;
    ImageService imageService;

    public GenAiController(ChatService chatService, ImageService imageService){
        this.chatService = chatService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("generate-image")
    public String generateImages(HttpServletResponse response, 
    @RequestParam String prompt,
    @RequestParam(defaultValue = "hd") String quality,
    @RequestParam(defaultValue = "1") int n,
    @RequestParam(defaultValue = "1024") int width,
    @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);
        //String imageUrl = imageResponse.getResult().getOutput().getUrl();
        //response.sendRedirect(imageUrl);

        List<String> imageUrls = imageResponse.getResults().stream()
        .map(result -> result.getOutput().getUrl())
        .collect(Collectors.toList());

        return imageUrls;
    }

}
