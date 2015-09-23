package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestCreateEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_CREATE).
            withPayload(new CreatePayloadBuilder().defaultStubPayload());
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_create() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                mockMainStyledText,
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_CREATE, icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForCreate").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForCreate");
        verify(mockMainStyledText).append(" created ");
    }

    @Test
    public void ref_type_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new CreatePayloadBuilder().defaultStubPayload().
                                withRefType("refType")).
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).append("refType");
        verify(mockMainStyledText).append(' ');
    }

    @Test
    public void ref_should_be_appended_to_main_and_repo_should_be_bold_to_main_when_ref_type_is_not_repository() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new CreatePayloadBuilder().defaultStubPayload().
                                withRefType("refType").
                                withRef("ref")).
                        withRepo("RepoForCreate").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).append("ref");
        verify(mockMainStyledText).append(" at ");
        verify(mockMainStyledText).bold("RepoForCreate");
    }

    @Test
    public void repo_should_be_trimmed_from_slash_and_bold_to_main_when_ref_type_is_repository() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new CreatePayloadBuilder().defaultStubPayload().
                                withRefType("repository")).
                        withRepo("Repos/RepoForCreate").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForCreate");
    }

    @Test
    public void repo_should_not_be_bold_to_main_when_repo_name_has_slash_as_the_last_character() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("willBeBold").
                        withPayload(new CreatePayloadBuilder().defaultStubPayload().
                                withRefType("repository")).
                        withRepo("WillNotBeBold/").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verifyNoRepoNameIsBold();
    }

    private void verifyNoRepoNameIsBold() {
        verify(mockMainStyledText, times(1)).bold(anyString());
    }

}
