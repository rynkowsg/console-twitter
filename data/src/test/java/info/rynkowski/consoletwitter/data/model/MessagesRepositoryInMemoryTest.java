package info.rynkowski.consoletwitter.data.model;

import info.rynkowski.consoletwitter.domain.model.Message;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MessagesRepositoryInMemoryTest {
  private static final String USERNAME = "rynkowsg";
  private static final String USERNAME2 = "miroburn";

  private MessagesRepositoryInMemory repositoryInMemory;

  @Before public void setUp() {
    repositoryInMemory = new MessagesRepositoryInMemory();
  }

  @Test public void testWhenEmpty() {
    // given
    // when
    // then
    assertEquals(0, repositoryInMemory.getMessages(USERNAME).size());
  }

  @Test public void testWhenOneElemAdded() {
    // given
    final Message message = new Message.Builder().text("Message Text").dateNow().build();
    // when
    repositoryInMemory.addMessage(USERNAME, message);
    // then
    final List<Message> messages = repositoryInMemory.getMessages(USERNAME);
    assertEquals(1, messages.size());
    assertArrayEquals(Collections.singletonList(message).toArray(), messages.toArray());
  }

  @Test public void testWithTwoUsers() {
    // given
    final Message firstMsgOfUser1 = new Message.Builder()
        .text("Message Text 1")
        .date(new DateTime(2017, 4, 16, 17, 0))
        .build();
    final Message messageOfUser2 = new Message.Builder()
        .text("Message Text 2")
        .date(new DateTime(2017, 4, 17, 17, 0))
        .build();
    final Message secondMsgOfUser1 = new Message.Builder()
        .text("Message Text 3")
        .date(new DateTime(2017, 4, 18, 17, 0))
        .build();
    // when
    repositoryInMemory.addMessage(USERNAME, firstMsgOfUser1);
    repositoryInMemory.addMessage(USERNAME2, messageOfUser2);
    repositoryInMemory.addMessage(USERNAME, secondMsgOfUser1);
    // then
    final List<Message> expectingMessagesOfUser1 = Arrays.asList(firstMsgOfUser1, secondMsgOfUser1);
    final List<Message> expectingMessagesOfUser2 = Collections.singletonList(messageOfUser2);
    final List<Message> messagesOfUser1 = repositoryInMemory.getMessages(USERNAME);
    final List<Message> messagesOfUser2 = repositoryInMemory.getMessages(USERNAME2);
    assertArrayEquals(expectingMessagesOfUser1.toArray(), messagesOfUser1.toArray());
    assertArrayEquals(expectingMessagesOfUser2.toArray(), messagesOfUser2.toArray());
  }
}
