package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.user.builder.PayloadBuilder;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.GistPayload;

public class GistPayloadBuilder implements PayloadBuilder {
    private String gistId;
    private String action;

    @Override
    public EventPayload build() {
        GistPayload stubPayload = mock(GistPayload.class);
        Gist stubGist = stubGist();
        when(stubPayload.getGist()).thenReturn(stubGist);
        when(stubPayload.getAction()).thenReturn(action);
        return stubPayload;
    }

    private Gist stubGist() {
        Gist stubGist = mock(Gist.class);
        when(stubGist.getId()).thenReturn(this.gistId);
        return stubGist;
    }

    public GistPayloadBuilder defaultStubPayload() {
        this.gistId = "GistId";
        return this;
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
