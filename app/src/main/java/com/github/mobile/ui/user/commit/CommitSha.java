package com.github.mobile.ui.user.commit;

import com.github.mobile.ui.StyledText;

public class CommitSha {
    private static final int TRUNCATED_POS = 7;
    private final String sha;

    public CommitSha(String sha) {
        this.sha = sha;
    }

    public void render(StyledText text) {
        text.monospace(truncatedSha());
    }

    private String truncatedSha() {
        return sha.substring(0, Math.min(TRUNCATED_POS, sha.length()));
    }
}