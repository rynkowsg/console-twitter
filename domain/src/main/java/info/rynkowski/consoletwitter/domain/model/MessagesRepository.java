package info.rynkowski.consoletwitter.domain.model;

import java.util.List;

public interface MessagesRepository {

  void addMessage(final Message message);

  List<Message> getMessages(final String username);
}
