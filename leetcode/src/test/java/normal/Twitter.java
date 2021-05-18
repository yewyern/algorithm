package normal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

public class Twitter {

    private final LinkedList<int[]> tweets;
    private final Map<Integer, Set<Integer>> followerMap;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        tweets = new LinkedList<>();
        followerMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        tweets.addFirst(new int[]{userId, tweetId});
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        return tweets.stream()
            .filter(tweet -> userId == tweet[0]
                || followerMap.get(userId) != null && followerMap.get(userId).contains(tweet[0]))
            .map(tweet -> tweet[1]).limit(10).collect(Collectors.toList());
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!followerMap.containsKey(followerId)) {
            followerMap.put(followerId, new HashSet<>());
        }
        followerMap.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerMap.containsKey(followerId)) {
            followerMap.get(followerId).remove(followeeId);
        }
    }

    @Test
    public void test() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));//5
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));//6,5
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));//5

        //输入：
        //["Twitter","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","getNewsFeed"]
        //[[],[1,5],[1,3],[1,101],[1,13],[1,10],[1,2],[1,94],[1,505],[1,333],[1,22],[1,11],[1]]
        //输出：
        //[null,null,null,null,null,null,null,null,null,null,null,null,[11,22,333,505,94,2,10,13,101,3,5]]
        //预期：
        //[null,null,null,null,null,null,null,null,null,null,null,null,[11,22,333,505,94,2,10,13,101,3]]
        Twitter twitter2 = new Twitter();
        twitter2.postTweet(1, 5);
        twitter2.postTweet(1, 3);
        twitter2.postTweet(1, 101);
        twitter2.postTweet(1, 13);
        twitter2.postTweet(1, 10);
        twitter2.postTweet(1, 2);
        twitter2.postTweet(1, 94);
        twitter2.postTweet(1, 505);
        twitter2.postTweet(1, 333);
        twitter2.postTweet(1, 22);
        twitter2.postTweet(1, 11);
        System.out.println(twitter2.getNewsFeed(1));//[11,22,333,505,94,2,10,13,101,3]
    }
}
