package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Replace all the tracks in a playlist, overwriting its existing tracks. This powerful request can be useful for
 * replacing tracks, re-ordering existing tracks, or clearing the playlist.
 */
public class ReplacePlaylistsTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link ReplacePlaylistsTracksRequest} constructor.
   *
   * @param builder A {@link ReplacePlaylistsTracksRequest.Builder}.
   */
  private ReplacePlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Replace tracks in a playlist.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  /**
   * Builder class for building a {@link ReplacePlaylistsTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link ReplacePlaylistsTracksRequest.Builder}.
     * <p>
     * Replacing tracks in the current user's public playlists requires authorization of the
     * {@code playlist-modify-public} scope; replacing tracks in the current user's private playlist (including
     * collaborative playlists) requires the {@code playlist-modify-private} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID setter.
     *
     * @param user_id The user's Spotify user ID.
     * @return A {@link ReplacePlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link ReplacePlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The track URIs setter.
     *
     * @param uris Optional. A comma-separated list of Spotify track URIs to set. Maximum: 100 track URIs.
     * @return A {@link ReplacePlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final String uris) {
      assert (uris != null);
      assert (!uris.equals(""));
      return setQueryParameter("uris", uris);
    }

    /**
     * The track URIs setter.
     * <p>
     * <b>Note:</b> If the URIs have already been set with {@link #uris(String)}, any URIs set here will be ignored.
     *
     * @param uris Optional. A JSON array of Spotify track URIs to set. Maximum: 100 track URIs.
     * @return A {@link ReplacePlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      assert (uris.size() <= 100);
      return setBodyParameter("uris", uris);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link ReplacePlaylistsTracksRequest}.
     */
    @Override
    public ReplacePlaylistsTracksRequest build() {
      setHeader("Content-Type", "application/json");
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/tracks");
      return new ReplacePlaylistsTracksRequest(this);
    }
  }
}
