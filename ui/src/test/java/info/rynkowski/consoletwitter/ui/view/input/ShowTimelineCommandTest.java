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
public class ShowTimelineCommandTest {

  @Mock private ConsolePresenter consolePresenter;
  private ShowTimelineCommand showTimelineCommand;

  @Before
  public void initialise() {
    showTimelineCommand = new ShowTimelineCommand(consolePresenter, new CommandParser());
  }

  @Test
  public void testProcessingCommand_correctCommand() {
    // when
    final CommandProcessor.Result result = showTimelineCommand.process("greg");
    // then
    assertEquals(CommandProcessor.Result.SUCCESS, result);
    verify(consolePresenter).onShowTimeline("greg");
  }

  @Test
  public void testProcessingCommand_incorrectCommands() {
    testIncorrectCommand("show greg");
    testIncorrectCommand("gre g");
  }

  private void testIncorrectCommand(final String command) {
    // when
    final CommandProcessor.Result result = showTimelineCommand.process(command);
    // then
    assertEquals(CommandProcessor.Result.FAILED, result);
    verifyZeroInteractions(consolePresenter);
  }
}
