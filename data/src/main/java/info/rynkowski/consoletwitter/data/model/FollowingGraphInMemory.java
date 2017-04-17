package info.rynkowski.consoletwitter.data.model;

import info.rynkowski.consoletwitter.domain.model.FollowingGraph;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FollowingGraphInMemory implements FollowingGraph {
  private final Map<String, List<String>> graph = new HashMap<>();

  @Override public void addConnection(final String follower, final String following) {
    if (graph.containsKey(follower)) {
      graph.get(follower).add(following);
    } else {
      graph.put(follower, new LinkedList<>(Collections.singleton(following)));
    }
  }

  @Override public List<String> getFollowingUsers(final String follower) {
    return graph.getOrDefault(follower, Collections.emptyList());
  }
}
