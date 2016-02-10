package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.CommitCommentPayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestCommitCommentEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aCommitCommentEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_commented_on_repo_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCommitCommentEvent()
                        .withLoginUserName("LoginUserName")
                        .withRepo("Repo")
                        .build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserName");
        verify(mockMainStyledText).append(" commented on ");
        verify(mockMainStyledText).bold("Repo");
    }

    @Test
    public void commit_id_should_be_appended_to_details_without_change_when_commit_id_is_10_characters_long() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCommitCommentEventWithCommitId("10chlongId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("10chlongId");
    }

    @Test
    public void commit_id_should_be_trimmed_and_appended_to_details_when_commit_id_is_longer_than_10_characters() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCommitCommentEventWithCommitId("longerthan10charId").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails("longerthan");
    }

    @Test
    public void comment_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCommitCommentEventWithComment("comment").
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

    private EventBuilder aCommitCommentEvent() {
        return new EventBuilder(Event.TYPE_COMMIT_COMMENT).with(aCommitCommentPayload());
    }

    private CommitCommentPayloadBuilder aCommitCommentPayload() {
        return new CommitCommentPayloadBuilder();
    }

    private EventBuilder aCommitCommentEventWithCommitId(String commitId) {
        return aCommitCommentEvent().with(aCommitCommentPayload().withCommitId(commitId));
    }

    private EventBuilder aCommitCommentEventWithComment(String comment) {
        return aCommitCommentEvent().with(aCommitCommentPayload().withComment(comment));
    }

}