package info.rynkowski.consoletwitter.ui.view;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import info.rynkowski.consoletwitter.ui.view.input.InputHandler;
import info.rynkowski.consoletwitter.ui.view.output.ElapsedTimeTextGenerator;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.joda.time.DateTime;

public class DefaultConsoleView implements ConsoleView {

  private InputStream inputStream;
  private PrintStream outputSteam;

  private ConsolePresenter presenter;

  private InputHandler inputHandler;
  private ElapsedTimeTextGenerator elapsedTimeTextGenerator;

  private Boolean closeFlag = false;

  public DefaultConsoleView(InputStream inputStream, PrintStream outputSteam,
      ConsolePresenter presenter, InputHandler inputHandler,
      ElapsedTimeTextGenerator elapsedTimeTextGenerator) {
    this.inputStream = inputStream;
    this.outputSteam = outputSteam;
    this.presenter = presenter;
    this.inputHandler = inputHandler;
    this.elapsedTimeTextGenerator = elapsedTimeTextGenerator;
    presenter.setView(this);
  }

  @Override public void open() {
    Scanner scanner = new Scanner(inputStream);
    while (!closeFlag) {
      outputSteam.print("> ");
      if(!scanner.hasNextLine()) {
        break;
      }
      final String command = scanner.nextLine();
      inputHandler.processCommand(command);
    }
  }

  @Override public void close() {
    closeFlag = true;
  }

  @Override public void print(final List<Message> messages) {
    print(messages, PrintMode.WITHOUT_AUTOR);
  }

  @Override public void print(final List<Message> messages, PrintMode mode) {
    final DateTime now = DateTime.now();
    for (final Message message : messages) {
      final String elapsedTime = elapsedTimeTextGenerator.generate(message.getDate(), now);
      final String authorDetails = mode == PrintMode.WITH_AUTOR ? message.getAuthor() + " - " : "";
      outputSteam.printf("%s%s (%s)\n", authorDetails, message.getText(), elapsedTime);
    }
  }
}
