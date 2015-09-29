package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.user.builder.PayloadBuilder;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.GistPayload;

public class GistPayloadBuilder implements PayloadBuilder {
    private String gistId;

    @Override
    public EventPayload build() {
        GistPayload stubPayload = mock(GistPayload.class);
        Gist stubGist = mock(Gist.class);
        when(stubGist.getId()).thenReturn(this.gistId);
        when(stubPayload.getGist()).thenReturn(stubGist);
        return stubPayload;
    }

    public GistPayloadBuilder defaultStubPayload() {
        this.gistId = "GistId";
        return this;
    }
}
