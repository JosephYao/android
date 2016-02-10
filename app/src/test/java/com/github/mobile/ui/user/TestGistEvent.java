package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.GistPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestGistEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_gist() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aGistEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_GIST, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aGistEvent().
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
                aGistEventWithAction("create").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("created");
    }

    @Test
    public void update_action_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aGistEventWithAction("update").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("updated");
    }

    @Test
    public void other_action_should_be_appended_to_main_without_change() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aGistEventWithAction("otherAction").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append("otherAction");
    }

    @Test
    public void gist_id_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aGistEventWithGistId("GistId").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append(" Gist ");
        verify(mockMainStyledText).append("GistId");
    }

    private EventBuilder aGistEventWithGistId(String gistId) {
        return aGistEvent().with(aGistPayload().withGistId(gistId));
    }

    private GistPayloadBuilder aGistPayload() {
        return new GistPayloadBuilder();
    }

    private EventBuilder aGistEvent() {
        return new EventBuilder(Event.TYPE_GIST).with(aGistPayload());
    }

    private EventBuilder aGistEventWithAction(String action) {
        return aGistEvent().with(aGistPayload().withAction(action));
    }

}
