package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Class that gets the fx-ids of content in the popup that asks for a new task or new event
 */
public class TaskEventParentPopup {
  @FXML
  private Scene scene;
  @FXML
  private Button newTask;
  @FXML
  private Button newEvent;
  @FXML
  private Button backButton;

  /**
   * A constructor for the Task event parent popup
   * @param scene A scene
   */
  public TaskEventParentPopup(Scene scene) {
    this.scene = scene;
    this.newTask = (Button) this.scene.lookup("#NewTask");
    this.newEvent = (Button) this.scene.lookup("#NewEvent");
    this.backButton = (Button) this.scene.lookup("#BackButton");
  }


  /**
   * Handles getting the button that creates a new task.
   * @return new task button
   */
  @FXML
  public Button getNewTask() {
    return this.newTask;
  }
  /**
   * Handles getting the button that creates a new event.
   * @return the new event button
   */
  @FXML
  public Button getNewEvent() {
    return this.newEvent;
  }
  /**
   * Handles getting the button that goes back.
   * @return the back button
   */
  @FXML
  public Button getBackButton() {
    return this.backButton;
  }


}
