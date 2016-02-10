package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PushPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestPushEvent {

    private static final int MAX_NUMBER_OF_COMMITS_TO_APPEND = 3;
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_push() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_PUSH, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEvent().
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
                aPushEventWithRef("RefForPush"),
                mockMainStyledText,
                stubDetailsStyledText());

        verifyRefBoldAndAppend("RefForPush", " at ");
    }

    @Test
    public void ref_should_be_truncated_when_start_with_refs_heads() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithRef("RestOfRef"),
                mockMainStyledText,
                stubDetailsStyledText());

        verifyRefBoldAndAppend("RestOfRef", " at ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEvent().
                        withRepo("RepoForPush").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForPush");
    }

    @Test
    public void one_new_commit_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithNumberOfCommit(1).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("1 new commit");
    }

    @Test
    public void two_new_commits_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithNumberOfCommit(2).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("2");
        verify(mockDetailsStyledText).append(" new commits");
    }

    @Test
    public void sha_should_be_monospace_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithCommitSha("len<=7").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyShaMonospace("len<=7");
    }

    @Test
    public void sha_longer_than_7_should_be_truncated_and_monospace_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithCommitSha("longerThan7").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyShaMonospace("longerT");
    }

    @Test
    public void message_without_new_line_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithCommitMessage("message without new line").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyMessageAppended("message without new line");
    }

    @Test
    public void message_with_new_line_should_be_truncated_and_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithCommitMessage("first line" + "\n" + "second line").
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyMessageAppended("first line");
    }

    @Test
    public void maximum_three_commits_should_be_all_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithNumberOfCommitAndMessage(MAX_NUMBER_OF_COMMITS_TO_APPEND).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyOnlyMaximumCommitsAppendedToDetails();
    }

    @Test
    public void fourth_commit_should_not_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aPushEventWithNumberOfCommitAndMessage(MAX_NUMBER_OF_COMMITS_TO_APPEND + 1).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verifyOnlyMaximumCommitsAppendedToDetails();
    }

    private void verifyOnlyMaximumCommitsAppendedToDetails() {
        verify(mockDetailsStyledText, times(MAX_NUMBER_OF_COMMITS_TO_APPEND)).append("MessageForPush");
    }

    private void verifyMessageAppended(String text2) {
        verify(mockDetailsStyledText).append(' ');
        verify(mockDetailsStyledText).append(text2);
    }

    private Event aPushEventWithRef(String ref) {
        return aPushEvent().with(aPushPayload().
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

    private PushPayloadBuilder aPushPayload() {
        return new PushPayloadBuilder();
    }

    private EventBuilder aPushEvent() {
        return new EventBuilder(Event.TYPE_PUSH).with(aPushPayload());
    }

    private EventBuilder aPushEventWithNumberOfCommit(int number) {
        return aPushEvent().with(aPushPayload().withNumberOfCommits(number));
    }

    private EventBuilder aPushEventWithCommitSha(String sha) {
        return aPushEvent().with(aPushPayload().withNumberOfCommits(1).withCommitSha(sha));
    }

    private EventBuilder aPushEventWithCommitMessage(String message) {
        return aPushEvent().with(aPushPayload().withNumberOfCommits(1).withCommitMessage(message));
    }

    private EventBuilder aPushEventWithNumberOfCommitAndMessage(int number) {
        return aPushEvent().with(aPushPayload().
                withCommitMessage("MessageForPush").
                withNumberOfCommits(number));
    }

}
