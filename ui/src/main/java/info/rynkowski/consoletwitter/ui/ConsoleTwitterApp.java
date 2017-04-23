package info.rynkowski.consoletwitter.ui;

import info.rynkowski.consoletwitter.data.model.FollowingGraphInMemory;
import info.rynkowski.consoletwitter.data.model.MessagesRepositoryInMemory;
import info.rynkowski.consoletwitter.domain.interactors.DefaultFollowUserInteractor;
import info.rynkowski.consoletwitter.domain.interactors.DefaultPostMessageInteractor;
import info.rynkowski.consoletwitter.domain.interactors.DefaultReadTimelineInteractor;
import info.rynkowski.consoletwitter.domain.interactors.DefaultReadWallInteractor;
import info.rynkowski.consoletwitter.domain.interactors.FollowUserInteractor;
import info.rynkowski.consoletwitter.domain.interactors.PostMessageInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadTimelineInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadWallInteractor;
import info.rynkowski.consoletwitter.domain.model.FollowingGraph;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import info.rynkowski.consoletwitter.ui.presenter.ConsolePresenter;
import info.rynkowski.consoletwitter.ui.presenter.DefaultConsolePresenter;
import info.rynkowski.consoletwitter.ui.view.ConsoleView;
import info.rynkowski.consoletwitter.ui.view.DefaultConsoleView;
import info.rynkowski.consoletwitter.ui.view.output.ElapsedTimeTextGenerator;
import info.rynkowski.consoletwitter.ui.view.input.InputHandler;

public class ConsoleTwitterApp {

  public static void main(String[] args) {
    // data
    final FollowingGraph followingGraph = new FollowingGraphInMemory();
    final MessagesRepository messagesRepository = new MessagesRepositoryInMemory();
    // domain
    final FollowUserInteractor followUserInteractor = new DefaultFollowUserInteractor(followingGraph);
    final PostMessageInteractor postMessageInteractor = new DefaultPostMessageInteractor(messagesRepository);
    final ReadTimelineInteractor readTimelineInteractor = new DefaultReadTimelineInteractor(messagesRepository);
    final ReadWallInteractor readWallInteractor = new DefaultReadWallInteractor(followingGraph, messagesRepository);
    // ui
    final ConsolePresenter consolePresenter = new DefaultConsolePresenter(followUserInteractor,
        postMessageInteractor, readTimelineInteractor, readWallInteractor);
    final InputHandler inputHandler = new InputHandler(consolePresenter);
    final ElapsedTimeTextGenerator elapsedTimeTextGenerator = new ElapsedTimeTextGenerator();
    final ConsoleView consoleView = new DefaultConsoleView(System.in, System.out,
        consolePresenter, inputHandler, elapsedTimeTextGenerator);
    // TODO: move creation of above objects to a dependency injection container.

    consoleView.open();
  }
}
