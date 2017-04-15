package info.rynkowski.consoletwitter.domain.interactors;

import info.rynkowski.consoletwitter.domain.model.FollowingGraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFollowUserInteractorTest {

  @Mock private FollowingGraph followingGraph;
  @InjectMocks private DefaultFollowUserInteractor followUser;

  @Test
  public void test_oneMessage() {
    // preparation
    doNothing().when(followingGraph).addConnection(anyString(), anyString());
    // usage
    FollowUserInteractor.Params params = new FollowUserInteractor.Params.Builder()
        .follower("rynkowsg").following("codurance").build();
    followUser.execute(params);
    // verification
    verify(followingGraph, times(1))
        .addConnection(params.getFollower(), params.getFollowing());
  }
}
