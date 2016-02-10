package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PullRequestReviewCommentPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestPullRequestReviewCommentEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aPullRequestReviewCommentEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_and_repo_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPullRequestReviewCommentEvent().
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
                aPullRequestReviewCommentEventWithCommitId("10chlongId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("10chlongId");
    }

    @Test
    public void commit_id_should_be_trimmed_and_appended_to_details_when_commit_id_is_longer_than_10_characters() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPullRequestReviewCommentEventWithCommitId("longerthan10charId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("longerthan");
    }

    @Test
    public void comment_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPullRequestCommentEventWithComment("comment").
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

    private EventBuilder aPullRequestReviewCommentEventWithCommitId(String commitId) {
        return aPullRequestReviewCommentEvent().with(aPullRequestReviewCommentPayload().withCommitId(commitId));
    }

    private PullRequestReviewCommentPayloadBuilder aPullRequestReviewCommentPayload() {
        return new PullRequestReviewCommentPayloadBuilder();
    }

    private EventBuilder aPullRequestReviewCommentEvent() {
        return new EventBuilder(Event.TYPE_PULL_REQUEST_REVIEW_COMMENT).with(aPullRequestReviewCommentPayload());
    }

    private EventBuilder aPullRequestCommentEventWithComment(String comment) {
        return aPullRequestReviewCommentEvent().with(aPullRequestReviewCommentPayload().withComment(comment));
    }

}
