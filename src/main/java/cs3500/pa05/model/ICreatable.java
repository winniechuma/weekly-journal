package cs3500.pa05.model;


/**
 * An interface ICreatable
 */
public interface ICreatable {
  String makeEditable();

  String getField(int field);

  DaysOfTheWeek getDay();

  boolean isEvent();
}
