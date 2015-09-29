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

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_gist() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_GIST).
                        withPayload(new GistPayloadBuilder().defaultStubPayload()).
                        build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_GIST, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_GIST).
                        withPayload(new GistPayloadBuilder().defaultStubPayload()).
                        withLoginUserName("LoginUserNameForGist").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForGist");
        verify(mockMainStyledText).append(' ');
    }
}
