package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import android.text.TextUtils;

import com.github.mobile.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestTextUtilsIsEmpty {

    @Test
    public void empty_string_is_empty() {
        assertEquals(true, TextUtils.isEmpty(""));
    }

    @Test
    public void string_a_is_not_empty() {
        assertEquals(false, TextUtils.isEmpty("a"));
    }
}
