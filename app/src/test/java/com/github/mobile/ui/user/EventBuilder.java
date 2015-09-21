package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.EventRepository;

public class EventBuilder {

    private String loginUserName;
    private String repoName;
    private PayloadBuilder payloadBuilder;
    private String type;

    public EventBuilder defaultStubEventFor(String type) {
        this.type = type;
        this.loginUserName = "LoginUserName";
        this.repoName = "Repo";
        this.payloadBuilder = new CommitCommentPayloadBuilder().defaultStubPayload();
        return this;
    }

    public Event build() {
        Event stubEvent = mock(Event.class);
        when(stubEvent.getType()).thenReturn(type);

        /**
         * this local variable is needed, DO NOT inline it, otherwise test code will fail. Please see http://stackoverflow.com/a/26319364 for more details.
         * Same for stubPayload and stubRepo.
         */
        User stubUser = stubUser();
        when(stubEvent.getActor()).thenReturn(stubUser);

        EventPayload stubPayload = payloadBuilder.build();
        when(stubEvent.getPayload()).thenReturn(stubPayload);

        EventRepository stubRepo = stubRepo();
        when(stubEvent.getRepo()).thenReturn(stubRepo);
        return stubEvent;
    }

    private User stubUser() {
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(loginUserName);
        return stubUser;
    }

    private EventRepository stubRepo() {
        EventRepository stubRepo = mock(EventRepository.class);
        when(stubRepo.getName()).thenReturn(repoName);
        return stubRepo;
    }

    public EventBuilder withPayload(PayloadBuilder payloadBuilder) {
        this.payloadBuilder = payloadBuilder;
        return this;
    }
}
