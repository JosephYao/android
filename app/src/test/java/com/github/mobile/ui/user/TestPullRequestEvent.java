package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PullRequestPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPullRequestEvent {

    public static final int PAYLOAD_NUMBER = 1;
    private final PullRequestPayloadBuilder stubPayload = new PullRequestPayloadBuilder().defaultStubPayload();
    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_PULL_REQUEST).withPayload(stubPayload);
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_pull_request() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_PULL_REQUEST, icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForPullRequest").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForPullRequest");
    }

    @Test
    public void action_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithAction("action").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText, times(2)).append(' ');
        verify(mockMainStyledText).append("action");
    }

    @Test
    public void synchronize_action_should_be_changed_to_updated_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEventWithAction("synchronize").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText, times(2)).append(' ');
        verify(mockMainStyledText).append("updated");
    }

    @Test
    public void payload_number_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.
                                withNumber(PAYLOAD_NUMBER)).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("pull request " + PAYLOAD_NUMBER);
        verify(mockMainStyledText).append(" on ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withRepo("RepoForPullRequest").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForPullRequest");
    }

    @Test
    public void pull_request_title_should_be_appended_to_detail_when_action_is_opened() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.
                                withAction("opened").
                                withPullRequestTitle("title")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("title");
    }

    @Test
    public void pull_request_title_should_be_appended_to_detail_when_action_is_closed() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.
                                withAction("closed").
                                withPullRequestTitle("title")).
                        build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("title");
    }

    private EventBuilder stubEventWithAction(String action) {
        return stubEvent.
                withPayload(stubPayload.
                        withAction(action));
    }

}
