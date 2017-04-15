package info.rynkowski.consoletwitter.domain.interactors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

public interface FollowUserInteractor extends Interactor<Void, FollowUserInteractor.Params> {

  @Getter
  class Params {
    private final String follower;
    private final String following;

    public Params(final Builder builder) {
      this.follower = builder.follower;
      this.following = builder.following;
    }

    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @Setter
    @Accessors(fluent = true, chain = true)
    public static class Builder {
      private String follower;
      private String following;

      public Params build() {
        return new Params(this);
      }
    }
  }
}
