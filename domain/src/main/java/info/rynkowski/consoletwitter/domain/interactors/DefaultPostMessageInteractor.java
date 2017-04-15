package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.MessagesRepository;

public class DefaultPostMessageInteractor implements PostMessageInteractor {

  private final MessagesRepository repository;

  public DefaultPostMessageInteractor(final MessagesRepository repository) {
    this.repository = repository;
  }

  @Override
  public Void execute(final Params params) {
    repository.addMessage(params.getUsername(), params.getMessage());
    return null;
  }
}
