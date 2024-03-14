package cs3500;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Completion;
import org.junit.jupiter.api.Test;


/**
 * Class representing the Completion Test class
 */
public class TestCompletion {

  /**
   * Testing that the expected completion matches
   * The defined enum variable
   */
  @Test
  public void testCompletion() {
    assertEquals("NO", Completion.NO.toString());
    assertEquals("YES", Completion.YES.toString());
  }
}

