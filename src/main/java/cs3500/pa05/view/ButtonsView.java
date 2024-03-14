package cs3500.pa05.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;


/**
 * Class representing the Buttons View
 */
public class ButtonsView {
  @FXML
  private Rectangle weekBackground;
  @FXML
  private Scene scene;
  @FXML
  private Button sunday;
  @FXML
  private Button monday;
  @FXML
  private Button tuesday;
  @FXML
  private Button wednesday;
  @FXML
  private Button thursday;
  @FXML
  private Button friday;
  @FXML
  private Button saturday;
  @FXML
  private Button clickMe;
  @FXML
  private Node saveTo;
  @FXML
  private Node open;
  @FXML
  private TextArea mondayText;
  @FXML
  private TextArea tuesdayText;
  @FXML
  private TextArea wednesdayText;
  @FXML
  private TextArea thursdayText;
  @FXML
  private TextArea fridayText;
  @FXML
  private TextArea saturdayText;
  @FXML
  private TextArea sundayText;
  @FXML
  private Button newTask;

  @FXML
  private Button newEvent;

  @FXML
  private Button saveToBujo;
  @FXML
  private Button SetYourLimit;
  @FXML
  private Button miniViewer;
  @FXML
  private TextArea tasksSidebar;
  @FXML
  private BorderPane maxBorderPane;
  @FXML
  private TextArea weekTitle;
  @FXML
  private Button CreateAQuote;
  @FXML
  private TextArea NotesTextArea;
  @FXML
  private Label journalTitle;
  @FXML
  private ChoiceBox theme;
  @FXML
  private Rectangle titleRec;

  @FXML
  private Rectangle mondayRec;

  @FXML
  private ImageView mondayImageView;

  @FXML
  private Rectangle tuesRec;

  @FXML
  private ImageView tuesdayImageView;


  @FXML
  private Rectangle wedRec;

  @FXML
  private ImageView wedImageView; //wedmageView

  @FXML
  private Rectangle thursRec;

  @FXML
  private ImageView thursImageView;

  @FXML
  private Rectangle fridayRec;

  @FXML
  private ImageView friImageView;

  @FXML
  private Rectangle satRec;

  @FXML
  private ImageView satImageView;

  @FXML
  private Rectangle sunRec;

  @FXML
  private ImageView sunImageView;





  /**
   * Constructor for the Buttons View CLass
   * @param scene A scene
   */
  public ButtonsView(Scene scene) {
    this.scene = scene;
    this.sunday = (Button) this.scene.lookup("#Sunday");
    this.monday = (Button) this.scene.lookup("#Monday");
    this.tuesday = (Button) this.scene.lookup("#Tuesday");
    this.wednesday = (Button) this.scene.lookup("#Wednesday");
    this.thursday = (Button) this.scene.lookup("#Thursday");
    this.friday = (Button) this.scene.lookup("#Friday");
    this.saturday = (Button) this.scene.lookup("#Saturday");
    this.clickMe = (Button) this.scene.lookup("#ClickMe");
    this.miniViewer = (Button) this.scene.lookup("#MiniViewer");
    this.mondayText = (TextArea) this.scene.lookup("#MondayText");
    this.tuesdayText = (TextArea) this.scene.lookup("#TuesdayText");
    this.wednesdayText = (TextArea) this.scene.lookup("#WednesdayText");
    this.thursdayText = (TextArea) this.scene.lookup("#ThursdayText");
    this.fridayText = (TextArea) this.scene.lookup("#FridayText");
    this.saturdayText = (TextArea) this.scene.lookup("#SaturdayText");
    this.sundayText = (TextArea) this.scene.lookup("#SundayText");
    this.SetYourLimit = (Button) this.scene.lookup("#SetYourLimit");
    this.saveTo = (Node) this.scene.lookup("#SaveTo");
    this.open = (Node) this.scene.lookup("#Open");
    this.tasksSidebar = (TextArea) this.scene.lookup("#Tasks");
    this.maxBorderPane = (BorderPane) this.scene.lookup("#maxBorderPane");
    this.weekTitle = (TextArea) this.scene.lookup("#WeekTitle");
    this.NotesTextArea = (TextArea) this.scene.lookup("#NotesTextArea");
    this.CreateAQuote = (Button) this.scene.lookup("#CreateAQuote");
    this.weekBackground = (Rectangle) this.scene.lookup("#weekBackground");
    this.journalTitle = (Label) this.scene.lookup("#titleLabel");

    this.titleRec = (Rectangle) this.scene.lookup("#titleRec");
    this.mondayRec = (Rectangle) this.scene.lookup("#mondayRec");

    this.mondayImageView = (ImageView) this.scene.lookup("mondayImageView");
    this.tuesRec = (Rectangle) this.scene.lookup("tuesRec");
    this.wedRec = (Rectangle) this.scene.lookup("wedRec");
    this.thursRec = (Rectangle) this.scene.lookup("thursRec");
    this.fridayRec = (Rectangle) this.scene.lookup("fridayRec");
    this.satRec = (Rectangle) this.scene.lookup("satRec");
    this.sunRec = (Rectangle) this.scene.lookup("sunRec");

    this.tuesdayImageView = (ImageView) this.scene.lookup("tuesdayImageView");

    this.wedImageView = (ImageView) this.scene.lookup("wedmageView");
    this.thursImageView = (ImageView) this.scene.lookup("thursImageView");
    this.friImageView = (ImageView) this.scene.lookup("friImageView");
    this.satImageView = (ImageView) this.scene.lookup("satImageView");
    this.sunImageView = (ImageView) this.scene.lookup("sunImageView");
    this.theme = (ChoiceBox) this.scene.lookup("#themeChoice");
  }

