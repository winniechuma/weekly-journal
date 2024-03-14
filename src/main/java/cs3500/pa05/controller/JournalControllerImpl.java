package cs3500.pa05.controller;

import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.EventClass;
import cs3500.pa05.model.ICreatable;
import cs3500.pa05.model.StoreWeekToBujo;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.ButtonsView;
import cs3500.pa05.view.DirectoryPopupButtons;
import cs3500.pa05.view.EventPopupButtons;
import cs3500.pa05.view.MiniViewerPopup;
import cs3500.pa05.view.PopupButtonsView;
import cs3500.pa05.view.TaskEventParentPopup;
import cs3500.pa05.view.TaskPopupButtons;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * The class representing a Journal Controller
 */
public class JournalControllerImpl implements JournalController {
  @FXML
  private ChoiceBox themeChoice;
  @FXML
  private ButtonsView buttonsView;
  @FXML
  private Popup popup;
  @FXML
  private Button clickMe;
  @FXML
  private Stage stage;
  @FXML
  private EventHandler buttonEventHandler;
  @FXML
  private Scene scene;
  @FXML
  private Button SetYourLimit;
  @FXML
  private Button enterCheckIt;
  private int countForTasks = 0;
  private int countForEvents = 0;
  private int setEventLimit;
  private int setTaskLimit;
  private int tasksCompleted = 0;
  private Boolean isValid;
  private Boolean isPopup = false;
  private Boolean isChildPopup = false;
  private Map<ICreatable, Boolean> taskQueue = new HashMap();
  private Map<ICreatable, Boolean> taskQueueSub = new HashMap<>();
  private Map<ICreatable, Button> buttonHashMap = new HashMap<>();
  private Map<Button, TextArea> dayHashMap = new HashMap<>();
  private Map<ICreatable, DaysOfTheWeek> textAreaHashMap = new HashMap<>();
  private Map<ICreatable, Button> creatableHashMap = new HashMap<>();
  private Map<Button, Boolean> emptySpotsHashMap = new HashMap<>();
  private Button monday, tuesday, wednesday, thurs, fri, sat, sun;
  @FXML
  Label titleText;
  @FXML
  private Rectangle weekBackground;
  private StoreWeekToBujo storeWeekToBujo;


  /**
   * @param stage A Stage
   *              Constructor for the journal Controller class
   */
  public JournalControllerImpl(Stage stage) {
    this.stage = stage;
    this.isValid = true;
    this.clickMe = new Button();
    this.SetYourLimit = new Button();
    this.storeWeekToBujo = new StoreWeekToBujo();
  }

  /**
   * Alternative constructor for when bujo save file is loaded
   *
   * @param stage             the stage where the file is being loaded
   * @param countForTasks     the count of the tasks created
   * @param countForEvents    the count of the events created
   * @param setEventLimit     the limit set for the number of events
   * @param setTaskLimit      the limit set for the number of tasks
   * @param tasksCompleted    the number of tasks that were completed
   * @param taskQueue         the queue of created tasks
   * @param taskQueueSub      the substitute of the queue of created tasks
   * @param buttonHashMap     the hashMap that connects buttons to their Created events/tasks
   * @param dayHashMap        the day associated with each textArea
   * @param textAreaHashMap   the day associated with each button in the mini viewer
   * @param creatableHashMap  the event/task associated with each button in the mini viewer
   * @param emptySpotsHashMap the button associated with each available spot in the mini viewer
   *                          (true/false)
   */

  public JournalControllerImpl(Stage stage, int countForTasks, int countForEvents,
                               int setEventLimit, int setTaskLimit, int tasksCompleted,
                               Map<ICreatable, Boolean> taskQueue,
                               Map<ICreatable, Boolean> taskQueueSub,
                               Map<ICreatable, Button> buttonHashMap,
                               Map<Button, TextArea> dayHashMap,
                               Map<ICreatable, DaysOfTheWeek> textAreaHashMap,
                               Map<ICreatable, Button> creatableHashMap,
                               Map<Button, Boolean> emptySpotsHashMap) {
    this.stage = stage;
    this.isValid = true;
    this.clickMe = new Button();
    this.SetYourLimit = new Button();
    this.countForTasks = countForTasks;
    this.countForEvents = countForEvents;
    this.setEventLimit = setEventLimit;
    this.setTaskLimit = setTaskLimit;
    this.tasksCompleted = tasksCompleted;
    this.taskQueue = taskQueue;
    this.taskQueueSub = taskQueueSub;
    this.buttonHashMap = buttonHashMap;
    this.dayHashMap = dayHashMap;
    this.textAreaHashMap = textAreaHashMap;
    this.creatableHashMap = creatableHashMap;
    this.emptySpotsHashMap = emptySpotsHashMap;

  }

  /**
   * Method that runs the intro fxml, which awaits the button click for the main program
   */
  public void runIntro() {
    this.enterCheckIt.setOnAction(e -> startWeek());
  }

  /**
   * Starts the week.fxml (the actual program) once the enter button is clicked
   */
  public void startWeek() {
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("Week.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      this.stage.setScene(s);
      this.stage.show();
      this.buttonsView = new ButtonsView(s);
      run();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Unable to load GUI.");
    }
  }

