package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Record representing the NumJson
 * @param name
 * @param num
 */
public record NumJson(
    @JsonProperty("name") String name,
    @JsonProperty("number") int num
) {
}
