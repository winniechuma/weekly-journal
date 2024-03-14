package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The record representing the Quote Json
 * @param quoteTextArea
 */
public record QuoteJson(
    @JsonProperty("quote") String quoteTextArea
) {
}