  /**
   * The method that runs all methods in this class when called in the driver
   */
  @FXML
  @Override
  public void run() {
    //ButtonsView -> all buttons in the Week.fxml
    this.buttonsView.getMonday().setOnAction(e -> makePopup(this.buttonsView.getMondayText(),
        DaysOfTheWeek.MONDAY));
    this.buttonsView.getTuesday().setOnAction(e -> makePopup(this.buttonsView.getTuesdayText(),
        DaysOfTheWeek.TUESDAY));
    this.buttonsView.getWednesday()
        .setOnAction(e -> makePopup(this.buttonsView.getWednesdayText(),
            DaysOfTheWeek.WEDNESDAY));
    this.buttonsView.getThursday().setOnAction(e -> makePopup(this.buttonsView.getThursdayText(),
        DaysOfTheWeek.THURSDAY));
    this.buttonsView.getFriday().setOnAction(e -> makePopup(this.buttonsView.getFridayText(),
        DaysOfTheWeek.FRIDAY));
    this.buttonsView.getSaturday().setOnAction(e -> makePopup(this.buttonsView.getSaturdayText(),
        DaysOfTheWeek.SATURDAY));
    this.buttonsView.getSunday().setOnAction(e -> makePopup(this.buttonsView.getSundayText(),
        DaysOfTheWeek.SUNDAY));
    this.buttonsView.getClickMe().setOnAction(e -> makeCheckItPopup());
    this.buttonsView.getMiniViewer().setOnAction(e -> makeMiniViewer());
    this.buttonsView.getSetYourLimit().setOnAction(e -> makeMaxPopup());
//      this.buttonsView.getMenuBar().setOnMousePressed(e -> fileOptions());
    this.buttonsView.getCreateAQuote().setOnAction(e -> makeQuotePopup());
    this.buttonsView.getSaveTo().setOnMousePressed(e -> saveFile());
    this.buttonsView.getOpen().setOnMousePressed(e -> openFile());
    this.buttonsView.getTheme().setOnMousePressed(e -> changeTheme());
  }

  /**
   * Gets all buttons connected to a task or event
   */
  @FXML
  private void clickableButtonCreatables() {
    for (Map.Entry<ICreatable, Button> inMap : this.buttonHashMap.entrySet()) {
      if (this.emptySpotsHashMap.containsKey(inMap.getValue())) {
        //gets the button + ICreatable
        Button thisButton = inMap.getValue();
        ICreatable creatable = inMap.getKey();
        //Gets the day associated with that creatable
        TextArea associatedDay = this.dayHashMap.get(thisButton);
        //Once this button is clicked in the miniViewer, calls MiniViewer
        thisButton.setOnAction(e -> miniViewer(creatable, associatedDay));
      }
    }
  }

  /**
   * If the button is clicked, opens the task or event popup
   *
   * @param creatable a task or event
   * @param txt       the text area associated with that task or event
   */
  private void miniViewer(ICreatable creatable, TextArea txt) {
    if (creatable.makeEditable().equals("task")) {
      makeTaskPopup(txt, creatable.getDay(), true, creatable);
    } else {
      makeEventPopup(txt, creatable.getDay(), true, creatable);
    }
  }

