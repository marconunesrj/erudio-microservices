package br.com.erudio.service;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.vo.request.ChatGptRequest;
import br.com.erudio.vo.response.ChatGptResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ChatGPTService {

    private Logger logger = Logger.getLogger
            (ChatGPTService.class.getName());
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.api.key}")
    String openApiKey;

    @Autowired
    private RestTemplate template;
    
    @SuppressWarnings("unchecked")
    public String chat(String prompt) throws JsonProcessingException { // throws JsonProcessingException {
        
        logger.info("Starting Prompt");
        
        ChatGptRequest request = new ChatGptRequest(model, prompt);

        logger.info((Supplier<String>) template.getInterceptors().getFirst());
        
        String json = new ObjectMapper().writeValueAsString(request);
        
        logger.info(json);
        
        logger.info("Processing Prompt");
        ChatGptResponse response = 
                template.postForObject(url, request, ChatGptResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }
    
    @SuppressWarnings("deprecation")
    public String getChatResponse(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String media = "{\"model\": \"" + model + "\", \"prompt\": \"" + prompt + "\"}";
//        String media = "{\"model\": \"" + model + "\", " + prompt + "\"}";
        logger.info(media);
        RequestBody body = RequestBody.create(mediaType, media);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + openApiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected response code: " + response);

            return response.body().string();
        }
    }

}
