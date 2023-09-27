package me.lkh.redisapitest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BanWord implements Serializable {
    private String apiVersion;
    private String category;
    private String service;
    private String banType;
    private String text;
    @Transient
    private String originText;
    private List<String> matchWords;
    private List<String> keys;
    private List<String> checkKeys;

    @Transient
    private transient Map<Integer, Object> nodeIndex;

    @Getter
    @Setter
    @Transient
    private transient Map<Integer, WordSplit> wordSplitMap;

    @Getter
    @Setter
    @Transient
    private transient Integer wordCount;

    @Getter
    @Setter
    @Transient
    private
}
