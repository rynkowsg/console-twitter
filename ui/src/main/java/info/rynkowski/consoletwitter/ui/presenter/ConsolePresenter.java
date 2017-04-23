package info.rynkowski.consoletwitter.ui.presenter;

import info.rynkowski.consoletwitter.ui.view.ConsoleView;

public interface ConsolePresenter {

  void setView(final ConsoleView view);

  void onExit();

  void onFollow(final String follower, final String followed);

  void onShowWall(final String username);

  void onShowTimeline(final String username);

  void onPostMessage(final String username, final String message);
}
