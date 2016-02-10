package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.DeletePayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestDeleteEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_delete() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aDeleteEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_DELETE, icon);
    }


    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aDeleteEvent().
                        withLoginUserName("LoginUserNameForDelete").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForDelete");
    }

    @Test
    public void payload_ref_type_and_ref_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aDeleteEventWithRefTypeAndRef("RefType", "Ref").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append(" deleted ");
        verify(mockMainStyledText).append("RefType");
        verify(mockMainStyledText).append(' ');
        verify(mockMainStyledText).append("Ref");
        verify(mockMainStyledText).append(" at ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aDeleteEvent().
                        withRepo("RepoForDelete").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForDelete");
    }

    private DeletePayloadBuilder aDeletePayload() {
        return new DeletePayloadBuilder();
    }

    private EventBuilder aDeleteEvent() {
        return new EventBuilder(Event.TYPE_DELETE).withPayload(aDeletePayload());
    }

    private EventBuilder aDeleteEventWithRefTypeAndRef(String refType, String ref) {
        return aDeleteEvent().withPayload(aDeletePayload().
                withRefType(refType).
                withRef(ref));
    }
}
