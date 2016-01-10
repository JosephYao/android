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
public class TestGollumEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_GOLLUM);
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_wiki() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_WIKI, icon);
    }

    @Test
    public void actor_and_repo_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForWiki").
                        withRepo("RepoForWiki").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForWiki");
        verify(mockMainStyledText).append(" updated the wiki in ");
        verify(mockMainStyledText).bold("RepoForWiki");
    }
}
