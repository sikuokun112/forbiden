package me.lkh.redisapitest.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordSplit {
    int index;
    List<WordInfo> wordList;
    List<List<WordInfo>> lines;
    String[] words;

    public String toWordString() {
        StringBuilder sb = new StringBuilder();
        for (WordInfo word : wordList) {
            sb.append(word.getWord());
        }

        return sb.toString();
    }

    public List<WordInfo> findMatchedWords(List<String> banword) {

        List<WordInfo> wordInfoList = new ArrayList<>();

        for (List<WordInfo> line : lines) {

            wordInfoList.addAll(findMatchedWords(line, banword));

        }

        return wordInfoList;

    }

    private List<WordInfo> findMatchedWords(List<WordInfo> onlyWordsInLine, List<String> banword) {

        List<WordInfo> matchedWords = new ArrayList<>();

        for (int i = 0; ( i + banword.size() ) <= onlyWordsInLine.size(); i++) {

            List<WordInfo> currentWords = onlyWordsInLine.subList(i, i + banword.size());

//            boolean matched = StreamUtils
//                    .zipWithIndex(
//                            currentWords.stream()
//                    )
//                    .allMatch(
//                            indexedWordInfo -> indexedWordInfo.getValue().getWord().equalsIgnoreCase(banword.get((int) indexedWordInfo.getIndex()))
//                    );

            if (matched)
                matchedWords.addAll(currentWords);

        }

        return matchedWords;

    }

    public static WordSplit splitOf(int idx, String sentence) {
        if (Objects.isNull(sentence)) throw new NullPointerException();
        WordSplit ret = new WordSplit();
        ret.setIndex(idx);

        ret.setWords(sentence.split(WORDS_SPLIT_REGEXP));
        ret.setWordList(new LinkedList<>());

        for (int i = 0; i < ret.getWords().length; i++) {
            ret.getWordList().add(WordInfo.of(
                    i
                    , WordType.getType(ret.getWords()[i])
                    , ret.getWords()[i]
            ));
        }

        ret.setLines(new LinkedList());
        List line = new LinkedList();

        for (int i = 0; i < ret.getWordList().size(); i++) {
            if (ret.getWordList().get(i).getWord().equals("\r") || ret.getWordList().get(i).getWord().equals("\n")) {
                // \r\n 일 경우 한줄로 처리함.
                if (((i + 1) < ret.getWordList().size()) &&
                        ((ret.getWordList().get(i).getWord().equals("\r")) && (ret.getWordList().get(i + 1).getWord().equals("\n")))) {
                    i++;
                }

                ret.getLines().add(line);
                line = new LinkedList();
                continue;
            }

            if (ret.getWordList().get(i).getWordType().equals(WordType.WORD)) {
                line.add(ret.getWordList().get(i));
            }
        }

        if (!line.isEmpty()) ret.getLines().add(line);
        return ret;
    }


}
