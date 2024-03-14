package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PopupButtonsView {
  @FXML
  private ColorPicker titleTextColorPicker;
  @FXML
  private ColorPicker borderColorPicker;
  @FXML
  private ColorPicker buttonColorPicker;
  @FXML
  private  ColorPicker backgroundColorPicker;
  @FXML
  private  Button open;
  @FXML
  private  Button doneColor;
  @FXML
  private Scene scene;
  @FXML
  private Button maximumDone;
  @FXML
  private Button saveLimits;
  @FXML
  private Button understandButton;
  @FXML
  private TextArea numOfEvents;
  @FXML
  private TextArea numOfTasks;
  @FXML
  private Button weeklyOverview;
  @FXML
  private TextArea totalTasksNum;
  @FXML
  private TextArea totalEventsNum;
  @FXML
  private TextArea percentTaskCompleteNum;
  @FXML
  private Button SaveWeekName;
  @FXML
  private Button DoneReading;
  @FXML
  private  TextArea EnterQuoteOfDayArea;
  @FXML
  private Button exitButton;
  @FXML
  private Button saveWeekButton;
  @FXML
  private Button saveQuote;
  @FXML
  private Button DoneWithQuote;
  @FXML
  private TextField saveName;
  @FXML
  private Button OkayTime;
  @FXML
  private Button OkayDuration;

  /**
   * Represents the popup Buttons view constructios
   * @param scene A scene
   */
  public PopupButtonsView(Scene scene) {
    this.scene = scene;


    this.numOfEvents = (TextArea) this.scene.lookup("#MaxEvents");
    this.numOfTasks = (TextArea) this.scene.lookup("#MaxTasks");
    this.maximumDone = (Button) this.scene.lookup("#maximumDone");
    this.saveLimits = (Button) this.scene.lookup("#SaveLimits");

    this.numOfEvents = (TextArea) this.scene.lookup("#MaxEvents");
    this.numOfTasks = (TextArea) this.scene.lookup("#MaxTasks");
    this.maximumDone = (Button) this.scene.lookup("#maximumDone");
    this.saveLimits = (Button) this.scene.lookup("#SaveLimits");
    this.understandButton = (Button) this.scene.lookup("#Iunderstand");
    this.weeklyOverview = (Button) this.scene.lookup("#WeeklyOverview");
    this.totalTasksNum = (TextArea) this.scene.lookup ("#Totaltasks");
     this.totalEventsNum = (TextArea) this.scene.lookup ("#Totalevents");
     this.percentTaskCompleteNum = (TextArea) this.scene.lookup("#Percentcomplete");

    this.SaveWeekName = (Button) this.scene.lookup("#SaveWeekName");
    this.DoneReading = (Button) this.scene.lookup("#DoneReading");

    this.exitButton = (Button) this.scene.lookup("#ExitButton");

    this.saveWeekButton = (Button) this.scene.lookup("#SaveWeekName");
    this.saveName = (TextField) this.scene.lookup("#NameTheWeek");

    this.saveQuote = (Button) this.scene.lookup("#SaveQuoteOfDay");
    this.DoneWithQuote = (Button) this.scene.lookup("#DoneWithQuote");
    this.EnterQuoteOfDayArea = (TextArea) this.scene.lookup("#EnterQuoteOfDayArea");
    this.OkayTime = (Button) this.scene.lookup("#OkayTime");
    this.OkayDuration = (Button) this.scene.lookup("#OkayDuration");
    this.doneColor = (Button) this.scene.lookup("#doneColor");
    this.open = (Button) this.scene.lookup("#colorButton");

    this.backgroundColorPicker = (ColorPicker) this.scene.lookup("#background"); // the main screen background
    this.buttonColorPicker = (ColorPicker) this.scene.lookup("#button"); // the button changer in the pop up
    this.borderColorPicker = (ColorPicker) this.scene.lookup("#boarder"); // the color of the boarder
    this.titleTextColorPicker = (ColorPicker) this.scene.lookup("#titleText"); // the color of the title of the txt



  }
  /**
   * Handles getting the weekly overview button
   * @return The weekly FIX THIS button
   */
  public Button getOkayTime() {
    return this.OkayTime;
  }

  public Button getDoneColor() {
    return this.doneColor;
  }
  public Button getOpenButton() {
    return this.open;
  }

  /**
   * Handles getting the weekly overview button
   * @return The weekly overview button
   */
  public Button getOkayDuration() {
    return this.OkayDuration;
  }
  /**
  /**
   * Handles getting the weekly overview button
   * @return The weekly overview button
   */
  public Button getWeeklyOverview() {
    return this.weeklyOverview;
  }
  /**
   * Handles getting the number of total tasks text area
   * @return The total number of tasks text area
   */
  public TextArea getTasksNumTextArea() {
    return this.totalTasksNum;
  }
  /**
   * Handles getting the number of total event text area
   * @return The total number of event text area
   */
  public TextArea getEventsNumTextArea() {
    return this.totalEventsNum;
  }
  /**
   * Handles getting the number of percentage of tasks completed text area
   * @return The text area that holds the percent of tasks completed
   */
  public TextArea getPercentTextArea() {
    return this.percentTaskCompleteNum;
  }
  /**
   * Handles getting the get the quote of the day text area
   * @return The text area that holds the quote of the day
   */
  public TextArea getEnterQuoteOfDayArea() {
    return this.EnterQuoteOfDayArea;
  }
  /**
   * Handles getting the button that happens when the user is done with the quote of day pop up
   * @return The done button that is clicked
   */
  public Button getDoneWithQuote() {
    return this.DoneWithQuote;
  }
  /**
   * Handles getting the done button that happens when the user enters their to save their quote
   * @return The save button that is clicked
   */
  public Button getSaveQuote() {
    return this.saveQuote;
  }
  /**
   * Handles getting the button that happens when the user wants to click done after putting in
   * the max number quote.
   * @return The done button that is clicked
   */
  public Button getMaximumDone(){ return this.maximumDone; }

  /**
   * Handles getting the button that save the limits.
   * @return the save limits button
   */
  public Button getSaveLimits() { return this.saveLimits; }

  /**
   * Handles getting the button when they have finished reading the intro screen
   * and want to exit
   * @return a button of "I understand"
   */
  public Button getUnderstandButton() { return this.understandButton; }

  /**
   * Handles getting the button when they have finished reading
   * and want to exit
   * @return The exit button
   */
  public Button getExitButton() {
    return this.exitButton;
  }
  /**
   * Handles getting the text field in the pop-up where name is typed.
   * @return The save Name button
   */
  public TextField getNameTheWeek() {
    return this.saveName;
  }
  /**
   * Handles getting the button when they have finished reading the intro screen
   * and want to exit
   * @return Done reading button
   */
  public Button getDoneReading() {
    return this.DoneReading;
  }
  /**
   * Handles getting the button that saves the name of the week to the main scree
   * @return Save week nae button
   */
  public Button getSaveWeekName() { return this.SaveWeekName; }
  /**
   * Handles getting the text area that user enters the number of events in
   * @return A Text area
   */
  public TextArea getNumOfEvents() { return this.numOfEvents; }
  /**
   * Handles getting the text area that user enters the number of tasks in
   * @return A Text area
   */

  public TextArea getNumOfTasks() { return this.numOfTasks; }
  public ColorPicker getBackgroundColorPicker() {
    return this.backgroundColorPicker;
  }
  public ColorPicker getBorderColorPicker() {
    return this.borderColorPicker;
  }
  public ColorPicker getButtonColorPicker() {
    return this.buttonColorPicker;
  }
  public ColorPicker getTitleTextColorPicker() {
    return this.titleTextColorPicker;
  }

}
