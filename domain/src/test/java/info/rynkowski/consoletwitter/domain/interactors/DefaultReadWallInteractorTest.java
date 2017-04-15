package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.FollowingGraph;
import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReadWallInteractorTest {
  private static final String USERNAME = "rynkowsg";

  @Mock private MessagesRepository messagesRepository;
  @Mock private FollowingGraph followingGraph;
  @InjectMocks private DefaultReadWallInteractor interactor;

  @Test
  public void testShowingEmptyWall() {
    // preparation
    final List<Message> emptyList = Collections.emptyList();
    doReturn(emptyList).when(followingGraph).getFollowingUsers(anyString());
    // usage
    final List<Message> wallMessages = interactor.execute(USERNAME);
    // verification
    assertEquals(0, wallMessages.size());
    verify(followingGraph, times(1)).getFollowingUsers(USERNAME);
  }

  @Test
  public void testShowingWallWithMessagesOfOneUser() {
    // preparation
    final String followingUser = "codurance";
    final List<String> listWithOneFollowing = Collections.singletonList(followingUser);
    doReturn(listWithOneFollowing).when(followingGraph).getFollowingUsers(USERNAME);
    final List<Message> listOfMessages = Arrays.asList(
        new Message.Builder().text("Message1").dateNow().build(),
        new Message.Builder().text("Message2").dateNow().build(),
        new Message.Builder().text("Message3").dateNow().build());
    doReturn(listOfMessages).when(messagesRepository).getMessages(followingUser);
    // usage
    final List<Message> wallMessages = interactor.execute(USERNAME);
    // verification
    verify(followingGraph, times(1)).getFollowingUsers(USERNAME);
    verify(messagesRepository, times(1)).getMessages(followingUser);
    assertEquals(listOfMessages.size(), wallMessages.size());
  }

  @Test
  public void testShowingWallWithMessagesOfTwoUsers() {
    // preparation
    //   users
    final String followingUser1 = "codurance";
    final String followingUser2 = "miroburn";
    final List<String> listOfFollowings = Arrays.asList(followingUser1, followingUser2);
    //   messages
    final List<Message> messagesOfUser1 = Arrays.asList(
        new Message.Builder().text("Message1a").dateNow().build(),
        new Message.Builder().text("Message2a").dateNow().build(),
        new Message.Builder().text("Message3a").dateNow().build());
    final List<Message> messagesOfUser2 = Arrays.asList(
        new Message.Builder().text("Message1b").dateNow().build(),
        new Message.Builder().text("Message2b").dateNow().build());
    //   rules
    doReturn(messagesOfUser1).when(messagesRepository).getMessages(followingUser1);
    doReturn(messagesOfUser2).when(messagesRepository).getMessages(followingUser2);
    doReturn(listOfFollowings).when(followingGraph).getFollowingUsers(USERNAME);
    // usage
    final List<Message> wallMessages = interactor.execute(USERNAME);
    // verification
    verify(followingGraph, times(1)).getFollowingUsers(USERNAME);
    verify(messagesRepository, times(1)).getMessages(followingUser1);
    verify(messagesRepository, times(1)).getMessages(followingUser2);
    assertEquals(messagesOfUser1.size() + messagesOfUser2.size(), wallMessages.size());
  }

  @Test
  public void testShowingWallWithMessagesOfTwoUsers_areMessagesOrdered() {
    // preparation
    //   users
    final String followingUser1 = "codurance";
    final String followingUser2 = "miroburn";
    final List<String> listOfFollowings = Arrays.asList(followingUser1, followingUser2);
    //   messages
    final Message coduranceMsg1 = new Message.Builder()
        .text("codurence: msg at 2017-04-16 13:02")
        .date(new DateTime(2017, 4, 16, 13, 2))
        .build();
    final Message coduranceMsg2 = new Message.Builder()
        .text("codurence: msg at 2017-04-17 9:55")
        .date(new DateTime(2017, 4, 17, 9, 55))
        .build();
    final Message miroburnMsg = new Message.Builder()
        .text("codurence: msg at 2017-04-16 19:00")
        .date(new DateTime(2017, 4, 16, 19, 0))
        .build();
    final List<Message> messagesOfUser1 = Arrays.asList(coduranceMsg1, coduranceMsg2);
    final List<Message> messagesOfUser2 = Collections.singletonList(miroburnMsg);
    //   rules
    doReturn(messagesOfUser1).when(messagesRepository).getMessages(followingUser1);
    doReturn(messagesOfUser2).when(messagesRepository).getMessages(followingUser2);
    doReturn(listOfFollowings).when(followingGraph).getFollowingUsers(USERNAME);
    // usage
    final List<Message> wallMessages = interactor.execute(USERNAME);
    // verification
    final List<Message> expectingList = Arrays.asList(coduranceMsg1, miroburnMsg, coduranceMsg2);
    assertArrayEquals("The wall messages should be sorted", expectingList.toArray(),
        wallMessages.toArray());
  }
}
