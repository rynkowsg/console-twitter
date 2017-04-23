package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import java.util.List;

public class FollowCommand implements CommandProcessor {

  private static final String USERNAME_PATTERN = "\\w{1,15}";
  private static final String MATCHING_PATTERN =
      String.format("^(%s) follows (%s)$", USERNAME_PATTERN, USERNAME_PATTERN);

  private ConsolePresenter consolePresenter;
  private CommandParser commandParser;

  public FollowCommand(final ConsolePresenter consolePresenter,
                       final CommandParser commandParser) {
    this.consolePresenter = consolePresenter;
    this.commandParser = commandParser;
  }

  @Override public Result process(final String commandText) {
    if (!commandParser.isMatching(MATCHING_PATTERN, commandText)) {
      return Result.FAILED;
    }
    final List<String> capturedValues = commandParser.captureValues(MATCHING_PATTERN, commandText);
    final String follower = capturedValues.get(0);
    final String followed = capturedValues.get(1);
    consolePresenter.onFollow(follower, followed);
    return Result.SUCCESS;
  }
}
