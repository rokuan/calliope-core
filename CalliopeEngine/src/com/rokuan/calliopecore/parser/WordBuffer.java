package com.rokuan.calliopecore.parser;

import java.util.List;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.IWord;

/**
 * Created by LEBEAU Christophe on 01/03/2015.
 */
public class WordBuffer<T extends IWord> extends TokenBuffer<T> {
    /**
     *
     */
    private static final long serialVersionUID = -403337949871603827L;

    public WordBuffer() {
        super();
    }

    public WordBuffer(List<T> ws) {
        super(ws);
    }

    public WordBuffer(WordBuffer<T> wb) {
        super(wb);
    }

    public WordBuffer<T> cut() {
        return new WordBuffer<T>(this.subList(getCurrentIndex(), this.size()));
    }

    public boolean syntaxStartsWith(WordPattern... patterns) {
        return WordPattern.syntaxStartsWith(this, patterns);
    }
}
