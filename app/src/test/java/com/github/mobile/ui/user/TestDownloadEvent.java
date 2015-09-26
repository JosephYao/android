package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.DownloadPayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestDownloadEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_DOWNLOAD).
            withPayload(new DownloadPayloadBuilder().defaultStubPayload());
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_upload() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_UPLOAD, icon);
    }

    @Test
    public void actor_upload_to_repo_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent
                        .withLoginUserName("LoginUserNameForDownload")
                        .withRepo("RepoForDownload")
                        .build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForDownload");
        verify(mockMainStyledText).append(" uploaded a file to ");
        verify(mockMainStyledText).bold("RepoForDownload");
    }

    @Test
    public void download_name_should_be_appended_to_details() {
        StyledText mockDetailsStyledText = mockDetailsStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent
                        .withPayload(new DownloadPayloadBuilder().defaultStubPayload().
                            withDownload("Download"))
                        .build(),
                mockMainStyledText(),
                mockDetailsStyledText);

        verify(mockDetailsStyledText).append("Download");
    }
}
