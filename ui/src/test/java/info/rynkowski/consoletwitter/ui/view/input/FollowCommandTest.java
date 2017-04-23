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
public class FollowCommandTest {

  @Mock private ConsolePresenter consolePresenter;
  private FollowCommand followCommand;

  @Before
  public void initialise() {
    followCommand = new FollowCommand(consolePresenter, new CommandParser());
  }

  @Test
  public void testProcessingCommand_correctCommand() {
    // when
    final CommandProcessor.Result result = followCommand.process("greg follows tom");
    // then
    assertEquals(CommandProcessor.Result.SUCCESS, result);
    verify(consolePresenter).onFollow("greg", "tom");
  }

  @Test
  public void testProcessingCommand_incorrectCommands() {
    testIncorrectCommand("greg follow tom");
    testIncorrectCommand("greg followstom");
    testIncorrectCommand("gregfollows tom");
    testIncorrectCommand("greg follows");
  }

  private void testIncorrectCommand(final String command) {
    // when
    final CommandProcessor.Result result = followCommand.process(command);
    // then
    assertEquals(CommandProcessor.Result.FAILED, result);
    verifyZeroInteractions(consolePresenter);
  }
}
