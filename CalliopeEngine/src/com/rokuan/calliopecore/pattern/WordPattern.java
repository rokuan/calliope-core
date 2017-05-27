package com.rokuan.calliopecore.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;

/**
 * Created by LEBEAU Christophe on 02/03/2015.
 */
public abstract class WordPattern {
    protected abstract int getLength();

    protected abstract boolean mayBeOptional();

    static class WordOrPattern extends WordPattern {
        private final List<WordPattern> choices;

        public WordOrPattern(WordPattern... ps) {
            choices = Arrays.asList(ps);
        }

        @Override
        protected int getLength() {
            return 1;
        }

        @Override
        protected boolean mayBeOptional() {
            for (WordPattern pat : choices) {
                if (pat.mayBeOptional()) {
                    return true;
                }
            }
            return false;
        }
    }

    static class WordSequencePattern extends WordPattern {
        private final List<WordPattern> parts;

        public WordSequencePattern(WordPattern... ps) {
            parts = Arrays.asList(ps);
        }

        public WordSequencePattern(List<WordPattern> ps) {
            parts = new ArrayList<WordPattern>(ps);
        }

        @Override
        protected int getLength() {
            int sum = 0;

            for (WordPattern pat : parts) {
                sum += pat.getLength();
            }

            return sum;
        }

        @Override
        protected boolean mayBeOptional() {
            for (WordPattern pat : parts) {
                if (!pat.mayBeOptional()) {
                    return false;
                }
            }
            return true;
        }
    }

    static class WordOptionalPattern extends WordPattern {
        private WordPattern pattern;

        public WordOptionalPattern(WordPattern pat) {
            pattern = pat;
        }

        @Override
        protected int getLength() {
            return pattern.getLength();
        }

        @Override
        protected boolean mayBeOptional() {
            return true;
        }
    }

    static class WordSimplePattern<T extends IWord> extends WordPattern {
        private WordMatcher<T> matcher;

        public WordSimplePattern(WordMatcher<T> m) {
            matcher = m;
        }

        @Override
        protected int getLength() {
            return 1;
        }

        @Override
        protected boolean mayBeOptional() {
            return false;
        }
    }

    static class WordSeparatedListPattern extends WordPattern {
        private WordPattern element;
        private WordPattern separator;

        public WordSeparatedListPattern(WordPattern elem, WordPattern sep) {
            element = elem;
            separator = sep;
        }

        @Override
        protected int getLength() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        protected boolean mayBeOptional() {
            return false;
        }
    }

    static class WordListPattern extends WordPattern {
        private WordPattern element;

        public WordListPattern(WordPattern elem) {
            element = elem;
        }

        @Override
        protected int getLength() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        protected boolean mayBeOptional() {
            return false;
        }
    }

    public static WordPattern or(WordPattern... patterns) {
        return new WordOrPattern(patterns);
    }

    public static WordPattern optional(WordPattern pattern) {
        return new WordOptionalPattern(pattern);
    }

    public static <T extends IWord> WordPattern simple(WordMatcher<T> matcher) {
        return new WordSimplePattern<T>(matcher);
    }

    public static WordPattern sequence(WordPattern... patterns) {
        return new WordSequencePattern(patterns);
    }

    public static WordPattern nonEmptyList(WordPattern part) {
        return new WordListPattern(part);
    }

    public static WordPattern separatedNonEmptyList(WordPattern part, WordPattern separator) {
        return new WordSeparatedListPattern(part, separator);
    }

    public static <T extends IWord> boolean syntaxStartsWith(WordBuffer<T> words, WordPattern... patterns) {
        return words.isIntoBounds() && realSyntaxStartsWith(new WordBuffer<T>(words), new WordSequencePattern(patterns));
    }

    private static <T extends IWord> boolean realSyntaxStartsWith(WordBuffer<T> words, WordPattern pattern) {
        if (pattern instanceof WordOrPattern) {
            WordOrPattern or = (WordOrPattern) pattern;
            boolean patternMatch = false;

            for (WordPattern pat : or.choices) {
                words.start();

                if (realSyntaxStartsWith(words, pat)) {
                    patternMatch = true;
                    words.end();
                    break;
                }

                words.cancel();
            }

            if (!patternMatch) {
                return false;
            }
        } else if (pattern instanceof WordSimplePattern) {
            @SuppressWarnings("unchecked")
            WordSimplePattern<T> simple = (WordSimplePattern<T>) pattern;

            if (words.getCurrentIndex() >= words.size()) {
                return false;
            }

            if (!simple.matcher.matches(words.getCurrentElement())) {
                return false;
            }

            words.next();
        } else if (pattern instanceof WordOptionalPattern) {
            WordOptionalPattern optional = (WordOptionalPattern) pattern;

            words.start();

            if (!realSyntaxStartsWith(words, optional.pattern)) {
                words.cancel();
                return true;
            }

            words.end();
        } else if (pattern instanceof WordSequencePattern) {
            WordSequencePattern sequence = (WordSequencePattern) pattern;

            words.start();

            for (WordPattern pat : sequence.parts) {
                if (!realSyntaxStartsWith(words, pat)) {
                    words.cancel();
                    return false;
                }
            }

            words.end();
        } else if (pattern instanceof WordListPattern) {
            WordListPattern list = (WordListPattern) pattern;

            words.start();

            if (!realSyntaxStartsWith(words, list.element)) {
                words.cancel();
                return false;
            }

            words.end();

            while (true) {
                if (words.getCurrentIndex() >= words.size()) {
                    break;
                }

                words.start();

                if (!realSyntaxStartsWith(words, list.element)) {
                    words.cancel();
                    break;
                }

                words.end();
            }
        } else if (pattern instanceof WordSeparatedListPattern) {
            WordSeparatedListPattern list = (WordSeparatedListPattern) pattern;
            WordPattern separatorSequence = WordPattern.sequence(list.separator, list.element);

            words.start();

            if (!realSyntaxStartsWith(words, list.element)) {
                words.cancel();
                return false;
            }

            words.end();

            while (true) {
                if (words.getCurrentIndex() >= words.size()) {
                    break;
                }

                words.start();

                if (!realSyntaxStartsWith(words, separatorSequence)) {
                    words.cancel();
                    break;
                }

                words.end();
            }
        } else {
            // TODO: should not happen
            return false;
        }

        return true;
    }
}
