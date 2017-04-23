package info.rynkowski.consoletwitter.ui.presenter;

import info.rynkowski.consoletwitter.domain.interactors.FollowUserInteractor;
import info.rynkowski.consoletwitter.domain.interactors.PostMessageInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadTimelineInteractor;
import info.rynkowski.consoletwitter.domain.interactors.ReadWallInteractor;
import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.ui.view.ConsoleView;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultConsolePresenterTest {

  @Mock private FollowUserInteractor followUser;
  @Mock private PostMessageInteractor postMessage;
  @Mock private ReadTimelineInteractor readTimeline;
  @Mock private ReadWallInteractor readWall;
  @Mock private ConsoleView consoleView;

  @InjectMocks private DefaultConsolePresenter consolePresenter;

  @Before
  public void beforeEachTest() {
    consolePresenter.setView(consoleView);
  }

  @Test
  public void testFollowingOneUser() {
    // given
    final String follower = "rynkowsg";
    final String followed = "codurance";
    // when
    consolePresenter.onFollow(follower, followed);
    // then
    final ArgumentCaptor<FollowUserInteractor.Params> argument =
        ArgumentCaptor.forClass(FollowUserInteractor.Params.class);
    verify(followUser, times(1)).execute(argument.capture());
    assertEquals(follower, argument.getValue().getFollower());
    assertEquals(followed, argument.getValue().getFollowing());
  }

  @Test
  public void testPostingOneMessage() {
    // given
    final String author = "rynkowsg";
    final String message = "Test Message";
    // when
    consolePresenter.onPostMessage(author, message);
    // then
    final ArgumentCaptor<Message> argument =
        ArgumentCaptor.forClass(Message.class);
    verify(postMessage, times(1)).execute(argument.capture());
    assertEquals(author, argument.getValue().getAuthor());
    assertEquals(message, argument.getValue().getText());
  }

  @Test
  public void testReadingUsersTimelineWithOnlyOneMessage() {
    // given
    final String username = "rynkowsg";
    final List<Message> messages = Collections.singletonList(
        new Message.Builder().text("MessageText").dateNow().build());
    doReturn(messages).when(readTimeline).execute(username);
    // when
    consolePresenter.onShowTimeline(username);
    // then
    verify(readTimeline, times(1)).execute(username);
    verify(consoleView).print(messages);
  }

  @Test
  public void testReadingWallWithOnlyOneMessage() {
    // given
    final String username = "rynkowsg";
    final List<Message> messages = Collections.singletonList(
        new Message.Builder().text("MessageText").dateNow().build());
    doReturn(messages).when(readWall).execute(username);
    // when
    consolePresenter.onShowWall(username);
    // then
    verify(readWall, times(1)).execute(username);
    verify(consoleView, times(1))
        .print(messages, ConsoleView.PrintMode.WITH_AUTOR);
  }
}
