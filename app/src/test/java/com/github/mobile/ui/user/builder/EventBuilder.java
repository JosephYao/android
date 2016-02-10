package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;

public class EventBuilder {

    private UserBuilder userBuilder = new UserBuilder();
    private String repo;
    private PayloadBuilder payloadBuilder = new NullPayloadBuilder();
    private String type;

    public EventBuilder(String type) {
        this.type = type;
    }

    public Event build() {
        Event event = new Event();
        event.setType(type);
        event.setActor(userBuilder.build());
        event.setPayload(payloadBuilder.build());
        event.setRepo(repo());
        return event;
    }

    private EventRepository repo() {
        EventRepository repo = new EventRepository();
        repo.setName(this.repo);
        return repo;
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
