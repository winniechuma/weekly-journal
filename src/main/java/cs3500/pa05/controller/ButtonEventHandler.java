package cs3500.pa05.controller;

import cs3500.pa05.view.ButtonsView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Class that handles a Button click
 */
public class ButtonEventHandler implements EventHandler {
  private ButtonsView buttonsView;

  /**
   * Handles a Button click given a buttonsView
   *
   * @param buttonsView all buttons present in the week.fxml
   */

  public ButtonEventHandler(ButtonsView buttonsView) {
    this.buttonsView = buttonsView;
  }

  /**
   * Returns different buttons based on the button clicked
   *
   * @param event the event which occurred
   */
  @FXML
  public void handle(Event event) {
    if (event.getSource() == this.buttonsView.getMonday()) {
      System.out.println("Monday");
    }
    if (event.getSource() == this.buttonsView.getTuesday()) {
      System.out.println("Tuesday");
    }
    if (event.getSource() == this.buttonsView.getWednesday()) {
      System.out.println("Wednesday");
    }
    if (event.getSource() == this.buttonsView.getThursday()) {
      System.out.println("Thursday");
    }
    if (event.getSource() == this.buttonsView.getFriday()) {
      System.out.println("Friday");
    }
    if (event.getSource() == this.buttonsView.getSaturday()) {
      System.out.println("Saturday");
    }
    if (event.getSource() == this.buttonsView.getSunday()) {
      System.out.println("Sunday");
    }
    if (event.getSource() == this.buttonsView.getClickMe()) {
      System.out.println("ClickMe");
    }
  }
}
