package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the MapJson
 * @param name Name
 * @param map Map
 */
public record MapJson(
    @JsonProperty("name") String name,
    @JsonProperty("map") Map map
    ) {
}
