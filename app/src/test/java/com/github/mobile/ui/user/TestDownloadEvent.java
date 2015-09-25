package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestDownloadEvent {

    @Test
    public void icon_should_be_upload() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_DOWNLOAD).
                        withPayload(new DownloadPayloadBuilder().defaultStubPayload()).
                        build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_UPLOAD, icon);
    }
}
