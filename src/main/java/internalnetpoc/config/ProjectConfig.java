package internalnetpoc.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;

import org.springframework.ai.vectorstore.Neo4jVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;




@Configuration
@EnableNeo4jRepositories("internalnetpoc.repository")
public class ProjectConfig {
    @Bean
    public Driver driver() {
        return GraphDatabase.driver(
            "bolt://localhost:7687",
            AuthTokens.basic("neo4j", "lacol_NII"));
    }
    @Bean
    public org. neo4j. ogm. config.Configuration configuration() {
        return new org. neo4j. ogm. config.Configuration.Builder(new ClasspathConfigurationSource("neo4j/ogm.properties")).build();
    }
    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "internalnetpoc");
    }
    @Bean
    public EmbeddingModel embeddingModel() {
        return new OpenAiEmbeddingModel(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
    }
    @Bean
    ChatClient chatClient(ChatClient.Builder builder, Neo4jVectorStore vectorStore, @Value("classpath:/system.md") Resource system) {

        return builder.defaultSystem(system)
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .build();
    }
    @Bean
    OpenAiChatModel chatModel() {
        return new OpenAiChatModel(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
    }
}
