package me.lkh.redisapitest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordInfo {
    WordType wordType;
    String word;
    int index;

    static WordInfo of(int wordIdx, WordType wordType, String word) {
        WordInfo ret = new WordInfo();
        ret.setIndex(wordIdx);
        ret.setWordType(wordType);
        ret.setWord(word);
        return ret;
    }
}
