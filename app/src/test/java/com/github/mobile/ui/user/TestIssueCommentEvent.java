package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestIssueCommentEvent {

    public static final int ISSUE_NUMBER = 1;
    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_ISSUE_COMMENT).
            withPayload(new IssueCommentPayloadBuilder().defaultStubPayload());
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_issue_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_COMMENT, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForIssueComment").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForIssueComment");
        verify(mockMainStyledText).append(" commented on ");
    }

    @Test
    public void pull_request_issue_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubIssueCommentPayloadWithIssueNumber(ISSUE_NUMBER).
                        withPullRequest()).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("pull request " + ISSUE_NUMBER);
    }

    @Test
    public void other_issue_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubIssueCommentPayloadWithIssueNumber(ISSUE_NUMBER).
                        withOutPullRequest()).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("issue " + ISSUE_NUMBER);
    }

    @Test
    public void repo_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withRepo("RepoForIssueComment").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append(" on ");
        verify(mockMainStyledText).bold("RepoForIssueComment");
    }

    private IssueCommentPayloadBuilder stubIssueCommentPayloadWithIssueNumber(int issueNumber) {
        return new IssueCommentPayloadBuilder().withIssueNumber(issueNumber);
    }
}
