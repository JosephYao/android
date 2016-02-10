package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.DownloadPayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestDownloadEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();
    private final StyledText mockDetailsStyledText = mockDetailsStyledText();

    @Test
    public void icon_should_be_upload() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aDownloadEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_UPLOAD, icon);
    }

    @Test
    public void actor_upload_to_repo_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aDownloadEvent()
                        .withLoginUserName("LoginUserNameForDownload")
                        .withRepo("RepoForDownload")
                        .build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForDownload");
        verify(mockMainStyledText).append(" uploaded a file to ");
        verify(mockMainStyledText).bold("RepoForDownload");
    }

    @Test
    public void download_name_should_be_appended_to_details() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aDownloadEventWithDownloadName("Download")
                        .build(),
                stubMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("Download");
    }

    private EventBuilder aDownloadEventWithDownloadName(String name) {
        return aDownloadEvent().with(aDownloadPayload().withDownload(name));
    }

    private DownloadPayloadBuilder aDownloadPayload() {
        return new DownloadPayloadBuilder();
    }

    private EventBuilder aDownloadEvent() {
        return new EventBuilder(Event.TYPE_DOWNLOAD).with(aDownloadPayload());
    }

}
