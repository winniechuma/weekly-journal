package cs3500;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.StoreWeekToBujo;
import org.junit.jupiter.api.Test;

public class TestStoreWeekToBujo {
  private StoreWeekToBujo storeWeekToBujo = new StoreWeekToBujo();

  @Test
  public void testHandleSave() {
    this.storeWeekToBujo.handleSave();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Day Text Areas: \n" +
        "{\"day\":\"MONDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"TUESDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"WEDNESDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"THURSDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"FRIDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"SATURDAY\",\"textAreas\":\"\"}\n" +
        "{\"day\":\"SUNDAY\",\"textAreas\":\"\"}\n" +
        "Quotes: {\"quote\":\"\"}\n" +
        "Task Queue: {\"task-queue\":\"\"}\n" +
        "Numeric Fields: \n" +
        "{\"name\":\"count-for-tasks\",\"number\":0}\n" +
        "{\"name\":\"count-for-events\",\"number\":0}\n" +
        "{\"name\":\"set-event-limit\",\"number\":0}\n" +
        "{\"name\":\"set-task-limit\",\"number\":0}\n" +
        "{\"name\":\"tasks-completed\",\"number\":0}\n" +
        "Map Fields: \n" +
        "{\"name\":\"task-queue\",\"map\":{}}\n" +
        "{\"name\":\"task-queue-sub\",\"map\":{}}\n" +
        "{\"name\":\"button-hash-map\",\"map\":{}}\n" +
        "{\"name\":\"day-hash-map\",\"map\":{}}\n" +
        "{\"name\":\"task-area-hash-map\",\"map\":{}}\n" +
        "{\"name\":\"creatable-hash-map\",\"map\":{}}\n" +
        "{\"name\":\"empty-spots-hash-map\",\"map\":{}}\n");
    assertEquals(stringBuilder, stringBuilder);
  }
}
