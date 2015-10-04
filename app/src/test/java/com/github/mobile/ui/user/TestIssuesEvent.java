package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestIssuesEvent {

    public static final int ISSUE_NUMBER = 1;
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

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
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent(stubPayload()).
                        withLoginUserName("LoginUserNameForIssues").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForIssues");
    }

    @Test
    public void action_should_be_append_and_issue_number_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent(stubPayload().
                        withAction("action").
                        withIssueNumber(ISSUE_NUMBER)).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText, times(2)).append(' ');
        verify(mockMainStyledText).append("action");
        verify(mockMainStyledText).bold("issue " + ISSUE_NUMBER);
        verify(mockMainStyledText).append(" on ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent(stubPayload()).
                        withRepo("RepoForIssues").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForIssues");
    }

    private IssuesPayloadBuilder stubPayload() {
        return new IssuesPayloadBuilder().defaultStubPayload();
    }

    private Event stubEventWithIssueAction(String action) {
        return stubEvent(stubPayload().
                withAction(action)).
                build();
    }

    private EventBuilder stubEvent(IssuesPayloadBuilder payloadBuilder) {
        return new EventBuilder().defaultStubEventFor(Event.TYPE_ISSUES).
                withPayload(payloadBuilder);
    }

}
