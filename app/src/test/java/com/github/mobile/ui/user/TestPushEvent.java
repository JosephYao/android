package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PushPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPushEvent {

    private final PushPayloadBuilder stubPayload = new PushPayloadBuilder().defaultStubPayload();
    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_PUSH).
            withPayload(stubPayload);
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_push() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_PUSH, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForPush").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForPush");
        verify(mockMainStyledText).append(" pushed to ");
    }

    @Test
    public void ref_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithRef("RefForPush"),
                mockMainStyledText,
                stubDetailsStyledText());

        verifyRefBoldAndAppend("RefForPush", " at ");
    }

    @Test
    public void ref_should_be_truncated_when_start_with_refs_heads() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithRef("RestOfRef"),
                mockMainStyledText,
                stubDetailsStyledText());

        verifyRefBoldAndAppend("RestOfRef", " at ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withRepo("RepoForPush").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForPush");
    }

    @Test
    public void one_new_commit_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.defaultStubPayload().
                                withNumberOfCommits(1)).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("1 new commit");
    }

    @Test
    public void two_new_commits_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.defaultStubPayload().
                                withNumberOfCommits(2)).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("2");
        verify(mockDetailsStyledText).append(" new commits");
    }

    @Test
    public void sha_should_be_monospace_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.defaultStubPayload().
                                withCommitSha("len<=7")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyShaMonospace("len<=7");
    }

    @Test
    public void sha_longer_than_7_should_be_truncated_and_monospace_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.defaultStubPayload().
                                withCommitSha("longerThan7")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyShaMonospace("longerT");
    }

    @Test
    public void message_without_new_line_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.defaultStubPayload().
                                withCommitMessage("message without new line")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append(' ');
        verify(mockDetailsStyledText).append("message without new line");
    }

    private Event stubEventWithRef(String ref) {
        return stubEvent.withPayload(stubPayload.
                withRef("refs/heads/" + ref)).
                build();
    }

    private void verifyRefBoldAndAppend(String refForPush, String text) {
        verify(mockMainStyledText).bold(refForPush);
        verify(mockMainStyledText).append(text);
    }

    private void verifyShaMonospace(String text2) {
        verify(mockDetailsStyledText).append('\n');
        verify(mockDetailsStyledText).monospace(text2);
    }

}
