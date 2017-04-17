package info.rynkowski.consoletwitter.data.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class FollowingGraphInMemoryTest {

  private FollowingGraphInMemory graph;

  @Before public void setUp() {
    graph = new FollowingGraphInMemory();
  }

  @Test public void testWhenNoConnections() {
    // given
    final String followerUsername = "rynkowsg";
    // when
    // then
    assertEquals(0, graph.getFollowingUsers(followerUsername).size());
  }

  @Test public void testWhenUserFollowOnePerson() {
    // given
    final String followerUsername = "rynkowsg";
    final String followingUsername = "codurance";
    // when
    graph.addConnection(followerUsername, followingUsername);
    final List<String> followingList = graph.getFollowingUsers(followerUsername);
    // then
    final List<String> expectedFollowingList = Collections.singletonList(followingUsername);
    assertArrayEquals(expectedFollowingList.toArray(), followingList.toArray());
  }

  @Test public void testWhenTwoUsersFollowCouplePeople() {
    // given
    final String follower1 = "rynkowsg";
    final String follower2 = "kate_b";
    final List<String> followingListOfUser1 = Arrays.asList("codurance", "miroburn", "unclebob");
    final List<String> followingListOfUser2 = Arrays.asList("rynkowsg", "miroburn");
    // when
    for (final String username : followingListOfUser1) {
      graph.addConnection(follower1, username);
    }
    for (final String username : followingListOfUser2) {
      graph.addConnection(follower2, username);
    }
    final List<String> recievedFollowingListOfUser1 = graph.getFollowingUsers(follower1);
    final List<String> recievedFollowingListOfUser2 = graph.getFollowingUsers(follower2);
    // then
    assertArrayEquals(followingListOfUser1.toArray(), recievedFollowingListOfUser1.toArray());
    assertArrayEquals(followingListOfUser2.toArray(), recievedFollowingListOfUser2.toArray());
  }
}
