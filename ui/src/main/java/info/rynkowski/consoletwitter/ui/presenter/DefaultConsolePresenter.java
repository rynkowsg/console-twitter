package info.rynkowski.consoletwitter.ui.presenter;

import info.rynkowski.consoletwitter.domain.interactors.FollowUserInteractor;
import info.rynkowski.consoletwitter.domain.interactors.PostMessageInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadTimelineInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadWallInteractor;
import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.ui.view.ConsoleView;
import java.util.List;

public class DefaultConsolePresenter implements ConsolePresenter {

  private final FollowUserInteractor followUser;
  private final PostMessageInteractor postMessage;
  private final ReadTimelineInteractor readTimeline;
  private final ReadWallInteractor readWall;

  private ConsoleView view;

  public DefaultConsolePresenter(final FollowUserInteractor followUser,
      final PostMessageInteractor postMessage, final ReadTimelineInteractor readTimeline,
      final ReadWallInteractor readWall) {
    this.followUser = followUser;
    this.postMessage = postMessage;
    this.readTimeline = readTimeline;
    this.readWall = readWall;
  }

  @Override public void setView(final ConsoleView view) {
    this.view = view;
  }

  @Override public void onExit() {
    view.close();
    setView(null);
  }

  @Override public void onFollow(final String follower, final String followed) {
    followUser.execute(
        new FollowUserInteractor.Params.Builder()
            .follower(follower)
            .following(followed)
            .build()
    );
  }

  @Override public void onPostMessage(final String username, final String message) {
    postMessage.execute(
        new Message.Builder().author(username).text(message).dateNow().build()
    );
  }

  @Override public void onShowWall(final String username) {
    final List<Message> messages = readWall.execute(username);
    view.print(messages, ConsoleView.PrintMode.WITH_AUTOR);
  }

  @Override public void onShowTimeline(final String username) {
    final List<Message> messages = readTimeline.execute(username);
    view.print(messages);
  }
}
