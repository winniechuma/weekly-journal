//package cs3500.pa05.controller;
//
//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
//
//public abstract class MouseEventHandler implements EventHandler<MouseEvent> {
//  private double xDiff;
//  private double yDiff;
//  @Override
//  public void handle(MouseEvent event) {
//    System.out.println("this was called");
//    xDiff = event.getSceneX();
//    yDiff = event.getSceneY();
//  }
//
//  public double getX() { return this.xDiff; }
//  public double getY() { return this.yDiff; }
//}
