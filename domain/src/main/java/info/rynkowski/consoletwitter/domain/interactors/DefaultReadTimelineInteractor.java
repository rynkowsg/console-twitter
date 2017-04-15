package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import java.util.List;

public class DefaultReadTimelineInteractor implements ReadTimelineInteractor {

  private final MessagesRepository repository;

  public DefaultReadTimelineInteractor(final MessagesRepository repository) {
    this.repository = repository;
  }

  public List<Message> execute(final String username) {
    return repository.getMessages(username);
  }
}
