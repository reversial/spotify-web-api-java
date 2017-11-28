package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

public class FeaturedPlaylistsRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final FeaturedPlaylistsRequest request = api.getFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country("SE")
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("featured-playlists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<FeaturedPlaylists> future = request.getAsync();

    Futures.addCallback(future, new FutureCallback<FeaturedPlaylists>() {
      @Override
      public void onSuccess(FeaturedPlaylists featuredPlaylists) {
        assertEquals("Behöver du hjälp att komma igång idag?", featuredPlaylists.getMessage());

        Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

        assertEquals(12, playlistPage.getTotal());
        assertEquals(1, playlistPage.getOffset());
        assertEquals(1, playlistPage.getLimit());
        assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
                        "locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=2&limit=1",
                playlistPage.getNext());
        assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
                        "locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=0&limit=1",
                playlistPage.getPrevious());

        List<PlaylistSimplified> items = playlistPage.getItems();
        assertEquals(1, items.size());

        PlaylistSimplified playlist = items.get(0);
        assertEquals("2BgVZaiDigaqxTbZEI2TpE", playlist.getId());
        assertEquals("Träning", playlist.getName());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final FeaturedPlaylistsRequest request = api.getFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country("SE")
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("featured-playlists.json"))
            .build();

    FeaturedPlaylists featuredPlaylists = request.get();

    assertEquals("Behöver du hjälp att komma igång idag?", featuredPlaylists.getMessage());

    Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

    assertEquals(12, playlistPage.getTotal());
    assertEquals(1, playlistPage.getOffset());
    assertEquals(1, playlistPage.getLimit());
    assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
                    "locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=2&limit=1",
            playlistPage.getNext());
    assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
                    "locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=0&limit=1",
            playlistPage.getPrevious());

    List<PlaylistSimplified> items = playlistPage.getItems();
    assertEquals(1, items.size());

    PlaylistSimplified playlist = items.get(0);
    assertEquals("2BgVZaiDigaqxTbZEI2TpE", playlist.getId());
    assertEquals("Träning", playlist.getName());

  }

}
