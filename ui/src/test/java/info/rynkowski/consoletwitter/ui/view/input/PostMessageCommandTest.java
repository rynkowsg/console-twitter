package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class PostMessageCommandTest {

  @Mock private ConsolePresenter consolePresenter;
  private PostMessageCommand postMessageCommand;

  @Before
  public void initialise() {
    postMessageCommand = new PostMessageCommand(consolePresenter, new CommandParser());
  }

  @Test
  public void testProcessingCommand_correctCommand() {
    // when
    final CommandProcessor.Result result = postMessageCommand.process("greg -> Hello Twitter!");
    // then
    assertEquals(CommandProcessor.Result.SUCCESS, result);
    verify(consolePresenter).onPostMessage("greg", "Hello Twitter!");
  }

  @Test
  public void testProcessingCommand_incorrectCommands() {
    testIncorrectCommand("greg ->Hello Twitter!");
    testIncorrectCommand("greg-> Hello Twitter!");
    testIncorrectCommand("greg - Hello Twitter!");
    testIncorrectCommand("-> Hello Twitter!");
    testIncorrectCommand(" -> Hello Twitter!");
  }

  private void testIncorrectCommand(final String command) {
    // when
    final CommandProcessor.Result result = postMessageCommand.process(command);
    // then
    assertEquals(CommandProcessor.Result.FAILED, result);
    verifyZeroInteractions(consolePresenter);
  }
}
