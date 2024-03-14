package cs3500.pa05.model;

public class Task implements ICreatable {
  private String name;
  private String description;
  private DaysOfTheWeek day;
  private boolean completionState;

  public Task(String name, String description, DaysOfTheWeek day, boolean completion) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.completionState = completionState;
  }

  public String makeEditable() {
    return "task";
  }

  //prints a field depending on the number
  public String getField(int field) {
    if (field == 0) {
      return this.name;
    } else if (field == 1) {
      return this.description;
    } else if (field == 2) {
      if (this.completionState) {
        return "true";
      } else {
        return "false";
      }
    } else {
      return "";
    }
  }

  public DaysOfTheWeek getDay() {
    return this.day;
  }

  public boolean isEvent() {
    return false;
  }

}
