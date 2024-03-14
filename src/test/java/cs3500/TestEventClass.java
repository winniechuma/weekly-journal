package cs3500;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.EventClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventClass {
  private String name;
  private String description;
  private String startTime;
  private String duration;
  DaysOfTheWeek expected;
  DaysOfTheWeek day;
  EventClass event;


  /**
   * The before each method that is called before each test is run
   */
  @BeforeEach
  public void setup(){
    name = "OOD";
    description = " A class that is alot of work";
    startTime ="10:00am";
    duration = " 1 hour ";
    day = DaysOfTheWeek.FRIDAY;
    event = new EventClass(name, description,day,startTime,duration);

  }


  /**
   * Test for the make editable method
   */
  @Test
  public void testMakeEditable(){
    Assertions.assertEquals("event", event.makeEditable());
  }

  /**
   * Test for the get field method
   */
  @Test
  public void testGetField(){
    Assertions.assertEquals(name, event.getField(0));
    Assertions.assertEquals(description, event.getField(1));
    Assertions.assertEquals(startTime, event.getField(2));
    Assertions.assertEquals(duration, event.getField(3));
    Assertions.assertEquals("", event.getField(9));
    Assertions.assertEquals("", event.getField(-1));
    Assertions.assertEquals("", event.getField(5));
  }


  /**
   * Test for the get Day method
   */
  @Test
  public void getDay(){
    expected = DaysOfTheWeek.FRIDAY;
    Assertions.assertEquals(expected, event.getDay());
  }
}

