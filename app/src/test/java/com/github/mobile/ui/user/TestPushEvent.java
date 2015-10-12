package com.github.mobile.ui.user;

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

    private Event stubEventWithRef(String ref) {
        return stubEvent.withPayload(stubPayload.
                withRef("refs/heads/" + ref)).
                build();
    }

    private void verifyRefBoldAndAppend(String refForPush, String text) {
        verify(mockMainStyledText).bold(refForPush);
        verify(mockMainStyledText).append(text);
    }

}
