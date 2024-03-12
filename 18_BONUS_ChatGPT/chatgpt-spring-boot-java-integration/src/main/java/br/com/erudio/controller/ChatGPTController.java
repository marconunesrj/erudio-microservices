package br.com.erudio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.erudio.service.ChatGPTService;

@RestController
@RequestMapping("/bot")
public class ChatGPTController {
    
    @Autowired
    private ChatGPTService service;
    
    // HOST/bot/chat
    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) throws JsonProcessingException {// throws JsonProcessingException {
        return service.chat(prompt);
    }

    @GetMapping("/chat1")
    public String getChatResponse(@RequestParam("prompt") String prompt) {
        try {
            return service.getChatResponse(prompt);
        } catch (IOException e) {
            // Aqui você pode lidar com exceções de forma apropriada, como logar o erro, lançar outra exceção, etc.
            e.printStackTrace();
            return "Erro ao obter resposta do ChatGPT: " + e.getMessage();
        }
    }
//    @PostMapping("/chat")
//    public String getChatResponse(@RequestBody String prompt) {
//        try {
//            return service.getChatResponse(prompt);
//        } catch (IOException e) {
//            // Aqui você pode lidar com exceções de forma apropriada, como logar o erro, lançar outra exceção, etc.
//            e.printStackTrace();
//            return "Erro ao obter resposta do ChatGPT: " + e.getMessage();
//        }
//    }
}
