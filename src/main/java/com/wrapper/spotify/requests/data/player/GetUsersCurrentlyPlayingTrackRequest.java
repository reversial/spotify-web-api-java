package com.wrapper.spotify.requests.data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersCurrentlyPlayingTrackRequest extends AbstractDataRequest {

  private GetUsersCurrentlyPlayingTrackRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public CurrentlyPlaying execute() throws
          IOException,
          SpotifyWebApiException {
    return new CurrentlyPlaying.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      setPath("/v1/me/player/currently-playing");
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }
  }
}
