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
public class ShowWallCommandTest {

  @Mock private ConsolePresenter consolePresenter;
  private ShowWallCommand showWallCommand;

  @Before
  public void initialise() {
    showWallCommand = new ShowWallCommand(consolePresenter, new CommandParser());
  }

  @Test
  public void testProcessingCommand_correctCommand() {
    // when
    final CommandProcessor.Result result = showWallCommand.process("greg wall");
    // then
    assertEquals(CommandProcessor.Result.SUCCESS, result);
    verify(consolePresenter).onShowWall("greg");
  }


  @Test
  public void testProcessingCommand_incorrectCommands() {
    testIncorrectCommand("greg walll");
    testIncorrectCommand("greg wal");
    testIncorrectCommand("gregwall");
    testIncorrectCommand("greg");
    testIncorrectCommand("wall");
  }

  private void testIncorrectCommand(final String command) {
    // when
    final CommandProcessor.Result result = showWallCommand.process(command);
    // then
    assertEquals(CommandProcessor.Result.FAILED, result);
    verifyZeroInteractions(consolePresenter);
  }
}
