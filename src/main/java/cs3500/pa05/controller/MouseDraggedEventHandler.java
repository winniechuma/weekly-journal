//package cs3500.pa05.controller;
//
//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//
///**
// *
// */
//public class MouseDraggedEventHandler implements EventHandler<MouseEvent> {
//  private Stage stage;
//  private double xDiff;
//  private double yDiff;
//  public MouseDraggedEventHandler(Stage stage, double xDiff, double yDiff) {
//    this.stage = stage;
//    this.xDiff = xDiff;
//    this.yDiff = yDiff;
//  }
//  @Override
//  public void handle(MouseEvent event) {
//    stage.setX(event.getScreenX() - xDiff);
//    stage.setY(event.getScreenY() - yDiff);
//  }
//
//}
