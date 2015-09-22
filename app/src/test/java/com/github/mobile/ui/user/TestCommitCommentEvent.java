package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestCommitCommentEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_COMMIT_COMMENT);
    private CommitCommentPayloadBuilder stubPayload = new CommitCommentPayloadBuilder().defaultStubPayload();

    @Test
    public void icon_should_be_comment() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }

    @Test
    public void actor_commented_on_repo_should_be_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent
                        .withLoginUserName("LoginUserName")
                        .withRepo("Repo")
                        .build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserName");
        verify(mockMainStyledText).append(" commented on ");
        verify(mockMainStyledText).bold("Repo");
    }

    @Test
    public void comment_id_should_be_appended_to_details_without_change_when_comment_id_is_10_characters_long() {
        StyledText mockDetailsStyledText = mockDetailsStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubPayload.
                        withCommentId("10chlongId")).
                        build(),
                mockMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails(mockDetailsStyledText, "10chlongId");
    }

    @Test
    public void comment_id_should_be_trimmed_and_appended_to_details_when_comment_id_is_longer_than_10_characters() {
        StyledText mockDetailsStyledText = mockDetailsStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubPayload.
                        withCommentId("longerthan10charId")).
                        build(),
                mockMainStyledText(),
                mockDetailsStyledText);

        verifyTextAppendedToDetails(mockDetailsStyledText, "longerthan");
    }

    @Test
    public void comment_should_be_appended_to_details() {
        StyledText mockDetailsStyledText = mockDetailsStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubPayload.
                        withComment("comment")).
                        build(),
                mockMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("comment");
    }

    private void verifyTextAppendedToDetails(StyledText mockDetailsStyledText, String text3) {
        verify(mockDetailsStyledText).append("Comment in");
        verify(mockDetailsStyledText).append(' ');
        verify(mockDetailsStyledText).monospace(text3);
        verify(mockDetailsStyledText).append(':');
        verify(mockDetailsStyledText).append('\n');
    }

    private StyledText mockDetailsStyledText() {
        StyledText mock = mock(StyledText.class);
        when(mock.append(anyChar())).thenReturn(mock);
        return mock;
    }

    private StyledText mockMainStyledText() {
        return mock(StyledText.class);
    }

}