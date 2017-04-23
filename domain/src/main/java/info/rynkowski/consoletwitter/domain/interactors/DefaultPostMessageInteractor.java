package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;

public class DefaultPostMessageInteractor implements PostMessageInteractor {

  private final MessagesRepository repository;

  public DefaultPostMessageInteractor(final MessagesRepository repository) {
    this.repository = repository;
  }

  @Override
  public Void execute(final Message message) {
    repository.addMessage(message);
    return null;
  }
}
