package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import cs3500.pa05.model.DaysOfTheWeek;
import java.util.List;

/**
 * A record Days Json
 *
 * @param day A day of the week
 * @param textAreaList a list of strings belonging to each text area
 */
public record DaysJson(@JsonProperty("day") DaysOfTheWeek day,
                       @JsonProperty("textAreas") String textAreaList) {
}
