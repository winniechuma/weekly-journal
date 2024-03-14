package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record representing the taskQueueJson
 * @param taskQueue
 */
public record TaskQueueJson(
    @JsonProperty("task-queue") String taskQueue
) {
}
