package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PullRequestReviewCommentPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPullRequestReviewCommentEvent {

    private final PullRequestReviewCommentPayloadBuilder stubPayload = new PullRequestReviewCommentPayloadBuilder()
            .defaultStubPayload();
    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event
            .TYPE_PULL_REQUEST_REVIEW_COMMENT).
            withPayload(stubPayload);
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_and_repo_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForPullRequestReviewComment").
                        withRepo("RepoForPullRequestReviewComment").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForPullRequestReviewComment");
        verify(mockMainStyledText).append(" commented on ");
        verify(mockMainStyledText).bold("RepoForPullRequestReviewComment");
    }

    @Test
    public void commit_id_should_be_appended_to_details_without_change_when_commit_id_is_10_characters_long() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithCommitId("10chlongId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("10chlongId");
    }

    @Test
    public void commit_id_should_be_trimmed_and_appended_to_details_when_commit_id_is_longer_than_10_characters() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithCommitId("longerthan10charId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("longerthan");
    }

    @Test
    public void comment_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubPayload.
                        withComment("comment")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("comment");
    }

    private void verifyTextAppendedToDetails(String commitId) {
        verify(mockDetailsStyledText).append("Comment in");
        verify(mockDetailsStyledText).append(' ');
        verify(mockDetailsStyledText).monospace(commitId);
        verify(mockDetailsStyledText).append(':');
        verify(mockDetailsStyledText).append('\n');
    }

    private EventBuilder stubEventWithCommitId(String commitId) {
        return stubEvent.withPayload(stubPayload.withCommitId(commitId));
    }

}
