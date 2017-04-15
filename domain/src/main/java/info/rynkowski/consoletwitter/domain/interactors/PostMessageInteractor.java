package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

public interface PostMessageInteractor extends Interactor<Void, PostMessageInteractor.Params> {

  @Getter
  class Params {
    private final String username;
    private final Message message;

    public Params(final Builder builder) {
      this.username = builder.username;
      this.message = builder.message;
    }

    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @Setter
    @Accessors(fluent = true, chain = true)
    public static class Builder {
      private String username;
      private Message message;

      public Params build() {
        return new DefaultPostMessageInteractor.Params(this);
      }
    }
  }
}
