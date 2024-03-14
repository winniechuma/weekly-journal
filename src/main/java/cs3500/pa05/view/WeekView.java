package cs3500.pa05.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Week View representation
 */
public class WeekView {
  private FXMLLoader loader;

  public WeekView() {
    // look up and store the layout
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("welcome.fxml"));
  }

  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}


