package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWatchEvent {

    private IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_star() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aWatchEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_STAR, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aWatchEvent()
                        .withLoginUserName("LoginUserNameForWatch")
                        .build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForWatch");
        verify(mockMainStyledText).append(" starred ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aWatchEvent()
                        .withRepo("RepoForWatch")
                        .build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForWatch");
    }

    private EventBuilder aWatchEvent() {
        return new EventBuilder(Event.TYPE_WATCH);
    }

}
