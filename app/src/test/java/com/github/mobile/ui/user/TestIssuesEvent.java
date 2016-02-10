package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.IssuesPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestIssuesEvent {

    private static final int ISSUE_NUMBER = 1;

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_issue_open() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithAction("opened").build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_OPEN, icon);
    }

    @Test
    public void icon_should_be_issue_reopen() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithAction("reopened").build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_REOPEN, icon);
    }

    @Test
    public void icon_should_be_issue_close() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithAction("closed").build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_CLOSE, icon);
    }

    @Test
    public void icon_should_be_null_when_any_other_action() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithAction("otherAction").build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertNull(icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEvent().
                        withLoginUserName("LoginUserNameForIssues").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForIssues");
    }

    @Test
    public void action_should_be_append_and_issue_number_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithActionAndIssueNumber("action", ISSUE_NUMBER).
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
                anIssuesEvent().
                        withRepo("RepoForIssues").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForIssues");
    }

    @Test
    public void issue_title_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                anIssuesEventWithIssueTitle("IssueTitle").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("IssueTitle");
    }

    private EventBuilder anIssuesEventWithIssueTitle(String issueTitle) {
        return anIssuesEvent().with(anIssuesPayload().withIssueTitle(issueTitle));
    }

    private IssuesPayloadBuilder anIssuesPayload() {
        return new IssuesPayloadBuilder();
    }

    private EventBuilder anIssuesEvent() {
        return new EventBuilder(Event.TYPE_ISSUES).with(anIssuesPayload());
    }

    private EventBuilder anIssuesEventWithActionAndIssueNumber(String action, int issueNumber) {
        return anIssuesEvent().with(anIssuesPayload().
                withAction(action).
                withIssueNumber(issueNumber));
    }

    private EventBuilder anIssuesEventWithAction(String action) {
        return anIssuesEvent().with(anIssuesPayload().withAction(action));
    }

}
