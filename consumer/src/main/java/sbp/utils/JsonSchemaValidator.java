package sbp.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.SchemaException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import sbp.dto.TransactionDto;

import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

@Slf4j
public class JsonSchemaValidator {
    public static ObjectMapper objectMapper;

    static {
        JsonSchemaValidator.objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
    }

    public static void validateTransaction(TransactionDto transaction, Path schemaPath) throws Exception {
        JsonNode jsonNode = objectMapper.valueToTree(transaction);

        try (InputStream schemaStream = inputStreamFromClasspath(schemaPath.toString())) {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(jsonSchema);

            schema.validate(new JSONObject(jsonNode.toString()));

        } catch (SchemaException e) {
            log.error("Validation error: {}", e.getMessage());
        }
    }

    private static InputStream inputStreamFromClasspath(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
