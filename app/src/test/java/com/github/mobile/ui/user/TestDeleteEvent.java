package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.DeletePayloadBuilder;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestDeleteEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final DeletePayloadBuilder stubPayload = new DeletePayloadBuilder().defaultStubPayload();
    private final EventBuilder stubEvent = new EventBuilder().
            defaultStubEventFor(Event.TYPE_DELETE).
            withPayload(stubPayload);
    StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_delete() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_DELETE, icon);
    }


    @Test
    public void actor_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForDelete").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForDelete");
    }

    @Test
    public void payload_ref_type_and_ref_should_be_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(stubPayload.
                                withRefType("RefType").
                                withRef("Ref")).
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).append(" deleted ");
        verify(mockMainStyledText).append("RefType");
        verify(mockMainStyledText).append(' ');
        verify(mockMainStyledText).append("Ref");
        verify(mockMainStyledText).append(" at ");
    }

    @Test
    public void repo_should_be_bold_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withRepo("RepoForDelete").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("RepoForDelete");
    }
}
