package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPublicEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_PUBLIC);
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_null() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertNull(icon);
    }

    @Test
    public void actor_and_repo_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForPublic").
                        withRepo("RepoForPublic").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForPublic");
        verify(mockMainStyledText).append(" open sourced repository ");
        verify(mockMainStyledText).bold("RepoForPublic");
    }
}
