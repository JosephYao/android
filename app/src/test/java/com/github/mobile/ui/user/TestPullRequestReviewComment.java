package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PullRequestReviewCommentPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPullRequestReviewComment {

    @Test
    public void icon_should_be_comment() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_PULL_REQUEST_REVIEW_COMMENT).
                        withPayload(new PullRequestReviewCommentPayloadBuilder().defaultStubPayload()).
                        build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_COMMENT, icon);
    }
}
