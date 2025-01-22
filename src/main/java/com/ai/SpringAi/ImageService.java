package com.ai.SpringAi;

import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;
    
    public ImageService(OpenAiImageModel openAiImageModel){
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, int n, int width, int height){
        ImageResponse imageResponse = openAiImageModel.call(
            new ImagePrompt(prompt,
            OptionAiImageOptions.builder()
            .withModel("dall-e-2")
            .withQuality(quality)
            .withN(n)
            .withHeight(height)
            .withWidth(width).build())
        );
        return imageResponse;
    }

}
