package cs3500.pa05;

import cs3500.pa05.controller.JournalControllerImpl;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.JournalViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class Driver extends Application {

  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   * @throws Exception if something goes wrong
   */

  /**
   *
   */
  @Override
  public void start(Stage primaryStage) {

    // add a title to the stage
    try {
      primaryStage.setTitle("Put User's name choice");

      JournalControllerImpl controller = new JournalControllerImpl(primaryStage);
      JournalView view = new JournalViewImpl(controller);

      Scene thisScene = view.load();
      primaryStage.setScene(thisScene);
      controller.setScene(thisScene);

   //    fetch the view's controller
      controller.runIntro();

      // render the stage
      primaryStage.show();
    } catch (IllegalStateException e) {
      //REMOVE LATER
      //e.printStackTrace();
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   *
   */
  public static void main(String[] args) {
    launch();
  }
}
