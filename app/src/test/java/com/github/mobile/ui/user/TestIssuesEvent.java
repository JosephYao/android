package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestIssuesEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_issue_open() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("opened"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_OPEN, icon);
    }

    @Test
    public void icon_should_be_issue_reopen() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("reopened"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_REOPEN, icon);
    }

    @Test
    public void icon_should_be_issue_close() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithIssueAction("closed"),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ISSUE_CLOSE, icon);
    }

    private Event stubEventWithIssueAction(String action) {
        return new EventBuilder().defaultStubEventFor(Event.TYPE_ISSUES).
                withPayload(new IssuesPayloadBuilder().defaultStubPayload().
                        withAction(action)).
                build();
    }

}
