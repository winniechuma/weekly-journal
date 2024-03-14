package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EventPopupButtons {
  @FXML
  public Scene scene;

  @FXML
  private Button eventDone;

  @FXML
  private Button eventSave;

  @FXML
  private TextArea durationEvent;

  @FXML
  private TextArea timeEvent;

  @FXML
  private TextArea descriptionEvent;

  @FXML
  private TextArea nameEvent;

  public EventPopupButtons(Scene scene) {
    this.scene = scene;
    this.nameEvent = (TextArea) this.scene.lookup("#NameEvent");
    this.durationEvent = (TextArea) this.scene.lookup("#DurationText");
    this.timeEvent = (TextArea) this.scene.lookup("#TimeText");
    this.descriptionEvent = (TextArea) this.scene.lookup("#DescriptionEvent");
    this.eventSave = (Button) this.scene.lookup("#EventSave");
    this.eventDone = (Button) this.scene.lookup("#EventDone");
  }

  /**
   * gets the event save button
   * @return a button
   */
  @FXML
  public Button getEventSave() { return this.eventSave; }
  /**
   * gets the event done button
   * @return a button
   */
  @FXML
  public Button getEventDone() { return this.eventDone; }
  /**
   * gets the event name button
   * @return a button
   */
  @FXML
  public TextArea getNameEvent() { return this.nameEvent; }
  /**
   * gets the description event  button
   * @return a button
   */
  @FXML
  public TextArea getDescriptionEvent() { return this.descriptionEvent; }
  /**
   * gets the duration of this event
   * @return a button
   */
  @FXML
  public TextArea getDurationEvent() { return this.durationEvent; }
  /**
   * gets the time of this event
   * @return a button
   */
  @FXML
  public TextArea getTimeEvent() { return this.timeEvent; }

}
