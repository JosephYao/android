package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;
import org.junit.Test;

public class TestCreateEvent {

    @Test
    public void icon_should_be_create() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        Event event = stubEvent(stubUser("LoginUserName"), stubRepo("Repo"));
        CreatePayload payload = mock(CreatePayload.class);
        when(event.getPayload()).thenReturn(payload);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                event,
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_CREATE, icon);
    }

    private StyledText mockDetailsStyledText() {
        return mock(StyledText.class);
    }

    private StyledText mockMainStyledText() {
        return mock(StyledText.class);
    }

    private Event stubEvent(User stubUser, EventRepository stubRepo) {
        Event stubEvent = mock(Event.class);
        when(stubEvent.getType()).thenReturn(Event.TYPE_CREATE);
        when(stubEvent.getActor()).thenReturn(stubUser);
        when(stubEvent.getRepo()).thenReturn(stubRepo);
        return stubEvent;
    }

    private EventRepository stubRepo(String repo) {
        EventRepository stubRepo = mock(EventRepository.class);
        when(stubRepo.getName()).thenReturn(repo);
        return stubRepo;
    }

    private User stubUser(String loginUserName) {
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(loginUserName);
        return stubUser;
    }
}
