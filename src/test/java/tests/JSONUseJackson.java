package tests;


import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

public class JSONUseJackson {

    @Test
    void parceJSON() {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            // convert JSON file to map
            Map<?, ?> map = mapper.readValue(Paths.get("src/test/resources/book.json").toFile(), Map.class);
            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
