package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import java.lang.String;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;
import org.junit.Test;

public class TestCommitCommentEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent(stubUser("LoginUserName")),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_should_be_bold_without_payload_commit_comment() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent(stubUser("LoginUserName")),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserName");
    }

    @Test
    public void repo_should_be_bold_without_payload_commit_comment() {
        StyledText mockMainStyledText = mockMainStyledText();

        Event stubEvent = stubEvent(stubUser("LoginUserName"));
        EventRepository stubRepo = stubRepo();
        when(stubEvent.getRepo()).thenReturn(stubRepo);
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent,
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("Repo");
    }

    private EventRepository stubRepo() {
        EventRepository stubRepo = mock(EventRepository.class);
        when(stubRepo.getName()).thenReturn("Repo");
        return stubRepo;
    }

    private StyledText mockDetailsStyledText() {
        return mock(StyledText.class);
    }

    private StyledText mockMainStyledText() {
        return mock(StyledText.class);
    }

    private Event stubEvent(User stubUser) {
        Event stubEvent = mock(Event.class);
        when(stubEvent.getType()).thenReturn(Event.TYPE_COMMIT_COMMENT);
        when(stubEvent.getActor()).thenReturn(stubUser);
        when(stubEvent.getPayload()).thenReturn(stubPayload());
        return stubEvent;
    }

    private CommitCommentPayload stubPayload() {
        return mock(CommitCommentPayload.class);
    }

    private User stubUser(String loginUserName) {
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(loginUserName);
        return stubUser;
    }

}