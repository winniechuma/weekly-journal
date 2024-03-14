package cs3500.pa05.model;

/**
 * Class representing the event class that implements the ICreatable interface
 */
public class EventClass implements ICreatable {
  private String name;
  private String description;
  private DaysOfTheWeek day;
  private String startTime;
  private String duration;

  /**
   * The constructor of this event class
   * @param name A name
   *
   * @param description a description of this event
   *
   * @param day The day this event is happening
   *
   * @param startTime the time this event starts
   *
   * @param duration how long this event will last
   */
  public EventClass(String name, String description, DaysOfTheWeek day,
               String startTime, String duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Handles opening up this event and allowing edits to it
   * @return A string, event
   */
  public String makeEditable() {
    return "event";
  }

  /**
   * Handles returning a field depending on the number
   * @param field number
   * @return A field of this class
   */
  public String getField(int field) {
    if (field == 0) {
      return this.name;
    } else if (field == 1) {
      return this.description;
    } else if (field == 2) {
      return this.startTime;
    } else if (field == 3) {
      return this.duration;
    } else {
      return "";
    }
  }

  /**
   * Acts as a getting of this Day
   * @return a day of this class
   */
  public DaysOfTheWeek getDay() {
    return this.day;
  }


  /**
   *  Handles checking if is event is true
   * @return true or false
   */
  public boolean isEvent() {
    return true;
  }

}
