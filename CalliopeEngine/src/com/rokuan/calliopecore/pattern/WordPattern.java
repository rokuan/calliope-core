package com.rokuan.calliopecore.pattern;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

/**
 * Created by LEBEAU Christophe on 02/03/2015.
 */
public abstract class WordPattern {
	protected abstract int getLength();
	protected abstract boolean mayBeOptional();

	static class WordOrPattern extends WordPattern {
		private List<WordPattern> choices;

		public WordOrPattern(WordPattern... ps){
			choices = new ArrayList<WordPattern>(ps.length);

			for(WordPattern pattern: ps){
				choices.add(pattern);
			}
		}

		@Override
		protected int getLength() {
			return 1;
		}

		@Override
		protected boolean mayBeOptional() {
			for(WordPattern pat: choices){
				if(pat.mayBeOptional()){
					return true;
				}
			}

			return false;
		}
	}

	static class WordSequencePattern extends WordPattern {
		private List<WordPattern> parts;

		public WordSequencePattern(WordPattern... ps){
			parts = new ArrayList<WordPattern>(ps.length);

			for(WordPattern p: ps){
				parts.add(p);
			}
		}
		
		public WordSequencePattern(List<WordPattern> ps) {
			parts = new ArrayList<WordPattern>(ps);
		}

		@Override
		protected int getLength() {
			int sum = 0;

			for(WordPattern pat: parts){
				sum += pat.getLength();
			}

			return sum;
		}

		@Override
		protected boolean mayBeOptional() {
			for(WordPattern pat: parts){
				if(!pat.mayBeOptional()){
					return false;
				}
			}

			return true;
		}
	}

	static class WordOptionalPattern extends WordPattern {
		private WordPattern pattern;

		public WordOptionalPattern(WordPattern pat){
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

	static class WordSimplePattern extends WordPattern {
		private Word.WordType type;
		private String valuePattern = null;
		private String verbPattern = null;

		public WordSimplePattern(Word.WordType ty){
			type = ty;
		}

		public WordSimplePattern(WordType ty, String valPattern) {
			this(ty);
			valuePattern = valPattern;
		}

		public WordSimplePattern(WordType ty, String valPattern, String infinitivePattern) {
			this(ty, valPattern);
			verbPattern = infinitivePattern;
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

		public WordSeparatedListPattern(WordPattern elem, WordPattern sep){
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

		public WordListPattern(WordPattern elem){
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

	public static WordPattern or(WordPattern... patterns){
		return new WordOrPattern(patterns);
	}

	public static WordPattern optional(WordPattern pattern){
		return new WordOptionalPattern(pattern);
	}

	public static WordPattern simple(Word.WordType ty){
		return new WordSimplePattern(ty);
	}

	public static WordPattern simple(Word.WordType ty, String regex){
		return new WordSimplePattern(ty, regex);
	}
	
	public static WordPattern simple(Word.WordType ty, String valueRegex, String verbRegex){
		return new WordSimplePattern(ty, valueRegex, verbRegex);
	}

	public static WordPattern sequence(WordPattern... patterns){
		return new WordSequencePattern(patterns);
	}

	public static WordPattern nonEmptyList(WordPattern part){
		return new WordListPattern(part);
	}

	public static WordPattern separatedNonEmptyList(WordPattern part, WordPattern separator){
		return new WordSeparatedListPattern(part, separator);
	}

	public static boolean syntaxStartsWith(WordBuffer words, WordPattern... patterns){
		if(!words.isIntoBounds()){
			return false;
		}
		
		WordBuffer copy = new WordBuffer(words);
		return realSyntaxStartsWith(copy, patterns);
	}

	private static boolean realSyntaxStartsWith(WordBuffer words, WordPattern... patterns){
		int patternsIndex = 0;

		while(true){
			if(patternsIndex >= patterns.length){
				return true;
			}

			//if(words.getCurrentIndex() >= words.size()){
			if(!words.isIntoBounds()){
				for(int pi=patternsIndex; pi<patterns.length; pi++){
					if(!patterns[pi].mayBeOptional()){
						return false;
					}
				}

				return true;
			}

			if(patterns[patternsIndex] instanceof WordOrPattern){
				WordOrPattern or = (WordOrPattern)patterns[patternsIndex];
				boolean patternMatch = false;

				for(WordPattern pat: or.choices){
					words.start();

					if(realSyntaxStartsWith(words, pat)){
						patternMatch = true;
						break;
					} 

					words.cancel();
				}

				if(!patternMatch){
					return false;
				}                
			} else if (patterns[patternsIndex] instanceof WordSimplePattern){
				WordSimplePattern simple = (WordSimplePattern)patterns[patternsIndex];

				if(!words.getCurrentElement().isOfType(simple.type)){
					return false;
				}

				try{
					if(simple.valuePattern != null && !words.getCurrentElement().getValue().matches(simple.valuePattern)){
						return false;
					}

					if(simple.verbPattern != null && !words.getCurrentElement().getVerbInfo().getVerb().getVerb().matches(simple.verbPattern)){
						return false;
					}
				}catch(Exception e){
					// TODO: lancer l'exception quoi qu'il arrive ?
					//System.out.println(e);
					e.printStackTrace();
					return false;
				}

				words.next();
			} else if(patterns[patternsIndex] instanceof WordOptionalPattern){
				WordOptionalPattern optional = (WordOptionalPattern)patterns[patternsIndex];

				words.start();

				if(!realSyntaxStartsWith(words, optional.pattern)){
					words.cancel();
				}
			} else if(patterns[patternsIndex] instanceof WordSequencePattern){
				WordSequencePattern sequence = (WordSequencePattern)patterns[patternsIndex];
				
				// TODO: pouvoir gerer les cas ou certains elements optionnels empietent sur le prochain pattern
				// Exemple: est ~ [ OPT(AUXILIARY), VERB ] 
				for(WordPattern pat: sequence.parts){
					if(!realSyntaxStartsWith(words, pat)){
						return false;
					}
				}
			} else if(patterns[patternsIndex] instanceof WordListPattern){
				WordListPattern list = (WordListPattern)patterns[patternsIndex];

				if(!realSyntaxStartsWith(words, list.element)){
					return false;
				}

				while(words.hasNext()){
					if(!realSyntaxStartsWith(words, list.element)){
						break;
					}
				}
			} else if(patterns[patternsIndex] instanceof WordSeparatedListPattern){
				WordSeparatedListPattern separatedList = (WordSeparatedListPattern)patterns[patternsIndex];

				if(!realSyntaxStartsWith(words, separatedList.element)){
					return false;
				}

				WordPattern elementSequence = WordPattern.sequence(separatedList.separator, separatedList.element);

				while(words.hasNext()){
					if(!realSyntaxStartsWith(words, elementSequence)){
						break;
					}
				}
			} else {
				// TODO: should not happen
				return false;
			}

			patternsIndex++;
		}
	}
}
