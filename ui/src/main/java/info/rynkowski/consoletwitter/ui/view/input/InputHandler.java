package info.rynkowski.consoletwitter.ui.view.input;

import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {

  private List<CommandProcessor> commandProcessors;
  private final ConsolePresenter presenter;

  public InputHandler(final ConsolePresenter presenter) {
    this.presenter = presenter;
    initialiseCommandProcessors();
  }

  public void processCommand(final String command) {
    for(final CommandProcessor processor : commandProcessors) {
      processor.process(command);
    }
  }

  private void initialiseCommandProcessors() {
    final CommandParser parser = new CommandParser();
    // TODO: Inject whole array to that class.
    commandProcessors = new ArrayList<>();
    commandProcessors.add(new FollowCommand(presenter, parser));
    commandProcessors.add(new PostMessageCommand(presenter, parser));
    commandProcessors.add(new ShowTimelineCommand(presenter, parser));
    commandProcessors.add(new ShowWallCommand(presenter, parser));
    commandProcessors.add(new ExitCommand(presenter, parser));
  }
}
