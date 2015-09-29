package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestGistEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_GIST).
            withPayload(new GistPayloadBuilder().defaultStubPayload());
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_gist() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_GIST, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForGist").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForGist");
        verify(mockMainStyledText).append(' ');
    }

    @Test
    public void create_action_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new GistPayloadBuilder().defaultStubPayload().
                                withAction("create")).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("created");
    }

    @Test
    public void update_action_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new GistPayloadBuilder().defaultStubPayload().
                                withAction("update")).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("updated");
    }

    @Test
    public void other_action_should_be_appended_to_main_without_change() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new GistPayloadBuilder().defaultStubPayload().
                                withAction("otherAction")).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("otherAction");
    }

    @Test
    public void gist_id_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new GistPayloadBuilder().defaultStubPayload().
                            withGistId("GistId")).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append(" Gist ");
        verify(mockMainStyledText).append("GistId");
    }
}