  /**
   * Asks for the directory in which the bujo file will be saved
   */
  private void saveFile() {
    try {
      //create a new popup and load it
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("DirectoryScreen.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      //position the popup
      popup.setX(200);
      popup.setY(200);
      //once the enter button is clicked, try to load that directory
      DirectoryPopupButtons directoryPopupButtons = new DirectoryPopupButtons(s);
      Button b = directoryPopupButtons.getDirectoryEnter();
      b.setOnAction(e -> saveFileHelper(directoryPopupButtons));
      System.out.println("We are here");
      //once the back button is pressed, hide the popup
      Button t = directoryPopupButtons.getDirectoryBack();
      t.setOnAction(e -> hideChildPopup(popup));
      //prevent other children popup from showing
      this.isChildPopup = true;
      //show the popup
      popup.show(this.stage);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Failed to load popup");
    }
  }

  /**
   * SaveFileHelper that gives the StoreWeekToBujo field access to information that must be stored
   */
  private void saveFileHelper(DirectoryPopupButtons directoryPopupButtons) {
    System.out.println("we made it this far");
    this.storeWeekToBujo.storeFields(this.stage, this.countForTasks, this.countForEvents,
        this.setEventLimit, this.setTaskLimit, this.tasksCompleted, this.taskQueue,
        this.taskQueueSub,
        this.buttonHashMap, this.dayHashMap, this.textAreaHashMap, this.creatableHashMap,
        this.emptySpotsHashMap);
    this.storeWeekToBujo.setDirectoryToWrite(
        directoryPopupButtons.getDirectoryTextArea().getText());
    System.out.println("Competence only");
    this.storeWeekToBujo.handleSave();

  }

  /**
   * Asks for the bujo file that is desired to be opened
   */
  public void openFile() {
    try {
      //create a new popup and load it
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("DirectoryScreen.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      //position the popup
      popup.setX(400);
      popup.setY(400);
      //once the enter button is clicked, try to load that directory
      DirectoryPopupButtons directoryPopupButtons = new DirectoryPopupButtons(s);
      Button b = directoryPopupButtons.getDirectoryEnter();
      b.setOnAction(e -> openFileHelper(directoryPopupButtons));
      //once the back button is pressed, hide the popup
      Button t = directoryPopupButtons.getDirectoryBack();
      t.setOnAction(e -> hideChildPopup(popup));
      //prevent other children popup from showing
      this.isChildPopup = true;
      //show the
      // popup
      popup.show(this.stage);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Failed to load popup");
    }
  }

  /**
   * SaveFileHelper that gives the StoreWeekToBujo field access to information that must be stored
   */
  private void openFileHelper(DirectoryPopupButtons directoryPopupButtons) {
    try {
      System.out.println("we made it this far");
      //Create a BufferedReader to read the bujo file
      BufferedReader bufferedReader =
          new BufferedReader(
              new FileReader(directoryPopupButtons.getDirectoryTextArea().getText()));
      //Instantiate the string needed to read the bujo file
      String nextLine;
      int count = 0;
      this.storeWeekToBujo.storeFields(this.stage, this.countForTasks, this.countForEvents,
          this.setEventLimit, this.setTaskLimit, this.tasksCompleted, this.taskQueue,
          this.taskQueueSub,
          this.buttonHashMap, this.dayHashMap, this.textAreaHashMap, this.creatableHashMap,
          this.emptySpotsHashMap);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("This file was not found, please try again");
    }
  }

  /**
   * Handles creating the weekly overview popup
   */
  @FXML
  private void makeWeeklyOverviewPopup() {
    try {
      if (!this.isChildPopup) {
        this.isPopup = false;
        Popup popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("WeeklyOverview.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        //back button
        PopupButtonsView popupButtonsView = new PopupButtonsView(s);
        setPercentWeeklyOverview(popupButtonsView);
        Button b = popupButtonsView.getExitButton();
        b.setOnAction(e -> hideChildPopup(popup));
        popup.setX(650);
        popup.setY(100);
        this.isChildPopup = true;
        popup.show(this.stage);
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failedWeeklyOverview");
    }
  }


  /**
   * Handles creating the check it popup
   */
  @FXML
  private void makeCheckItPopup() {
    try {
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("CheckIt.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      PopupButtonsView popupButtonsView = new PopupButtonsView(s);
      Button weeklyView = popupButtonsView.getWeeklyOverview();
      weeklyView.setOnAction(e -> makeWeeklyOverviewPopup());
      Button saveNameButton = popupButtonsView.getSaveWeekName();
      saveNameButton.setOnAction(e -> handleSavingNameWeek(popupButtonsView, popup)); // come back here
      Button openButton = popupButtonsView.getOpenButton();
      openButton.setOnAction(e -> makeColorThemePopup());
      Button done = popupButtonsView.getDoneReading();
      done.setOnAction(e -> hideChildPopup(popup));
      popup.getContent().add(weeklyView);
      popup.setX(100);
      popup.setY(100);
      popup.show(this.stage);
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
      throw new IllegalStateException(e);
    }
  }

  /**
   * Handles making the color theme popup
   */
  private void makeColorThemePopup() {
    try {
      if (!this.isPopup) {
        this.popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("Theme.fxml"));
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        PopupButtonsView popupButtonsView = new PopupButtonsView(s);
        ColorPicker c = popupButtonsView.getBackgroundColorPicker(); // the background which is fine
        ColorPicker c2 = popupButtonsView.getBorderColorPicker(); // the boarder which is fine
        ColorPicker c3 = popupButtonsView.getButtonColorPicker(); // the #button
        ColorPicker c4 = popupButtonsView.getTitleTextColorPicker(); // the title on the main page
        c.setOnAction(e -> handleBackgroundColor(popupButtonsView) );// work
        c2.setOnAction(e -> handleBoarderColor(popupButtonsView) );// work
        c3.setOnAction(e -> handleButtonDaysColor(popupButtonsView) ); // changes the color of the button // error
        c4.setOnAction(e -> handleTextColor(popupButtonsView) ); // changes the color of the text // works fine now
        Button b = popupButtonsView.getDoneColor();
        b.setOnAction(e -> hidePopup(this.popup));
        this.popup.show(this.stage);
        this.isPopup = true;
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
    }
  }


  /**
   * Handles changing the theme itself
   */
  public void changeTheme() {
    this.themeChoice = this.buttonsView.getTheme();
    this.buttonsView.prepChoices();
    this.themeChoice.setOnAction(e -> {
      Object selectedTheme = this.themeChoice.getValue();
      if (selectedTheme != null) {
        this.weekBackground = buttonsView.getWeekBackground();
        if (selectedTheme.equals("OODNow")) {
          this.weekBackground.setFill(Color.PURPLE);
          this.buttonsView.getTitleRec().setFill(Color.PINK);
          this.buttonsView.getMondayRec().setFill(Color.ORANGE);
          this.buttonsView.getWeekBackground().setStroke(Color.YELLOW);
          Font font = new Font("Arial", 12);
          this.buttonsView.getMondayText().setFont(font);
          this.buttonsView.getClickMe().setStyle("-fx-background-color: DarkRed ;");
          System.out.println("here 20000" + this.buttonsView.toString());
        } else if (selectedTheme.equals("OODBeg")) {
          Font font = new Font("Times New Roman", 12);
          this.buttonsView.getWeekBackground().setStroke(Color.BLACK);
          this.weekBackground.setFill(Color.DARKGREY);
          this.buttonsView.getTitleRec().setFill(Color.DARKBLUE);
          this.buttonsView.getMondayRec().setFill(Color.BROWN);
          this.buttonsView.getMondayText().setFont(font);
          this.buttonsView.getClickMe().setStyle("-fx-background-color: Pink ;");
        } else {
          this.buttonsView.getWeekBackground().setStroke(Color.RED);
          this.weekBackground.setFill(Color.LIGHTBLUE);
          this.buttonsView.getTitleRec().setFill(Color.YELLOW);
          this.buttonsView.getMondayRec().setFill(Color.PURPLE);
          Font font = new Font("Trebuchet MS", 12);
          this.buttonsView.getMondayText().setFont(font);
          this.buttonsView.getClickMe().setStyle("-fx-background-color: Blue ;");
        }
      }
    });
  }

  /**
   * Handles the background color
   * @param popupButtonsView popupButtonsView
   */
  @FXML
  private void handleBackgroundColor(PopupButtonsView popupButtonsView) {
    this.weekBackground = buttonsView.getWeekBackground();
    this.weekBackground.setFill(popupButtonsView.getBackgroundColorPicker().getValue());
  }

  /**
   * Handles changing the Button Day colors
   */
  @FXML
  private void handleButtonDaysColor(PopupButtonsView popupButtonsView) {
    this.monday = this.buttonsView.getMonday();
    this.tuesday = this.buttonsView.getTuesday();
    this.wednesday = this.buttonsView.getWednesday();
    this.thurs = this.buttonsView.getThursday();
    this.fri = this.buttonsView.getFriday();
    this.sat = this.buttonsView.getSaturday();
    this.sun = this.buttonsView.getSunday();

    Color colorValue = popupButtonsView.getButtonColorPicker().getValue();
    int red = (int)(colorValue.getRed() * 255);
    int green = (int) (colorValue.getGreen() * 255);
    int  blue = (int) (colorValue.getBlue() * 255);
    int  alpha =  (int) (colorValue.getOpacity() * 255);

    String hexColor = String.format("#%02x%02x%02x%02x", red, green, blue, alpha);
    this.monday.setStyle("-fx-background-color: " + hexColor + ";");
    this.tuesday.setStyle("-fx-background-color: " + hexColor + ";");
    this.wednesday.setStyle("-fx-background-color: " + hexColor + ";");
    this.thurs.setStyle("-fx-background-color: " + hexColor + ";");
    this.fri.setStyle("-fx-background-color: " + hexColor + ";");
    this.sat.setStyle("-fx-background-color: " + hexColor + ";");
    this.sun.setStyle("-fx-background-color: " + hexColor + ";");
  }

  /**
   * Handles changing the border color
   */
  @FXML
  private void handleBoarderColor(PopupButtonsView popupButtonsView) {
    this.weekBackground = buttonsView.getWeekBackground();
    this.weekBackground.setStroke(popupButtonsView.getBorderColorPicker().getValue());
  }

  /**
   * Handles setting the text color
   */
  @FXML
  private void handleTextColor(PopupButtonsView popupButtonsView) {
    this.titleText = buttonsView.getJournalTitle();
    this.titleText.setTextFill(popupButtonsView.getTitleTextColorPicker().getValue());
    System.out.println(this.titleText);
  }

  /**
   * Handles Saving the name of the week that the user enters
   */
  @FXML
  private void handleSavingNameWeek(PopupButtonsView popupButtonsView, Popup popup) {
    System.out.println("handle was called");
    String nameEventInput = popupButtonsView.getNameTheWeek().getText();
    saveNameOfWeek(nameEventInput);
    popupButtonsView.getNameTheWeek().clear();
    this.storeWeekToBujo.setWeekName(nameEventInput);
  }

  /**
   * Handles saving the name of the week that the user enters but on the main screen
   */
  @FXML
  private void saveNameOfWeek(String weekNameInput) {
    TextArea weekName = this.buttonsView.getWeekTitle();
    if (!weekNameInput.isEmpty()) {
      String currentText = weekName.getText();
      String updatedText = currentText + " " + weekNameInput;
      weekName.setText(updatedText);
    }
  }

  /**
   * @param popupButtonsView A popupButtonView
   *                         Handles setting the percentage for the weekly overview popup
   */
  public void setPercentWeeklyOverview(PopupButtonsView popupButtonsView) {
    TextArea totalWeekEvents = popupButtonsView.getEventsNumTextArea();
    TextArea totalWeekTasks = popupButtonsView.getTasksNumTextArea();
    TextArea percentOfTask = popupButtonsView.getPercentTextArea();
    String events = String.valueOf(countForEvents);
    String tasks = String.valueOf(countForTasks);
    String percent = String.valueOf(percentOfTaskCompleted());
    totalWeekEvents.setText(events);
    totalWeekTasks.setText(tasks);
    percentOfTask.setText(percent);
  }


  /**
   * Handles creating the popup for the event and task on the same screen
   *
   * @param txt A text area
   * @param day The day of the week
   */
  @FXML
  private void makePopup(TextArea txt, DaysOfTheWeek day) {
    try {
      //Is there a current parent popup?
      if (!this.isPopup) {
        this.popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("handleBoth.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        //^^^ Loads the popup
        //Gets the popupButtonsView of this popup
        TaskEventParentPopup popupButtonsView = new TaskEventParentPopup(s);
        //new task button that creates a new task popup
        Button t = popupButtonsView.getNewTask();
        t.setOnAction(f -> makeTaskPopup(txt, day, false, null));
        //new event button that creates a new event popup
        Button m = popupButtonsView.getNewEvent();
        m.setOnAction(w -> makeEventPopup(txt, day, false, null));
        //back button which hides the popup
        Button b = popupButtonsView.getBackButton();
        b.setOnAction(e -> hidePopup(this.popup));
        this.popup.show(this.stage);
        //boolean that ensures that a parent popup is not created
        this.isPopup = true;
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
    }
  }

  /**
   * Handles making the popup for the mini viewer
   */
  @FXML
  private void makeMiniViewer() {
    if (!this.isPopup) {
      try {
        //create a new popup and load it
        this.popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("MiniViewer.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        //Create a MiniViewerPopup method
        MiniViewerPopup miniViewerPopup = new MiniViewerPopup(s);
        //get the gridpane
        GridPane gridPane = miniViewerPopup.getGridPane();
        //instantiate the initial column and row
        int col = 0;
        int row = 0;
        //adds every task and event button to the mini viewer
        for (Map.Entry<ICreatable, Button> inMap : this.buttonHashMap.entrySet()) {
          if (this.emptySpotsHashMap.containsKey(inMap.getValue())) {
            if (col >= gridPane.getColumnCount() && row >= gridPane.getRowCount()) {
              //if the maximum number of buttons is reached, do nothing
            } else if (col >= gridPane.getColumnCount()) {
              //if the maximum number of columns is reached, set it to 0 and add to columns
              col = 0;
              row++;
              gridPane.add(inMap.getValue(), col, row);
            } else {
              //if the maximum number of rows is reached, set it to 0 and add to columns
              row = 0;
              col++;
              gridPane.add(inMap.getValue(), col, row);
            }
          }
        }
        //adds the buttons to the miniviewer
        clickableButtonCreatables();
        //makes the back button exit the popup
        Button b = miniViewerPopup.getMiniViewerBackButton();
        b.setOnAction(e -> hidePopup(popup));
        //make the popup show
        popup.show(this.stage);
      } catch (IllegalStateException | IOException e) {
        System.out.println("Run failed");
      }
    }
  }

  /**
   * Handles making the popup to create a new event
   *
   * @param txt        A Textarea
   * @param day        A day of the week
   * @param overriding A boolean
   * @param event      An event
   */
  @FXML
  private void makeEventPopup(TextArea txt, DaysOfTheWeek day,
                              boolean overriding, ICreatable event) {
    try {
      //checks if the maximum number of events has been reached
      if (this.setEventLimit <= this.countForEvents && this.setEventLimit != 0) {
        //show the popup that blocks the user from adding more events
        checkMaximum(this.setTaskLimit, this.countForTasks);
      }
      //Otherwise, allow the user to create a new event
      else if (!this.isChildPopup) {
        //hide the parent popup (the one that allows you to create a new task and new event)
        this.popup.hide();
        this.isPopup = false;
        //create a new child popup, and load said popup
        Popup popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("event.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        //Get all the elements available in the make event popup
        EventPopupButtons eventPopupButtons = new EventPopupButtons(s);
        //hide the popup once the done button is clicked
        Button b = eventPopupButtons.getEventDone();
        b.setOnAction(e -> hideChildPopup(popup));
        //if this event is being edited
        if (overriding) {
          //modify all qualitied of the event (through eventPopupButtons)
          eventModification(event, eventPopupButtons);
          //save the buttons and events in the creatableHashMap
          Button p = eventPopupButtons.getEventSave();
          //Get the Button associated with the event being edited
          Button miniViewerButton = this.creatableHashMap.get(event);
          ///revise the name of the button to the new name
          miniViewerButton.setText(eventPopupButtons.getNameEvent().getText());
          //modify the events on all text areas in which it is shown
          p.setOnAction(e -> handleEventModification(eventPopupButtons, event,
              day, txt, miniViewerButton, popup));
        } else {
          //Once the save button is clicked, save this information to the day associated
          //and the event popup when it is opened in the mini viewer
          Button p = eventPopupButtons.getEventSave();
          p.setOnAction(e -> handleAddEventButton(eventPopupButtons, txt, popup, day));
        }
        //ensure that no other child popups appear
        this.isChildPopup = true;
        //show the popup
        popup.show(this.stage);
        //when editing, show the warning
        checkMaximum(this.setEventLimit, this.countForEvents);
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
    }
  }

  /**
   * Handles making the popup to create a new task
   *
   * @param txt        A Textarea
   * @param day        A day of the week
   * @param overriding A boolean
   */
  @FXML
  private void makeTaskPopup(TextArea txt, DaysOfTheWeek day,
                             boolean overriding, ICreatable task) {
    try {
      //checks if the maximum number of events has been reached
      if (this.setTaskLimit <= this.countForTasks && this.setTaskLimit != 0) {
        //shows the commitment warning popup before any more tasks are added
        checkMaximum(this.setTaskLimit, this.countForTasks);
      }
      //if no other child popups are present
      else if (!this.isChildPopup) {
        //hide the parent popup
        this.popup.hide();
        this.isPopup = false;
        //create a new popup and load it
        Popup popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("task.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        //get all the buttons for the task popup
        TaskPopupButtons taskPopupButtons = new TaskPopupButtons(s);
        //make sure the popup hides when the done button is pressed
        Button b = taskPopupButtons.getTaskDone();
        b.setOnAction(e -> hideChildPopup(popup));
        //if a current task is being edited
        if (overriding) {
          //modify the tasks
          taskModification(task, taskPopupButtons);
          //ensure that the save button saves the current state of the task
          Button p = taskPopupButtons.getTaskSave();
          Button miniViewerButton = this.creatableHashMap.get(task);
          p.setOnAction(e -> handleTaskModification(taskPopupButtons, task,
              day, txt, miniViewerButton, popup));
        } else {
          //If the task is new, save it as a new task
          Button p = taskPopupButtons.getTaskSave();
          p.setOnAction(e -> handleAddTaskButton(taskPopupButtons, txt, popup, day));
          popup.getContent().add(b);
        }
        this.isChildPopup = true;
        //show the popup
        popup.show(this.stage);
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
    }
  }


  /**
   * Handles making the popup for the maximum number of events and tasks
   */
  @FXML
  private void makeMaxPopup() {
    try {
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("Maximum.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      PopupButtonsView popupButtonsView = new PopupButtonsView(s);
      Button done = popupButtonsView.getMaximumDone();
      Button save = popupButtonsView.getSaveLimits();
      save.setOnAction(e -> setMaximum(popupButtonsView));
      done.setOnAction(e -> popup.hide());
      popup.getContent().add(done);
      popup.setX(100);
      popup.setY(100);
      popup.show(this.stage);
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failed");
    }
  }

  /**
   * Handles saving and setting the maximum tasks and events using user input
   *
   * @param popupButtonsView A popup button view
   */
  private void setMaximum(PopupButtonsView popupButtonsView) {
    String maxEvents = popupButtonsView.getNumOfEvents().getText();
    String maxTask = popupButtonsView.getNumOfTasks().getText();
    int eventMax;
    int taskMax;
    try {
      eventMax = Integer.parseInt(maxEvents);
      taskMax = Integer.parseInt(maxTask);
      this.setEventLimit = eventMax;
      this.setTaskLimit = taskMax;
      System.out.println(eventMax);
    } catch (NumberFormatException e) {
      popupButtonsView.getNumOfEvents().setText("");
      popupButtonsView.getNumOfTasks().setText("");
    }
  }


  /**
   * Handles calculating the tasks completed
   *
   * @return the percentage of tasks completed
   */
  public int percentOfTaskCompleted() {
    if (this.tasksCompleted == 0) {
      return 0;
    } else {
      return (this.tasksCompleted / this.countForTasks) * 100;
    }
  }


  /**
   * Handles making the popup for the maximum number of events and tasks
   */
  private void checkMaximum(int limit, int count) {
    //if the number of events creates is greater than the one they set then show a warning
    if (limit <= count && limit != 0) {
      try {
        Popup popup = new Popup();
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().
                getResource("CommitmentWarnings.fxml"));
        loader.setController(this);
        Scene s = loader.load();
        popup.getContent().add((Node) s.getRoot());
        PopupButtonsView popupButtonsView = new PopupButtonsView(s);
        Button t = popupButtonsView.getUnderstandButton();
        t.setOnAction(e -> popup.hide());
        //make the popup show
        popup.show(this.stage);
      } catch (IllegalStateException | IOException e) {
        System.out.println("Run failed");
      }

      // commitment warning
    }
  }

  /**
   * Handles making the quote popup
   */
  @FXML
  private void makeQuotePopup() {
    try {
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("Quote.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      PopupButtonsView popupButtonsView = new PopupButtonsView(s);
      Button saveQuote = popupButtonsView.getSaveQuote(); // Get the button that saves a quote
      Button done = popupButtonsView.getDoneWithQuote();
      saveQuote.setOnAction(e -> handleSavingDailyQuotes(popupButtonsView, popup));
      done.setOnAction(e -> popup.hide());
      //   popup.getContent().add(done);
      popup.setX(100);
      popup.setY(100);
      popup.show(this.stage);
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failedCheckIt");
    }
  }

  /**
   * Handles making the time warning popup
   */
  @FXML
  private void makeTimeWarningPopup() {
    try {
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("TimeWarning.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      PopupButtonsView popupButtonsView = new PopupButtonsView(s);
      Button okay = popupButtonsView.getOkayTime();
      okay.setOnAction(e -> popup.hide());
      //   popup.getContent().add(done);
      popup.setX(100);
      popup.setY(100);
      popup.show(this.stage);
    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failedCheckIt");
    }
  }


  /**
   * Handles making the Duration warning popup
   */
  @FXML
  private void makeDurationWarningPopup() {
    try {
      Popup popup = new Popup();
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("DurationWarning.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      popup.getContent().add((Node) s.getRoot());
      PopupButtonsView popupButtonsView = new PopupButtonsView(s);

      Button okay = popupButtonsView.getOkayDuration();
      okay.setOnAction(e -> popup.hide());
      //   popup.getContent().add(done);
      popup.setX(100);
      popup.setY(100);
      popup.show(this.stage);

    } catch (IllegalStateException | IOException e) {
      System.out.println("Run failedCheckIt");
    }
  }


  /**
   * Handles saying the Daily Quotes to the main screen
   * @param popupButtonsView popupButtonsView
   * @param popup A popup
   */
  @FXML
  private void handleSavingDailyQuotes(PopupButtonsView popupButtonsView, Popup popup) {
    System.out.println("handle was called saving Daily Quotes");
    String nameEventInput = popupButtonsView.getEnterQuoteOfDayArea().getText();
    getDailyQuote(nameEventInput);
    popupButtonsView.getEnterQuoteOfDayArea().clear();
  }

  @FXML
  private void getDailyQuote(String weekQuoteInput) {
    TextArea DailQuotes = this.buttonsView.getDailyQuotes(); // Daily quotes area On the Main Screen
    if (!weekQuoteInput.isEmpty()) { // if their is text in their then get the text
      String currentText = DailQuotes.getText();
      String updatedText = currentText + " " + weekQuoteInput + "\n";
      this.buttonsView.getDailyQuotes().setText(updatedText);
      //add to StoreWeekToBujo for saving
      this.storeWeekToBujo.setDailyQuotesTextArea(updatedText);
    }
  }

  /**
   * Modified all fields of the task popup based on what was saved
   *
   * @param task             the task being edited
   * @param taskPopupButtons the list of elements in a taskPopup
   */
  @FXML
  private void taskModification(ICreatable task, TaskPopupButtons taskPopupButtons) {
    taskPopupButtons.getNameTask().setText(task.getField(0));
    taskPopupButtons.getDescriptionTask().setText(task.getField(1));
    //Checks the boolean state of the checkbox based on the string true or false
    Boolean state;
    if (task.getField(2) == "true") {
      state = true;
    } else {
      state = false;
    }
    taskPopupButtons.getCompleteBox().setSelected(state);
  }


  /**
   * Creates a new task, modified it based on user input, and appends it to the
   * taskArea and the taskQueue
   *
   * @param taskPopupButtons the buttons contained in the taskPopup
   * @param txt              the TextArea associated with the task (the day)
   * @param popup            the popup
   * @param day              the day that the task is set in
   */
  @FXML
  private void handleAddTaskButton(TaskPopupButtons taskPopupButtons, TextArea txt,
                                   Popup popup, DaysOfTheWeek day) {
    //Modified all aspects of the task based on user input
    String nameTaskInput = taskPopupButtons.getNameTask().getText();
    String descriptionTaskInput = taskPopupButtons.getDescriptionTask().getText();
    Boolean isComplete = taskPopupButtons.getCompleteBox().isSelected();
    //Create a new task based on that input
    Task newTask = new Task(nameTaskInput, descriptionTaskInput, day, isComplete);
    //Creates a new button associated to the task to be put in the minibar
    Button taskButton = new Button(nameTaskInput + " " + isComplete);
    //adds the buttons and tasks and textAreas to their hashmaps respectively
    this.buttonHashMap.put(newTask, taskButton);
    this.dayHashMap.put(taskButton, txt);
    this.textAreaHashMap.put(newTask, day);
    this.creatableHashMap.put(newTask, taskButton);
    this.emptySpotsHashMap.put(taskButton, true);
    //adds this task to the task queue
    taskQueue.put(newTask, isComplete);
    taskQueueSub.put(newTask, isComplete);
    putInsideSideBar();
    //saves the changes
    saveChangesToThisDay(txt, day);
    taskPopupButtons.getNameTask().clear();
    taskPopupButtons.getDescriptionTask().clear();
    hideChildPopup(popup);
  }

  /**
   * Modifies a task that was already created
   *
   * @param taskPopupButtons access to the elements of the taskPopup
   * @param task             the task being edited
   * @param day              the day that task was set in
   * @param txt              the TextArea that task is being appended to
   * @param button           the Button associated with that task in the MiniViewer
   * @param popup            the taskPopup
   */
  @FXML
  private void handleTaskModification(TaskPopupButtons taskPopupButtons, ICreatable task,
                                      DaysOfTheWeek day, TextArea txt,
                                      Button button, Popup popup) {
    //Updates the contents of the task
    String nameTaskInput = taskPopupButtons.getNameTask().getText();
    String descriptionTaskInput = taskPopupButtons.getDescriptionTask().getText();
    Boolean isComplete = taskPopupButtons.getCompleteBox().isSelected();
    //removes the old task from all the hashMaps
    this.buttonHashMap.remove(task);
//    this.dayHashMap.remove(task);
    this.creatableHashMap.remove(task);
    this.textAreaHashMap.remove(task);
    //removes the old task from the taskQueue
    taskQueue.remove(task);
    taskQueueSub.remove(task);
    //creates a new task based on the updated user inpur
    task = new Task(nameTaskInput, descriptionTaskInput, day, isComplete);
    //adds the new task to the hashmaps
    this.buttonHashMap.put(task, button);
    this.dayHashMap.put(button, txt);
    this.creatableHashMap.put(task, button);
    this.textAreaHashMap.put(task, day);
    //adds the new task to the taskQueue
    taskQueue.put(task, isComplete);
    taskQueueSub.put(task, isComplete);
    //saves the changes of the task
    saveChangesToThisDay(txt, day);
    //update the sidebar
    putInsideSideBar();
    //hides the taskPopup
    hideChildPopup(popup);
  }

  /**
   * Modifies all textAreas in the event popup
   *
   * @param event             the event being passed in
   * @param eventPopupButtons the access to the properties in the event popup
   */
  @FXML
  private void eventModification(ICreatable event, EventPopupButtons eventPopupButtons) {
    eventPopupButtons.getNameEvent().setText(event.getField(0));
    eventPopupButtons.getDescriptionEvent().setText(event.getField(1));
    eventPopupButtons.getTimeEvent().setText(event.getField(2));
    eventPopupButtons.getDurationEvent().setText(event.getField(3));
  }

  /**
   * modified the text in the text areas and saves the event to all locations
   *
   * @param eventPopupButtons the proprties of the event popup
   * @param event             the event being changed
   * @param day               the day that the event is set in
   * @param txt               the Day TextArea that is being updated
   * @param button            the Button that is being modified in the miniViewer
   * @param popup             the popup that is currently present
   */
  @FXML
  private void handleEventModification(EventPopupButtons eventPopupButtons, ICreatable event,
                                       DaysOfTheWeek day, TextArea txt,
                                       Button button, Popup popup) {
    //Modified textAreas in the event popup
    String nameEventInput = eventPopupButtons.getNameEvent().getText();
    String descriptionEventInput = eventPopupButtons.getDescriptionEvent().getText();
    String durationEventInput = eventPopupButtons.getDurationEvent().getText();
    String timeEventInput = eventPopupButtons.getTimeEvent().getText();

    //removes the event from all current hashmaps that represent it
    this.buttonHashMap.remove(event);
//    this.dayHashMap.remove(event);
    this.creatableHashMap.remove(event);
    this.textAreaHashMap.remove(event);
    //creates a new event based on new input
    event = new EventClass(nameEventInput, descriptionEventInput, day,
        durationEventInput, timeEventInput);
    //puts these events in each hash map
    this.buttonHashMap.put(event, button);
    this.dayHashMap.put(button, txt);
    this.creatableHashMap.put(event, button);
    this.textAreaHashMap.put(event, day);
    //saves the changes to the Event popup
    saveChangesToThisDay(txt, day);
    //hide the child popup
    hideChildPopup(popup);
  }

  /**
   * Saves the changes of a new event to all locations
   *
   * @param eventPopupButtons the elements of the popup being accessed
   * @param txt               the TextArea being appended
   * @param popup             the popup that is currently shown
   * @param day               the day for the event
   */
  @FXML
  private void handleAddEventButton(EventPopupButtons eventPopupButtons, TextArea txt,
                                    Popup popup, DaysOfTheWeek day) {
    //gets all the information of the new event
    String nameEventInput = eventPopupButtons.getNameEvent().getText();
    String descriptionEventInput = eventPopupButtons.getDescriptionEvent().getText();
    String durationEventInput = eventPopupButtons.getDurationEvent().getText();
    String timeEventInput = eventPopupButtons.getTimeEvent().getText();
    int durationInput;
    try {
      durationInput = Integer.parseInt(durationEventInput);
      if (durationInput > 0) {
        if (timeEventInput.matches(
            "\\d{2}:\\d{2}")) {// Have to check this when the save button is clicked onthe vent hting..hmm
          EventClass newEvent = new EventClass(nameEventInput, descriptionEventInput, day,
              durationEventInput, timeEventInput);
          //create a new button from this event
          Button eventButton = new Button(nameEventInput + " " + timeEventInput);
          //puts this new button in every hashmap
          this.buttonHashMap.put(newEvent, eventButton);
          this.dayHashMap.put(eventButton, txt);
          this.creatableHashMap.put(newEvent, eventButton);
          this.emptySpotsHashMap.put(eventButton, true);
          this.textAreaHashMap.put(newEvent, day);
          //save the changes of this event
          saveChangesToThisDay(txt, day);
          //Clear all fields
          eventPopupButtons.getNameEvent().clear();
          eventPopupButtons.getDescriptionEvent().clear();
          eventPopupButtons.getDurationEvent().clear();
          eventPopupButtons.getTimeEvent().clear();
          //hide the child popup
          hideChildPopup(popup);
        } else {
          this.makeTimeWarningPopup();
        }
      } else {
        this.makeDurationWarningPopup();
      }
    } catch (NumberFormatException e) {
      this.makeDurationWarningPopup();
    }
  }


  /**
   * Saves the changes of the event
   *
   * @param txt the TextArea being appended
   */
  @FXML
  private void saveChangesToThisDay(TextArea txt, DaysOfTheWeek day) {
    //clear the textArea
    txt.clear();
    for (Map.Entry<ICreatable, DaysOfTheWeek> inMap : textAreaHashMap.entrySet()) {
      //as long as the name of the event is not empty, create a String representing this event
      ICreatable creatable = inMap.getKey();
      if (inMap.getValue().equals(day)) {
        if (creatable.isEvent() && !creatable.getField(0).isEmpty()) {
          String nameInput = creatable.getField(0);
          String descriptionInput = creatable.getField(1);
          String durationInput = creatable.getField(2);
          String timeInput = creatable.getField(3);
          String updatedText = nameInput + " " + descriptionInput + " " +
              durationInput + " " + timeInput + "\n";
          txt.appendText(updatedText);
          countForEvents++;
        } else {
          //Create a string representing this task
          String nameInput = creatable.getField(0);
          String descriptionInput = creatable.getField(1);
          //get the boolean completion status from the task queue
          // and convert the boolean to a String
          Boolean completeStatus = taskQueue.get(creatable);
          String completeStatusString;
          if (completeStatus) {
            completeStatusString = "Complete";
            tasksCompleted++;
          } else {
            completeStatusString = "Incomplete";
          }
          String updatedText = nameInput + " " + descriptionInput
              + " " + completeStatusString + "\n";
          //append the text to the TextArea of that day
          txt.appendText(updatedText);
          countForTasks++; // Count for tasks that the user creates
        }
      }
    }
    saveChangesOfDayToFile(txt, day);
  }

  /**
   * allows StoreWeekToBujo to save the changes of the day text area based on the day
   *
   * @param txt the TextArea being stored
   * @param day the day of the week that event/task takes place
   */
  private void saveChangesOfDayToFile(TextArea txt, DaysOfTheWeek day) {
    if (day.equals(DaysOfTheWeek.MONDAY)) {
      this.storeWeekToBujo.setMondayTextArea(txt.getText());
    } else if (day.equals(DaysOfTheWeek.TUESDAY)) {
      this.storeWeekToBujo.setTuesdayTextArea(txt.getText());
    } else if (day.equals(DaysOfTheWeek.WEDNESDAY)) {
      this.storeWeekToBujo.setWednesdayTextArea(txt.getText());
    } else if (day.equals(DaysOfTheWeek.THURSDAY)) {
      this.storeWeekToBujo.setThursdayTextArea(txt.getText());
    } else if (day.equals(DaysOfTheWeek.FRIDAY)) {
      this.storeWeekToBujo.setFridayTextArea(txt.getText());
    } else if (day.equals(DaysOfTheWeek.SATURDAY)) {
      this.storeWeekToBujo.setSaturdayTextArea(txt.getText());
    } else {
      this.storeWeekToBujo.setSundayTextArea(txt.getText());
    }
  }

  /**
   * Puts tasks into the sidebar
   */
  @FXML
  private void putInsideSideBar() {
    //Instantiates the name and sidebar, and completion status
    String name;
    TextArea sideBarText = this.buttonsView.getTasksSidebar();
    Boolean completeStatus;
    String completeStatusString;
    //clear the sidebar
    sideBarText.clear();
    for (Map.Entry<ICreatable, Boolean> inMap : taskQueue.entrySet()) {
      //get the name and completion status
      name = inMap.getKey().getField(0);
      completeStatus = inMap.getValue();
      //convert the boolean to a String
      if (completeStatus) {
        completeStatusString = "Complete";
        tasksCompleted++;
      } else {
        completeStatusString = "Incomplete";
      }
      //append the text to the side bar
      String outPut = "\n" + name + " " + completeStatusString;
      sideBarText.appendText(outPut);
      //add to StoreWeekToBujo for saving
      this.storeWeekToBujo.setTaskQueueTextArea(sideBarText.getText());

    }
  }


  /**
   * Hides the popup and sets isPopup to false
   *
   * @param popup The popup passed in
   */
  private void hidePopup(Popup popup) {
    this.isPopup = false;
    popup.hide();
  }

  /**
   * Hides the popup and sets isChildPopup to false
   *
   * @param popup The popup passed in
   */
  private void hideChildPopup(Popup popup) {
    this.isChildPopup = false;
    popup.hide();
  }

  /**
   * Sets the scene, creates a buttonEventHandler for the Week.fxml, and accesses the button
   * on the splash screen
   *
   * @param scene the scene passed in from the driver
   */
  public void setScene(Scene scene) {
    this.scene = scene;
    this.buttonEventHandler = new ButtonEventHandler(new ButtonsView(scene));
    this.enterCheckIt = (Button) this.scene.lookup("#EnterCheckIt");
  }

}
