package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.FollowingGraph;
import info.rynkowski.consoletwitter.domain.model.Message;
import info.rynkowski.consoletwitter.domain.model.MessagesRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultReadWallInteractor implements ReadWallInteractor {

  private final FollowingGraph followingGraph;
  private final MessagesRepository messagesRepository;

  public DefaultReadWallInteractor(final FollowingGraph followingGraph,
      final MessagesRepository messagesRepository) {
    this.followingGraph = followingGraph;
    this.messagesRepository = messagesRepository;
  }

  @Override
  public List<Message> execute(final String username) {
    List<Message> messages = new ArrayList<>();
    final List<String> followingUsers = followingGraph.getFollowingUsers(username);
    for (final String followingUser : followingUsers) {
      final List<Message> followingUserMessages = messagesRepository.getMessages(followingUser);
      messages.addAll(followingUserMessages);
    }
    Collections.sort(messages);
    return messages;
  }
}
