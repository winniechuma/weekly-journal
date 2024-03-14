package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.JournalControllerImpl;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a journal view implementation
 */
public class JournalViewImpl implements JournalView {

  private final FXMLLoader loader;

  /**
   * @param controller controller
   */
  public JournalViewImpl(JournalControllerImpl controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("Intro.fxml"));
    this.loader.setController(controller);
  }


  /**
   * Represents the implemented load method that loads this scence
   * @return a scene
   */
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }

}
