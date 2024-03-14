package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DirectoryPopupButtons {

  @FXML
  private Scene scene;

  @FXML
  private TextArea directoryTextArea;

  @FXML
  private Button directoryBack;

  @FXML
  private Button directoryEnter;

  public DirectoryPopupButtons(Scene scene) {
    this.scene = scene;
    this.directoryTextArea = (TextArea) this.scene.lookup("#DirectoryTextArea");
    this.directoryBack = (Button) this.scene.lookup("#DirectoryBack");
    this.directoryEnter = (Button) this.scene.lookup("#DirectoryEnter");
  }

  public TextArea getDirectoryTextArea() {
    return this.directoryTextArea;
  }

  public Button getDirectoryBack() {
    return this.directoryBack;
  }

  public Button getDirectoryEnter() {
    return this.directoryEnter;
  }

}
