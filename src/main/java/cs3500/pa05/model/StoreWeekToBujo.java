package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.DaysJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.ICreatableJson;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.MapJson;
import cs3500.pa05.model.json.NumJson;
import cs3500.pa05.model.json.QuoteJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.TaskQueueJson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class StoreWeekToBujo {

  private String mondayTextArea = "";

  private String tuesdayTextArea = "";

  private String wednesdayTextArea = "";

  private String thursdayTextArea = "";

  private String fridayTextArea = "";

  private String saturdayTextArea = "";

  private String sundayTextArea = "";

  private String weekNameTextArea = "";

  private String dailyQuotesTextArea = "";

  private String taskQueueTextArea = "";

  private Map<Button, Boolean> emptySpotsHashMap = new HashMap<>();

  private Map<ICreatable, Button> creatableHashMap = new HashMap<>();

  private Map<ICreatable, DaysOfTheWeek> textAreaHashMap = new HashMap<>();

  private Map<Button, TextArea> dayHashMap = new HashMap<>();

  private Map<ICreatable, Button> buttonHashMap = new HashMap<>();

  private Map<ICreatable, Boolean> taskQueueSub = new HashMap<>();

  private Map<ICreatable, Boolean> taskQueue = new HashMap<>();

  private int tasksCompleted = 0;

  private int setTaskLimit = 0;

  private int setEventLimit = 0;

  private int countForEvents = 0;

  private int countForTasks = 0;

  private String directoryToWrite;
  private boolean isValid = false;
  private QuoteJson quoteJson;
  private TaskQueueJson taskQueueJson;
  private StringBuilder stringBuilder = new StringBuilder();

  /**
   * Constructor for creating a save bujo file
   */
  public StoreWeekToBujo() {

  }

  /**
   * Alternative contructor for when bujo save file is loaded
   *
   * @param stage the stage where the file is being loaded
   * @param countForTasks the count of the tasks created
   * @param countForEvents the count of the events created
   * @param setEventLimit the limit set for the number of events
   * @param setTaskLimit the limit set for the number of tasks
   * @param tasksCompleted the number of tasks that were completed
   * @param taskQueue the queue of created tasks
   * @param taskQueueSub the substitute of the queue of created tasks
   * @param buttonHashMap the hashMap that connects buttons to their Created events/tasks
   * @param dayHashMap the day associated with each textArea
   * @param textAreaHashMap the day associated with each button in the mini viewer
   * @param creatableHashMap the event/task associated with each button in the mini viewer
   * @param emptySpotsHashMap the button associated with each available spot in the mini viewer
   *                          (true/false)
   */

  public StoreWeekToBujo(Stage stage, int countForTasks, int countForEvents,
                               int setEventLimit, int setTaskLimit, int tasksCompleted,
                               Map<ICreatable, Boolean> taskQueue,
                               Map<ICreatable, Boolean> taskQueueSub,
                               Map<ICreatable, Button> buttonHashMap,
                               Map<Button, TextArea> dayHashMap,
                               Map<ICreatable, DaysOfTheWeek> textAreaHashMap,
                               Map<ICreatable, Button> creatableHashMap,
                               Map<Button, Boolean> emptySpotsHashMap) {

    //FOR DISPLAY
    this.buttonHashMap = buttonHashMap;

    //FOR FUNCTION
//    this.stage = stage;
    this.isValid = true;
//    this.clickMe = new Button();
//    this.SetYourLimit = new Button();
    this.setEventLimit = setEventLimit;
    this.setTaskLimit = setTaskLimit;
    this.dayHashMap = dayHashMap;
    this.textAreaHashMap = textAreaHashMap;
    this.creatableHashMap = creatableHashMap;
    this.emptySpotsHashMap = emptySpotsHashMap;
    this.countForTasks = countForTasks;
    this.countForEvents = countForEvents;
    this.tasksCompleted = tasksCompleted;
    this.taskQueue = taskQueue;
    this.taskQueueSub = taskQueueSub;

  }

  /**
   * Handles saving the current week into a bujo file
   */
  public void handleSave() {
    saveDayList();
    saveDailyQuotes();
    saveTaskQueue();
    saveNums();
    saveMaps();
    saveBujo();
  }

  /**
   * Saves each day into a DayJson, and then it is then stored in the list field
   */
  private void saveDayList() {
    List<DaysJson> daysJsonsList;
    DaysJson daysJsonMonday = new DaysJson(DaysOfTheWeek.MONDAY, this.mondayTextArea);
    DaysJson daysJsonTuesday = new DaysJson(DaysOfTheWeek.TUESDAY, this.tuesdayTextArea);
    DaysJson daysJsonWednesday = new DaysJson(DaysOfTheWeek.WEDNESDAY, this.wednesdayTextArea);
    DaysJson daysJsonThursday = new DaysJson(DaysOfTheWeek.THURSDAY, this.thursdayTextArea);
    DaysJson daysJsonFriday = new DaysJson(DaysOfTheWeek.FRIDAY, this.fridayTextArea);
    DaysJson daysJsonSaturday = new DaysJson(DaysOfTheWeek.SATURDAY, this.saturdayTextArea);
    DaysJson daysJsonSunday = new DaysJson(DaysOfTheWeek.SUNDAY, this.sundayTextArea);
    daysJsonsList = Arrays.asList(daysJsonMonday,
        daysJsonTuesday, daysJsonWednesday,
        daysJsonThursday, daysJsonFriday, daysJsonSaturday, daysJsonSunday);

    this.stringBuilder.append("Day Text Areas: \n");
    for (int i = 0; i < daysJsonsList.size(); i++) {
      JsonNode serializedRecord = JsonUtils.serializeRecord(daysJsonsList.get(i));
      this.stringBuilder.append(serializedRecord.toString() + "\n");
    }


  }

  /**
   * Saves the text area of the daily quotes into the quoteJson field
   */
  private void saveDailyQuotes() {
    this.quoteJson = new QuoteJson(this.dailyQuotesTextArea);
    JsonNode serializedRecord = JsonUtils.serializeRecord(this.quoteJson);
    this.stringBuilder.append("Quotes: " + serializedRecord.toString() + "\n");
  }

  /**
   * Saves the text area of the task queue into the taskQueueJson field
   */
  private void saveTaskQueue() {
    this.taskQueueJson = new TaskQueueJson(this.taskQueueTextArea);
    JsonNode serializedRecord = JsonUtils.serializeRecord(this.taskQueueJson);
    this.stringBuilder.append("Task Queue: " + serializedRecord.toString() + "\n");
  }

  /**
   * Saves the list of numbers needed for a week into the numJsonList field
   */
  private void saveNums() {
    NumJson countForTasksJson = new NumJson("count-for-tasks", this.countForEvents);
    NumJson countForEventsJson = new NumJson("count-for-events", this.countForEvents);
    NumJson setEventLimitJson = new NumJson("set-event-limit", this.setEventLimit);
    NumJson setTaskLimitJson = new NumJson("set-task-limit", this.setTaskLimit);
    NumJson taskCompletedJson = new NumJson("tasks-completed", this.tasksCompleted);

    List<NumJson> numJsonList =
        Arrays.asList(countForTasksJson, countForEventsJson, setEventLimitJson,
        setTaskLimitJson, taskCompletedJson);

    this.stringBuilder.append("Numeric Fields: \n");
    for (int i = 0; i < numJsonList.size(); i++) {
      JsonNode serializedRecord = JsonUtils.serializeRecord(numJsonList.get(i));
      this.stringBuilder.append(serializedRecord.toString() + "\n");
    }
  }

  /**
   * Saves all hashMaps needed to load a week into a MapJson
   */
  private void saveMaps() {

    Map<ICreatableJson, Boolean> creatableJsonBooleanMap = new HashMap<>();
    for (Map.Entry<ICreatable, Boolean> inMap : this.taskQueue.entrySet()) {
      if (inMap.getKey().isEvent()) {
        creatableJsonBooleanMap.put(new EventJson(inMap.getKey().getField(0),
            inMap.getKey().getField(1), inMap.getKey().getField(2),
                inMap.getKey().getField(3)), inMap.getValue());
      } else {
        creatableJsonBooleanMap.put(new TaskJson(inMap.getKey().getField(0),
                inMap.getKey().getField(1), inMap.getKey().getField(2)), inMap.getValue());
      }
    }
    Map<ICreatableJson, Boolean> creatableJsonBooleanMapSub = new HashMap<>();
    for (Map.Entry<ICreatable, Boolean> inMap : this.taskQueueSub.entrySet()) {
      if (inMap.getKey().isEvent()) {
      creatableJsonBooleanMapSub.put(new EventJson(inMap.getKey().getField(0),
          inMap.getKey().getField(1), inMap.getKey().getField(2),
              inMap.getKey().getField(3)), inMap.getValue());
    } else {
        creatableJsonBooleanMapSub.put(new TaskJson(inMap.getKey().getField(0),
                inMap.getKey().getField(1), inMap.getKey().getField(2)), inMap.getValue());
      }
    }
    Map<ICreatableJson, String> creatableJsonButtonJsonMap = new HashMap<>();
    for (Map.Entry<ICreatable, Button> inMap : this.buttonHashMap.entrySet()) {
      if (inMap.getKey().isEvent()) {
      creatableJsonButtonJsonMap.put(new EventJson(inMap.getKey().getField(0),
              inMap.getKey().getField(1), inMap.getKey().getField(2),
              inMap.getKey().getField(3)), inMap.getValue().getText());
    } else {
        creatableJsonButtonJsonMap.put(new TaskJson(inMap.getKey().getField(0),
                inMap.getKey().getField(1), inMap.getKey().getField(2)),
            inMap.getValue().getText());
      }
    }
    Map<String, String> buttonJsonTextAreaJsonMap = new HashMap<>();
    for (Map.Entry<Button, TextArea> inMap : this.dayHashMap.entrySet()) {
      buttonJsonTextAreaJsonMap.put(inMap.getKey().getText(),
          inMap.getValue().getText());
    }
    Map<ICreatableJson, DaysOfTheWeek> creatableJsonDayMap = new HashMap<>();
    for (Map.Entry<ICreatable, DaysOfTheWeek> inMap : this.textAreaHashMap.entrySet()) {
      if (inMap.getKey().isEvent()) {
      creatableJsonDayMap.put(new EventJson(inMap.getKey().getField(0),
          inMap.getKey().getField(1), inMap.getKey().getField(2),  inMap.getKey().getField(3)),
          inMap.getValue());
    } else {
        creatableJsonDayMap.put(new TaskJson(inMap.getKey().getField(0),
                inMap.getKey().getField(1), inMap.getKey().getField(2)),
            inMap.getValue());
      }
    }
    Map<ICreatableJson, String> creatableButtonMap = new HashMap<>();
    for (Map.Entry<ICreatable, Button> inMap : this.creatableHashMap.entrySet()) {
      if (inMap.getKey().isEvent()) {
        creatableButtonMap.put(new EventJson(inMap.getKey().getField(0),
                inMap.getKey().getField(1), inMap.getKey().getField(2),
                inMap.getKey().getField(3)), inMap.getValue().getText());
      } else {
        creatableButtonMap.put(new TaskJson(inMap.getKey().getField(0),
            inMap.getKey().getField(1), inMap.getKey().getField(2)),
            inMap.getValue().getText());
      }
    }
    Map<String, Boolean> buttonJsonBooleanMap = new HashMap<>();
    for (Map.Entry<Button, Boolean> inMap : this.emptySpotsHashMap.entrySet()) {
      buttonJsonBooleanMap.put(inMap.getKey().getText(), inMap.getValue());
    }
    MapJson taskQueueJson = new MapJson("task-queue", creatableJsonBooleanMap);
    MapJson taskQueueSubJson = new MapJson("task-queue-sub", creatableJsonBooleanMapSub);
    MapJson buttonHashMapJson = new MapJson("button-hash-map", creatableJsonButtonJsonMap);
    MapJson dayHashMapJson = new MapJson("day-hash-map", buttonJsonTextAreaJsonMap);
    MapJson textAreaHashMapJson = new MapJson("task-area-hash-map", creatableJsonDayMap);
    MapJson creatableHashMapJson = new MapJson("creatable-hash-map", creatableButtonMap);
    MapJson emptySpotsHashMapJson = new MapJson("empty-spots-hash-map", buttonJsonBooleanMap);

    List<MapJson> mapJsonList = new ArrayList<>(Arrays.asList(taskQueueJson, taskQueueSubJson,
        buttonHashMapJson, dayHashMapJson, textAreaHashMapJson, creatableHashMapJson,
        emptySpotsHashMapJson));

    this.stringBuilder.append("Map Fields: \n");
    for (int i = 0; i < mapJsonList.size(); i++) {
      JsonNode serializedRecord = JsonUtils.serializeRecord(mapJsonList.get(i));
      this.stringBuilder.append(serializedRecord.toString() + "\n");
      System.out.print("Did it on em");
    }

  }

  /**
   * Saves the week into a bujo file that is serialized to a directory
   */
  private void saveBujo() {
    this.stringBuilder.append(this.weekNameTextArea);
    writeBujoToFile(this.stringBuilder.toString());
  }

  /**
   * Writes the bujo data onto a bujo file
   */
  private void writeBujoToFile(String serializedBujo) {
    try {
      System.out.println(this.directoryToWrite + ".bujo");
      Path bujoPath = Paths.get(this.directoryToWrite + ".bujo");
      FileWriter fileWriter = new FileWriter(bujoPath.toFile());
      fileWriter.write(serializedBujo);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Failed to write to file");
    }
  }

  /**
   * Sets the name of the week field to the given week name
   *
   * @param weekName the given week name
   */
  public void setWeekName(String weekName) {
    this.weekNameTextArea = weekName;
  }

  /**
   * Sets the monday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setMondayTextArea(String textArea) {
    this.mondayTextArea = textArea;
  }

  /**
   * Sets the tuesday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setTuesdayTextArea(String textArea) {
    this.tuesdayTextArea = textArea;
  }

  /**
   * Sets the wednesday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setWednesdayTextArea(String textArea) {
    this.wednesdayTextArea = textArea;
  }

  /**
   * Sets the thursday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setThursdayTextArea(String textArea) {
    this.thursdayTextArea = textArea;
  }

  /**
   * Sets the friday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setFridayTextArea(String textArea) {
    this.fridayTextArea = textArea;
  }

  /**
   * Sets the saturday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setSaturdayTextArea(String textArea) {
    this.saturdayTextArea = textArea;
  }

  /**
   * Sets the sunday text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setSundayTextArea(String textArea) {
    this.sundayTextArea = textArea;
  }

  /**
   * Sets the daily quotes text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setDailyQuotesTextArea(String textArea) {
    this.dailyQuotesTextArea = textArea;
  }

  /**
   * Sets the task queue text area field to the given text area
   *
   * @param textArea the given textArea
   */
  public void setTaskQueueTextArea(String textArea) {
    this.taskQueueTextArea = textArea;
  }

  /**
   * Sets the directoryToWrite field to the specified string
   *
   * @param path the string passed in by the user
   */
  public void setDirectoryToWrite(String path) {
    this.directoryToWrite = path;
  }

  /**
   * Stores all the fields of the bujo into this class
   */
  public void storeFields(Stage stage, int countForTasks, int countForEvents,
                         int setEventLimit, int setTaskLimit, int tasksCompleted,
                         Map<ICreatable, Boolean> taskQueue,
                         Map<ICreatable, Boolean> taskQueueSub,
                         Map<ICreatable, Button> buttonHashMap,
                         Map<Button, TextArea> dayHashMap,
                         Map<ICreatable, DaysOfTheWeek> textAreaHashMap,
                         Map<ICreatable, Button> creatableHashMap,
                         Map<Button, Boolean> emptySpotsHashMap) {
    //FOR DISPLAY
    this.buttonHashMap = buttonHashMap;

    //FOR FUNCTION
//    this.stage = stage;
    this.setEventLimit = setEventLimit;
    this.setTaskLimit = setTaskLimit;
    this.dayHashMap = dayHashMap;
    this.textAreaHashMap = textAreaHashMap;
    this.creatableHashMap = creatableHashMap;
    this.emptySpotsHashMap = emptySpotsHashMap;
    this.countForTasks = countForTasks;
    this.countForEvents = countForEvents;
    this.tasksCompleted = tasksCompleted;
    this.taskQueue = taskQueue;
    this.taskQueueSub = taskQueueSub;
  }

  public StringBuilder getStringBuilder() {
    return this.stringBuilder;
  }

}
