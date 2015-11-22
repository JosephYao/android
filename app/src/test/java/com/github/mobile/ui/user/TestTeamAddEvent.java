package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.TeamAddPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestTeamAddEvent {

    private final TeamAddPayloadBuilder stubPayload = new TeamAddPayloadBuilder().defaultStubPayload();
    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_TEAM_ADD).
            withPayload(stubPayload).
            withRepo("RepoForTeamAdd");
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_add_member() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_ADD_MEMBER, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserForTeamAdd").build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserForTeamAdd");
        verify(mockMainStyledText).append(" added ");
    }

    @Test
    public void payload_user_should_be_bold_to_main_if_not_empty() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withPayload(stubPayload.
                        withUser("PayloadUserForTeamAdd")).build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("PayloadUserForTeamAdd");
        verify(mockMainStyledText).append(" to team");
    }

    @Test
    public void repo_name_should_be_bold_to_main_if_payload_user_is_empty_and_repo_name_exists() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.withRepo("Repos/RepoForTeamAdd").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForTeamAdd");
        verify(mockMainStyledText).append(" to team");
    }

}
