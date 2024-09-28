package internalnetpoc.controller;

import java.util.function.Function;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.Neo4jVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import internalnetpoc.service.InitService;


@RestController
public class InitController {
    private final InitService initService;
    private final ChatClient chatClient;
    @Autowired
    public InitController(InitService initService, EmbeddingModel embeddingModel, ChatClient chatClient, Neo4jVectorStore vectorStore, OpenAiChatModel chatModel) {
        this.chatClient = chatClient;
        this.initService = initService;
    }

    @GetMapping("/app/v1/init")
    public void init() {
//        initService.PersonCsvToDb();
//        initService.createManagerRelationship();
//        initService.createInteractions();
        initService.addRepositories();
        initService.loadVectorStore();
    }
    @GetMapping("/ai/chat")
    String completion(@RequestParam(value = "message", defaultValue = "What's up fam?? Nkosi here!") String message, String voice) {
        return chatClient.prompt()
                //                        .system(sp -> sp.param("voice", voice))
                .user(message)
                .call()
                .content();
    }
    @GetMapping("/ai/chat2")
    void userMessageTo(@RequestParam(value = "message", defaultValue = "What's up fam?? Nkosi here!") String message, String voice) {
        Function<String, String> generateQuery = userMessage -> chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
        String cypherQuery = generateQuery.apply(message);
        initService.executeCypherQuery(cypherQuery);
    }
}