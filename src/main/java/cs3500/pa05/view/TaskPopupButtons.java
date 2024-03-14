package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

/**
 * The class that represents the task pop up buttons
 */
public class TaskPopupButtons {

  @FXML
  private Scene scene;
  @FXML
  private TextArea nameTask;
  @FXML
  private TextArea descriptionTask;
  @FXML
  private CheckBox complete;
  @FXML
  private Button taskDone;
  @FXML
  private Button taskSave;

  /**
   * The constructor for the task popup buttons
   * @param scene A scene
   */
  public TaskPopupButtons(Scene scene) {
    this.scene = scene;
    this.nameTask = (TextArea) this.scene.lookup("#NameText");
    this.descriptionTask = (TextArea) this.scene.lookup("#DescriptionTask");
    this.complete = (CheckBox) this.scene.lookup("#CheckTask");
    this.taskDone = (Button) this.scene.lookup("#TaskDone");
    this.taskSave = (Button) this.scene.lookup("#TaskSave");
  }

  /**
   * Handles getting the button that saves the task
   * @return the task save button
   */
  public Button getTaskSave() { return this.taskSave; }
  /**
   * Handles getting the button that is done on the task popup.
   * @return the task done button
   */
  public Button getTaskDone() { return this.taskDone; }
  /**
   * Handles getting the text area that names the task.
   * @return the name task text area
   */
  public TextArea getNameTask() { return this.nameTask; }
  /**
   * Handles getting the text area that describes the task.
   * @return the description task text area
   */
  public TextArea getDescriptionTask() { return this.descriptionTask; }
  /**
   * Handles getting the checkbox that states whether this task is complete.
   * @return complete checkbox
   */
  public CheckBox getCompleteBox() { return this.complete; }

}

