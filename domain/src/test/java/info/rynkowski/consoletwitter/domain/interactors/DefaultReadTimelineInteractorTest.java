package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReadTimelineInteractorTest {

  @Mock private MessagesRepository messagesRepository;
  @InjectMocks private DefaultReadTimelineInteractor readTimeline;

  @Test
  public void test_noMessages() {
    // preparation
    final List<String> emptyList = Collections.emptyList();
    doReturn(emptyList).when(messagesRepository).getMessages(anyString());
    // usage
    final String username = "rynkowsg";
    final List<Message> messages = readTimeline.execute(username);
    // verification
    assertEquals(0, messages.size());
  }

  @Test
  public void test_oneMessage() {
    // preparation
    final List<String> oneItemList = Collections.singletonList("Message1");
    doReturn(oneItemList).when(messagesRepository).getMessages(anyString());
    // usage
    final String username = "rynkowsg";
    final List<Message> messages = readTimeline.execute(username);
    // verification
    assertEquals(1, messages.size());
    verify(messagesRepository).getMessages(username);
  }
}
