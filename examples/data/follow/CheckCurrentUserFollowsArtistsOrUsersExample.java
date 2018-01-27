package data.follow;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.requests.data.follow.CheckCurrentUserFollowsArtistsOrUsersRequest;

import java.util.concurrent.Future;

public class CheckCurrentUserFollowsArtistsOrUsersExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;
  private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final CheckCurrentUserFollowsArtistsOrUsersRequest checkCurrentUserFollowsArtistsOrUsersRequest =
          spotifyApi.checkCurrentUserFollowsArtistsOrUsers(type, ids)
          .build();

  public static void checkCurrentUserFollowsArtistsOrUsers_Sync() {
    try {
      final Boolean[] booleans = checkCurrentUserFollowsArtistsOrUsersRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void checkCurrentUserFollowsArtistsOrUsers_Async() {
    try {
      final Future<Boolean[]> booleansFuture = checkCurrentUserFollowsArtistsOrUsersRequest.executeAsync();

      // ...

      final Boolean[] booleans = booleansFuture.get();

      System.out.println("Length: " + booleans.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}