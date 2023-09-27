package me.lkh.redisapitest.model;

public enum WordType {
    SPACE, CRLF, WORD;

    public WordType getWordType(String word) {
        if (word.equals(" ") || word.equals("&nbsp;") || word.equals("\u00A0")) {
            return SPACE;
        }
        if (word.equals("\r") || word.equals("\n")) {
            return CRLF;
        }
        return WORD;
    }
}
