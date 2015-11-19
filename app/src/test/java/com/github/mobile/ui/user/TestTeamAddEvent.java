package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.TeamAddPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestTeamAddEvent {

    @Test
    public void icon_should_be_add_member() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_TEAM_ADD).
                        withPayload(new TeamAddPayloadBuilder().defaultStubPayload()).
                        withRepo("RepoForTeamAdd").build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ADD_MEMBER, icon);
    }
}
