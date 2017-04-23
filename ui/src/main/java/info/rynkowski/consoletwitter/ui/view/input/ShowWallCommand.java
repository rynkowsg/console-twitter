package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import java.util.List;

public class ShowWallCommand implements CommandProcessor {

  private static final String USERNAME_PATTERN = "\\w{1,15}";
  private static final String MATCHING_PATTERN = String.format("^(%s) wall$", USERNAME_PATTERN);

  private ConsolePresenter consolePresenter;
  private CommandParser commandParser;

  public ShowWallCommand(final ConsolePresenter consolePresenter,
                       final CommandParser commandParser) {
    this.consolePresenter = consolePresenter;
    this.commandParser = commandParser;
  }

  @Override public Result process(final String commandText) {
    if (!commandParser.isMatching(MATCHING_PATTERN, commandText)) {
      return Result.FAILED;
    }
    final List<String> capturedValues = commandParser.captureValues(MATCHING_PATTERN, commandText);
    final String username = capturedValues.get(0);
    consolePresenter.onShowWall(username);
    return Result.SUCCESS;
  }
}
