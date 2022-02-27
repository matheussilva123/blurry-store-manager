package br.com.dias.blurrystoremanager.configuration.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public JsonConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public String toJson(final Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T toObject(final String json, final Class<? extends T> clazz) {
        if (json.isBlank()) {
            return null;
        }
        return (T) objectMapper.readValue(json, clazz);
    }

}