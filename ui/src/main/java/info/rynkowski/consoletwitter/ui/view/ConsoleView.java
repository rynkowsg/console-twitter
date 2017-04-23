package info.rynkowski.consoletwitter.ui.view;

import info.rynkowski.consoletwitter.domain.model.Message;
import java.util.List;

public interface ConsoleView {

  void open();

  void close();

  void print(final List<Message> messages);
}
