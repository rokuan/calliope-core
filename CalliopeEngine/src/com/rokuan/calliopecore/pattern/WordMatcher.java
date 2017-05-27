package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.IWord;

public interface WordMatcher<T extends IWord> {
    boolean matches(T word);
}
