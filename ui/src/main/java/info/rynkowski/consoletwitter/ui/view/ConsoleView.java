package info.rynkowski.consoletwitter.ui.view;

import info.rynkowski.consoletwitter.domain.model.Message;
import java.util.List;

public interface ConsoleView {

  enum PrintMode { WITH_AUTOR, WITHOUT_AUTOR }

  void open();

  void close();

  void print(final List<Message> messages);

  void print(final List<Message> messages, PrintMode printMode);
}
