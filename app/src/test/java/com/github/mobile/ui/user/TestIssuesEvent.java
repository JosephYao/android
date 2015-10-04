package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestIssuesEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_issue_open() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("opened"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_OPEN, icon);
    }

    @Test
    public void icon_should_be_issue_reopen() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("reopened"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_REOPEN, icon);
    }

    @Test
    public void icon_should_be_issue_close() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("closed"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_CLOSE, icon);
    }

    @Test
    public void icon_should_be_null_when_any_other_action() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("otherAction"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertNull(icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_ISSUES).
                        withPayload(new IssuesPayloadBuilder().defaultStubPayload()).
                        withLoginUserName("LoginUserNameForIssues").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForIssues");
    }

    private Event stubEventWithIssueAction(String action) {
        return new EventBuilder().defaultStubEventFor(Event.TYPE_ISSUES).
                withPayload(new IssuesPayloadBuilder().defaultStubPayload().
                        withAction(action)).
                build();
    }

}
