package cs3500.pa05.model.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class representing the JSon Utils
 */
public class JsonUtils {
  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
//  public static Record deserializeRecord(Record record) {
//    ObjectMapper mapper = new ObjectMapper();
//    Item readValue = mapper.readValue(record, PropertySheet.Item.class);
//  }
}