  public Rectangle getTitleRec() {
    return this.titleRec;
  }
  public Rectangle getMondayRec() {
    return this.mondayRec;
  }


  public ChoiceBox getTheme() {
    return this.theme;
  }


  public Label getJournalTitle() {
    return this.journalTitle;
  }

  public Rectangle getWeekBackground() {
    return this.weekBackground;
  }

  /**
   *
   * @return A button for Sunday
   */
  public Button getSunday() {
    return this.sunday;
  }

  /**
   *
   * @return A button for monday
   */
  public Button getMonday() {
    return this.monday;
  }
  /**
   *
   * @return A button for tuesday
   */
  public Button getTuesday() {
    return this.tuesday;
  }

  /**
   *
   * @return A button for wednesday
   */
  public Button getWednesday() {
    return this.wednesday;
  }

  /**
   *
   * @return A button for thursday
   */
  public Button getThursday() {
    return this.thursday;
  }

  /**
   *
   * @return A button for Friday
   */
  public Button getFriday() {
    return this.friday;
  }

  /**
   *
   * @return A button for Saturday
   */
  public Button getSaturday() {
    return this.saturday;
  }

  /**
   *
   * @return A button for Click Me
   */
  public Button getClickMe() {
    return this.clickMe;
  }
  public Button getMiniViewer() { return this.miniViewer; }
  public Button getSetYourLimit() {
    return this.SetYourLimit;
  }
  public TextArea getMondayText() { return this.mondayText; }
  public TextArea getTuesdayText() { return this.tuesdayText; }
  public TextArea getWednesdayText() { return this.wednesdayText; }
  public TextArea getThursdayText() { return this.thursdayText; }
  public TextArea getFridayText() { return this.fridayText; }
  public TextArea getSaturdayText() { return this.saturdayText; }
  public TextArea getSundayText() { return this.sundayText; }
  public Node getSaveTo() { return this.saveTo; }
  public Node getOpen() { return this.open; }
  public Scene getScene() {
    return this.scene;
  }
  public void prepChoices() {
    this.theme.setItems(FXCollections.observableArrayList("OODNow", "OODBeg", "OODMid"));
  }


  public TextArea getTasksSidebar(){ return this.tasksSidebar; }
  public BorderPane getMaxBorderPane() { return this.maxBorderPane; }
  public TextArea getWeekTitle() {
    return this.weekTitle;
  }
  public TextArea getDailyQuotes() { return this.NotesTextArea; }
  public Button getCreateAQuote() { return this.CreateAQuote; }

}
