package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.FollowingGraph;

public class DefaultFollowUserInteractor implements FollowUserInteractor {

  private final FollowingGraph followingGraph;

  public DefaultFollowUserInteractor(final FollowingGraph followingGraph) {
    this.followingGraph = followingGraph;
  }

  @Override
  public Void execute(final Params params) {
    followingGraph.addConnection(params.getFollower(), params.getFollowing());
    return null;
  }
}
