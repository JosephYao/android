package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.CreatePayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestCreateEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_create() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_CREATE, icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEvent().
                        withLoginUserName("LoginUserNameForCreate").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForCreate");
        verify(mockMainStyledText).append(" created ");
    }

    @Test
    public void ref_type_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEventWithRefType("refType").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("refType");
        verify(mockMainStyledText).append(' ');
    }

    @Test
    public void ref_should_be_appended_to_main_and_repo_should_be_bold_to_main_when_ref_type_is_not_repository() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEventWithRefTypeAndRef("refType", "ref").
                        withRepo("RepoForCreate").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("ref");
        verify(mockMainStyledText).append(" at ");
        verify(mockMainStyledText).bold("RepoForCreate");
    }

    @Test
    public void repo_should_be_trimmed_from_slash_and_bold_to_main_when_ref_type_is_repository() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEventWithRefType("repository").
                        withRepo("Repos/RepoForCreate").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForCreate");
    }

    @Test
    public void repo_should_not_be_bold_to_main_when_repo_name_has_slash_as_the_last_character() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aCreateEventWithRefType("repository").
                        withLoginUserName("willBeBold").
                        withRepo("WillNotBeBold/").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verifyNoRepoNameIsBold();
    }

    private void verifyNoRepoNameIsBold() {
        verify(mockMainStyledText, times(1)).bold(anyString());
    }

    private EventBuilder aCreateEvent() {
        return new EventBuilder(Event.TYPE_CREATE).withPayload(aCreatePayload());
    }

    private CreatePayloadBuilder aCreatePayload() {
        return new CreatePayloadBuilder();
    }

    private EventBuilder aCreateEventWithRefType(String refType) {
        return aCreateEvent().withPayload(aCreatePayload().
                withRefType(refType));
    }

    private EventBuilder aCreateEventWithRefTypeAndRef(String refType, String ref) {
        return aCreateEvent().withPayload(aCreatePayload().
                withRefType(refType).
                withRef(ref));
    }
}
