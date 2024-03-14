package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents the journal view interface
 */
public interface JournalView {
  /**
   * @return a scene
   * @throws IllegalStateException if it is not loading the proper thing
   */
  Scene load() throws IllegalStateException;
}
