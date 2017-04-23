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
public class ExitCommandTest {

  @Mock private ConsolePresenter consolePresenter;
  private ExitCommand exitCommand;

  @Before
  public void initialise() {
    exitCommand = new ExitCommand(consolePresenter, new CommandParser());
  }

  @Test
  public void testProcessingCommand_correctCommand() {
    // when
    final CommandProcessor.Result result = exitCommand.process(":exit");
    // then
    assertEquals(CommandProcessor.Result.SUCCESS, result);
    verify(consolePresenter).onExit();
  }

  @Test
  public void testProcessingCommand_incorrectCommand() {
    // when
    final CommandProcessor.Result result = exitCommand.process("exit");
    // then
    assertEquals(CommandProcessor.Result.FAILED, result);
    verifyZeroInteractions(consolePresenter);
  }
}
