package system.score.vms.utils;

import system.score.vms.exception.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for JSON serialization and deserialization using Jackson.
 * Provides convenient methods for converting between Java objects and JSON.
 */
public class JsonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
    private final ObjectMapper objectMapper;
    
    /**
     * Constructor that initializes ObjectMapper with enterprise-ready configuration.
     */
    public JsonUtil() {
        this.objectMapper = createObjectMapper();
    }
    
    /**
     * Creates and configures the ObjectMapper with enterprise settings.
     * 
     * @return Configured ObjectMapper instance
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // Register modules
        mapper.registerModule(new JavaTimeModule());
        
        // Configure serialization
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        
        // Configure deserialization
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        
        return mapper;
    }
    
    /**
     * Converts a Java object to JSON string.
     * 
     * @param object The object to serialize
     * @return JSON string representation
     * @throws RestClientException If serialization fails
     */
    public String toJson(Object object) throws RestClientException {
        try {
            if (object == null) {
                return "null";
            }
            
            String json = objectMapper.writeValueAsString(object);
            logger.debug("Serialized object to JSON: {}", json);
            return json;
            
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize object to JSON: {}", object.getClass().getSimpleName(), e);
            throw new RestClientException("Failed to serialize object to JSON: " + e.getMessage(), 
                    "JSON_SERIALIZATION_ERROR", e);
        }
    }
    
    /**
     * Converts a JSON string to a Java object of the specified type.
     * 
     * @param json The JSON string
     * @param clazz The target class
     * @param <T> The target type
     * @return Deserialized object
     * @throws RestClientException If deserialization fails
     */
    public <T> T fromJson(String json, Class<T> clazz) throws RestClientException {
        try {
            if (json == null || json.trim().isEmpty()) {
                return null;
            }
            
            T object = objectMapper.readValue(json, clazz);
            logger.debug("Deserialized JSON to object of type: {}", clazz.getSimpleName());
            return object;
            
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize JSON to {}: {}", clazz.getSimpleName(), json, e);
            throw new RestClientException("Failed to deserialize JSON to " + clazz.getSimpleName() + 
                    ": " + e.getMessage(), "JSON_DESERIALIZATION_ERROR", e);
        }
    }
    
    /**
     * Converts a JSON string to a List of objects of the specified type.
     * 
     * @param json The JSON string
     * @param clazz The target class for list elements
     * @param <T> The target type
     * @return List of deserialized objects
     * @throws RestClientException If deserialization fails
     */
    public <T> List<T> fromJsonList(String json, Class<T> clazz) throws RestClientException {
        try {
            if (json == null || json.trim().isEmpty()) {
                return null;
            }
            
            TypeReference<List<T>> typeRef = new TypeReference<List<T>>() {};
            List<T> list = objectMapper.readValue(json, 
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            
            logger.debug("Deserialized JSON to list of {} objects", clazz.getSimpleName());
            return list;
            
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize JSON to List<{}>: {}", clazz.getSimpleName(), json, e);
            throw new RestClientException("Failed to deserialize JSON to List<" + clazz.getSimpleName() + 
                    ">: " + e.getMessage(), "JSON_DESERIALIZATION_ERROR", e);
        }
    }
    
    /**
     * Converts a JSON string to a Java object using TypeReference for complex types.
     * 
     * @param json The JSON string
     * @param typeRef The TypeReference for the target type
     * @param <T> The target type
     * @return Deserialized object
     * @throws RestClientException If deserialization fails
     */
    public <T> T fromJson(String json, TypeReference<T> typeRef) throws RestClientException {
        try {
            if (json == null || json.trim().isEmpty()) {
                return null;
            }
            
            T object = objectMapper.readValue(json, typeRef);
            logger.debug("Deserialized JSON using TypeReference");
            return object;
            
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize JSON using TypeReference: {}", json, e);
            throw new RestClientException("Failed to deserialize JSON using TypeReference: " + 
                    e.getMessage(), "JSON_DESERIALIZATION_ERROR", e);
        }
    }
    
    /**
     * Pretty prints a JSON string with proper formatting.
     * 
     * @param json The JSON string to format
     * @return Formatted JSON string
     * @throws RestClientException If formatting fails
     */
    public String prettyPrint(String json) throws RestClientException {
        try {
            Object obj = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Failed to pretty print JSON: {}", json, e);
            throw new RestClientException("Failed to pretty print JSON: " + e.getMessage(), 
                    "JSON_FORMATTING_ERROR", e);
        }
    }
    
    /**
     * Checks if a string is valid JSON.
     * 
     * @param json The string to validate
     * @return true if valid JSON, false otherwise
     */
    public boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
    
    /**
     * Gets the configured ObjectMapper instance.
     * 
     * @return The ObjectMapper instance
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
