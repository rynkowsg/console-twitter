package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InputHandlerTest {

  @Mock private ConsolePresenter consolePresenter;

  private InputHandler inputHandler;

  @Before public void initialise() {
    inputHandler = new InputHandler(consolePresenter);
  }

  @Test
  public void testHandlingExitCommand() {
    // when
    inputHandler.processCommand(":exit");
    // then
    verify(consolePresenter).onExit();
  }

  @Test
  public void testHandlingFollowCommand() {
    // when
    inputHandler.processCommand("greg follows tom");
    // then
    verify(consolePresenter).onFollow("greg", "tom");
  }

  @Test
  public void testHandlingPostMessageCommand() {
    // when
    inputHandler.processCommand("greg -> Hello Twitter!");
    // then
    verify(consolePresenter).onPostMessage("greg", "Hello Twitter!");
  }

  @Test
  public void testHandlingShowTimelineCommand() {
    // when
    inputHandler.processCommand("greg");
    // then
    verify(consolePresenter).onShowTimeline("greg");
  }

  @Test
  public void testHandlingShowWallCommand() {
    // when
    inputHandler.processCommand("greg wall");
    // then
    verify(consolePresenter).onShowWall("greg");
  }
}
