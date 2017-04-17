package info.rynkowski.consoletwitter.data.model;

import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessagesRepositoryInMemory implements MessagesRepository {

  private final Map<String, List<Message>> storage = new HashMap<>();

  @Override
  public void addMessage(final String username, final Message message) {
    if (storage.containsKey(username)) {
      storage.get(username).add(message);
    } else {
      storage.put(username, new LinkedList<>(Collections.singleton(message)));
    }
  }

  @Override
  public List<Message> getMessages(final String username) {
    return storage.getOrDefault(username, Collections.emptyList());
  }
}
