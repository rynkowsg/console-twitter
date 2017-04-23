package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;

public class ExitCommand implements CommandProcessor {

  private static final String PATTERN = "^:exit$";

  private ConsolePresenter consolePresenter;
  private CommandParser commandParser;

  public ExitCommand(final ConsolePresenter consolePresenter, CommandParser commandParser) {
    this.consolePresenter = consolePresenter;
    this.commandParser = commandParser;
  }

  @Override public CommandProcessor.Result process(final String commandText) {
    if (!commandParser.isMatching(PATTERN, commandText)) {
      return CommandProcessor.Result.FAILED;
    }

    consolePresenter.onExit();
    return CommandProcessor.Result.SUCCESS;
  }
}
