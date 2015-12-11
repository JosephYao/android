package com.github.mobile.ui.user.builder;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.StyledText;

public class StyledTextDataMother {

    public static StyledText mockMainStyledText() {
        StyledText mock = mock(StyledText.class);
        when(mock.append(anyChar())).thenReturn(mock);
        return mock;
    }

    public static StyledText stubMainStyledText() {
        return mockMainStyledText();
    }

    public static StyledText mockDetailsStyledText() {
        StyledText mock = mock(StyledText.class);
        when(mock.append(anyChar())).thenReturn(mock);
        when(mock.append(anyString())).thenReturn(mock);
        return mock;
    }

    public static StyledText stubDetailsStyledText() {
        return mockDetailsStyledText();
    }
}
