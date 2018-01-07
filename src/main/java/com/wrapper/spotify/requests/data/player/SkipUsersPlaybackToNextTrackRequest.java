package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SkipUsersPlaybackToNextTrackRequest extends AbstractDataRequest {

  private SkipUsersPlaybackToNextTrackRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return postJson();
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    @Override
    public SkipUsersPlaybackToNextTrackRequest build() {
      setPath("/v1/me/player/next");
      return new SkipUsersPlaybackToNextTrackRequest(this);
    }
  }
}
