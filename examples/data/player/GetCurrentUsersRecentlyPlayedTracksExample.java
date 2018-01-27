package data.player;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import com.wrapper.spotify.requests.data.player.GetCurrentUsersRecentlyPlayedTracksRequest;

import java.util.Date;
import java.util.concurrent.Future;

public class GetCurrentUsersRecentlyPlayedTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetCurrentUsersRecentlyPlayedTracksRequest getCurrentUsersRecentlyPlayedTracksRequest =
          spotifyApi.getCurrentUsersRecentlyPlayedTracks()
                  .after(new Date(1517087230))
                  .before(new Date(1453932420))
                  .limit(10)
          .build();

  public static void getCurrentUsersRecentlyPlayedTracks_Sync() {
    try {
      final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased = getCurrentUsersRecentlyPlayedTracksRequest.execute();

      System.out.println("Total: " + playHistoryPagingCursorbased.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getCurrentUsersRecentlyPlayedTracks_Async() {
    try {
      final Future<PagingCursorbased<PlayHistory>> pagingCursorbasedFuture = getCurrentUsersRecentlyPlayedTracksRequest.executeAsync();

      // ...

      final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased = pagingCursorbasedFuture.get();

      System.out.println("Total: " + playHistoryPagingCursorbased.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}