package cs3500;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DaysOfTheWeek;
import org.junit.jupiter.api.Test;


/**
 * Class representing the Days of the week Test class
 */
public class TestDaysOfTheWeek {

  /**
   * Testing that the expected Day of the week matches
   * The defined enum variable
   */
  @Test
  public void testCompletion() {
    assertEquals("MONDAY", DaysOfTheWeek.MONDAY.toString());
    assertEquals("TUESDAY", DaysOfTheWeek.TUESDAY.toString());
    assertEquals("WEDNESDAY", DaysOfTheWeek.WEDNESDAY.toString());
    assertEquals("THURSDAY", DaysOfTheWeek.THURSDAY.toString());
    assertEquals("FRIDAY", DaysOfTheWeek.FRIDAY.toString());
    assertEquals("SATURDAY", DaysOfTheWeek.SATURDAY.toString());
    assertEquals("SUNDAY", DaysOfTheWeek.SUNDAY.toString());


  }
}

