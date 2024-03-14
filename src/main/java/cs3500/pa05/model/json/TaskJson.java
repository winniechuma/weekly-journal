package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.Time;

/**
 * A record of TaskJson
 * @param name The name
 * @param description The description
 * @param completionStatus whether the task is complete or not
 */
public record TaskJson(@JsonProperty("name") String name,
                       @JsonProperty("description") String description,
                       @JsonProperty("completion-status") String completionStatus)
    implements ICreatableJson {
}

