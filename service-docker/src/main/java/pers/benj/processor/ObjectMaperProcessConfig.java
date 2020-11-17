package pers.benj.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMaperProcessConfig {

    @Bean
    public ObjectMapperProcess objectMapperProcess() {
        return new ObjectMapperProcess();
    }

    @Bean
    @ConditionalOnBean(ObjectMapperProcess.class)
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper(ObjectMapperProcess objectMapperProcess) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapperProcess.postProcessAfterInitialization(objectMapper, "objectMapper");
        return objectMapper;
    }
}
