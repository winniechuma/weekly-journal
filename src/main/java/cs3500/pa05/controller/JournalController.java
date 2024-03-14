package cs3500.pa05.controller;


import java.io.IOException;

public interface JournalController {
  /**
   * @throws IllegalStateException If there was something that caused the exception
   * @throws IOException If there was something that caused the exception
   */
  void run() throws IllegalStateException, IOException;

}
