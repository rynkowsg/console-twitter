package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import java.util.List;

public class PostMessageCommand implements CommandProcessor {

  private static final String USERNAME_PATTERN = "\\w{1,15}";
  private static final String MESSAGE_PATTERN = ".*";
  private static final String MATCHING_PATTERN =
      String.format("^(%s) -> (%s)$", USERNAME_PATTERN, MESSAGE_PATTERN);

  private ConsolePresenter consolePresenter;
  private CommandParser commandParser;

  public PostMessageCommand(final ConsolePresenter consolePresenter,
                       final CommandParser commandParser) {
    this.consolePresenter = consolePresenter;
    this.commandParser = commandParser;
  }

  @Override public Result process(final String commandText) {
    if (!commandParser.isMatching(MATCHING_PATTERN, commandText)) {
      return Result.FAILED;
    }
    final List<String> capturedValues = commandParser.captureValues(MATCHING_PATTERN, commandText);
    final String author = capturedValues.get(0);
    final String message = capturedValues.get(1);
    consolePresenter.onPostMessage(author, message);
    return Result.SUCCESS;
  }
}
