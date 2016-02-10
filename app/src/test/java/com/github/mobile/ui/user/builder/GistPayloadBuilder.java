package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.GistPayload;

public class GistPayloadBuilder implements PayloadBuilder {
    private String gistId = "GistId";
    private String action;

    @Override
    public EventPayload build() {
        GistPayload gistPayload = new GistPayload();
        gistPayload.setAction(action);
        gistPayload.setGist(gist());
        return gistPayload;
    }

    private Gist gist() {
        Gist gist = new Gist();
        gist.setId(gistId);
        return gist;
    }

    public GistPayloadBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public GistPayloadBuilder withGistId(String gistId) {
        this.gistId = gistId;
        return this;
    }
}
