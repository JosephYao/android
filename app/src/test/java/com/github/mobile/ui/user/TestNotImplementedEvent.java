package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.fail;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.user.builder.EventBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestNotImplementedEvent {

    private final IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test(expected = IllegalStateException.class)
    public void not_implemented_event_type() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aNotImplementedEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        fail();
    }

    private EventBuilder aNotImplementedEvent() {
        return new EventBuilder("not implemented");
    }

}