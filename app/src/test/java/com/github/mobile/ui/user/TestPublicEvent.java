package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertNull;

import com.github.mobile.ui.user.builder.EventBuilder;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPublicEvent {

    @Test
    public void icon_should_be_null() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_PUBLIC).build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertNull(icon);
    }
}
