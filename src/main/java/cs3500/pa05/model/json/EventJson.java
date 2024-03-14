package cs3500.pa05.model.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.ICreatable;
import cs3500.pa05.model.Time;

/**
 *A record EventJson
 * @param name The name
 * @param description The description
 * @param time the time of the event
 * @param duration The duration of the event
 */
public record EventJson (@JsonProperty("name") String name,
                                   @JsonProperty("description") String description,
                                   @JsonProperty("time") String time,
                                   @JsonProperty("duration") String duration)
    implements ICreatableJson {

}