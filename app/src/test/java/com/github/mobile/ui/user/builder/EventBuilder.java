package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.EventRepository;

public class EventBuilder {

    private UserBuilder userBuilder;
    private String repo;
    private PayloadBuilder payloadBuilder;
    private String type;

    public EventBuilder(String type) {
        this.type = type;
        this.userBuilder = new UserBuilder();
    }

    public EventBuilder() {
    }

    public EventBuilder defaultStubEventFor(String type) {
        this.type = type;
        this.payloadBuilder = new CommitCommentPayloadBuilder();
        this.userBuilder = new UserBuilder();
        return this;
    }

    public Event build() {
        Event stubEvent = mock(Event.class);
        when(stubEvent.getType()).thenReturn(type);

        /**
         * this local variable is needed, DO NOT inline it, otherwise test code will fail. Please see http://stackoverflow.com/a/26319364 for more details.
         * Same for stubPayload and stubRepo.
         */
        User stubUser = userBuilder.build();
        when(stubEvent.getActor()).thenReturn(stubUser);

        if (payloadBuilder != null) {
            EventPayload stubPayload = payloadBuilder.build();
            when(stubEvent.getPayload()).thenReturn(stubPayload);
        }

        EventRepository stubRepo = stubRepo();
        when(stubEvent.getRepo()).thenReturn(stubRepo);
        return stubEvent;
    }

    private EventRepository stubRepo() {
        EventRepository stubRepo = mock(EventRepository.class);
        when(stubRepo.getName()).thenReturn(repo);
        return stubRepo;
    }

    public EventBuilder with(PayloadBuilder payloadBuilder) {
        this.payloadBuilder = payloadBuilder;
        return this;
    }

    public EventBuilder withLoginUserName(String loginUserName) {
        userBuilder.withLoginUserName(loginUserName);
        return this;
    }

    public EventBuilder withRepo(String repo) {
        this.repo = repo;
        return this;
    }
}
