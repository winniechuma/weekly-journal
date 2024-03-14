package cs3500.pa05.model;


/**
 * Class representation of time
 */
public class Time {
  private int hours;
  private int min;

  /**
   * The constructor for this time class
   * @param hours hours of this time
   * @param min minutes of the time
   */
  Time(int hours, int min) {
    this.hours = hours;
    this.min = min;
  }

  /**
   * Handles checking if the hour is valid or not
   * @return true or false
   */
  public boolean isValidHour() {
    return (this.hours >= 0 && this.hours <= 23);
  }
  /**
   * Handles checking if the minute is valid or not
   * @return true or false
   */
  public boolean isValidMinute() {
    return (this.min > 0 && this.min <= 60);
  }

  /**
   * Handles getting the hour of this class
   * @return an hour
   */
  public int getHour() {
    try {
      if (isValidHour()) {
        return this.hours;
      } else {
        throw new IllegalArgumentException();
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid hour");
      return 0;
    }
  }

  /**
   *Handles getting the minute of this class
   * @return a minute
   */
  public int getMinute() {
    try {
      if (isValidMinute()) {
        return this.min;
      } else {
        throw new IllegalArgumentException();
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid minute");
      return 0;
    }
  }

}