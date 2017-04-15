package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPostMessageInteractorTest {

  @Mock private MessagesRepository messagesRepository;
  @InjectMocks private DefaultPostMessageInteractor postMessage;

  @Test
  public void test_oneMessage() {
    // preparation
    doNothing().when(messagesRepository).addMessage(anyString(), any(Message.class));
    // usage
    final PostMessageInteractor.Params params = new PostMessageInteractor.Params.Builder()
        .username("rynkowsg")
        .message(new Message.Builder().text("MessageText").dateNow().build())
        .build();
    postMessage.execute(params);
    // verification
    verify(messagesRepository, times(1))
        .addMessage(params.getUsername(), params.getMessage());
  }
}
