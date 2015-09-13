package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestCommitCommentEvent {

    @Test
    public void icon_should_be_comment() throws Exception {
        // Arrange
        Event event = new Event();
        event.setType(Event.TYPE_COMMIT_COMMENT);

        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
        IconAndViewTextManager spyIconAndViewTextManager = spy(iconAndViewTextManager);
        doNothing().when(spyIconAndViewTextManager).formatCommitComment(event, null, null);

        // Act
        String icon = spyIconAndViewTextManager.setIconAndFormatStyledText(event, null, null);

        // Assert
        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_should_be_bold() throws Exception {
        // Arrange
        Event stubEvent = mock(Event.class);
        when(stubEvent.getType()).thenReturn(Event.TYPE_COMMIT_COMMENT);
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn("LoginUserName");
        when(stubEvent.getActor()).thenReturn(stubUser);
        CommitCommentPayload stubPayload = mock(CommitCommentPayload.class);
        when(stubEvent.getPayload()).thenReturn(stubPayload);

        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
        StyledText mockMain = mock(StyledText.class);
        StyledText mockDetails = mock(StyledText.class);

        // Act
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(stubEvent, mockMain, mockDetails);

        // Assert
        verify(mockMain).bold("LoginUserName");
    }

}