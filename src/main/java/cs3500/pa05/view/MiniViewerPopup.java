package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MiniViewerPopup {
  @FXML
  private Button miniViewerBackButton;
  @FXML
  private GridPane gridPane;
  @FXML
  private Scene scene;

  /**
   * Represents the mini viewer class popup
   * @param scene A scene
   */
  public MiniViewerPopup(Scene scene) {
    this.scene = scene;
    this.miniViewerBackButton = (Button) this.scene.lookup("#MiniViewerBack");
    this.gridPane = (GridPane) this.scene.lookup("#MiniViewerGridPane");
  }

  /**
   * Handles getting the back button of this mini viewer pop up
   * @return The back button
   */
  @FXML
  public Button getMiniViewerBackButton() { return this.miniViewerBackButton; }
  /**
   * Handles getting the grid pane button of this mini viewer pop up
   * @return The grid-pane button
   */
  @FXML
  public GridPane getGridPane() { return this.gridPane; }
}
