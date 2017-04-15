package info.rynkowski.consoletwitter.domain.model;

import java.util.List;

public interface FollowingGraph {

  void addConnection(final String follower, final String following);

  List<String> getFollowingUsers(final String follower);
}
