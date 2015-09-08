package com.rokuan.calliopecore.pattern;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

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
		/*private Word.WordType[] types;
		private String valuePattern = null;
		private String verbPattern = null;

		public WordSimplePattern(Word.WordType[] ty){
			types = ty ;
		}

		public WordSimplePattern(WordType[] ty, String valPattern) {
			this(ty);
			valuePattern = valPattern;
		}

		public WordSimplePattern(WordType[] ty, String valPattern, String infinitivePattern) {
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
		}*/
		private WordMatcher matcher;

		public WordSimplePattern(WordMatcher m){
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

	public static WordPattern simpleWord(Word.WordType ty){
		return new WordSimplePattern(SimpleWordMatcher.builder().setTypes(ty).build());
	}

	public static WordPattern simpleWord(Word.WordType[] ty){
		return new WordSimplePattern(SimpleWordMatcher.builder().setTypes(ty).build());
	}

	public static WordPattern simpleWord(String regex){
		return new WordSimplePattern(SimpleWordMatcher.builder().setWordRegex(regex).build());
	}

	public static WordPattern simpleWord(Word.WordType ty, String regex){
		return new WordSimplePattern(SimpleWordMatcher.builder().setTypes(ty).setWordRegex(regex).build());
	}

	public static WordPattern simpleWord(Word.WordType[] ty, String regex){
		return new WordSimplePattern(SimpleWordMatcher.builder().setTypes(ty).setWordRegex(regex).build());
	}

	/*public static WordPattern simple(Word.WordType ty, String valueRegex, String verbRegex){
		return new WordSimplePattern(null);
	}*/

	/*public static WordPattern simpleVerb(String verbRegex){
		return new WordSimplePattern(new VerbMatcher().getBuilder().setVerbRegex(verbRegex).build());
	}

	public static WordPattern simpleVerb(String verbRegex, String conjugationRegex){
		return new WordSimplePattern(new VerbMatcher().getBuilder()
				.setVerbRegex(verbRegex)
				.setConjugatedVerbRegex(conjugationRegex)
				.build());
	}

	public static WordPattern simpleVerb(boolean auxiliary, String verbRegex, String conjugationRegex){
		return new WordSimplePattern(new VerbMatcher().getBuilder()
				.setAuxiliary(auxiliary)
				.setVerbRegex(verbRegex)
				.setConjugatedVerbRegex(conjugationRegex)
				.build());
	}*/

	public static WordPattern simplePlacePrep(PlaceType next){
		return simplePlacePrep(next, false);
	}

	public static WordPattern simplePlacePrep(PlaceType follower, boolean contracted){
		return new WordSimplePattern(new PlacePrepositionMatcher().getBuilder()
		.setMatchContractedForm(contracted)
		.setPossibleFollowers(follower)
		.build()); 
	}

	public static WordPattern simpleTimePrep(TimeType next){
		return simpleTimePrep(next, false);
	}

	public static WordPattern simpleTimePrep(TimeType next, boolean contracted){
		return new WordSimplePattern(new TimePrepositionMatcher().getBuilder()
		.setMatchContractedForm(contracted)
		.setPossibleFollowers(next)
		.build());
	}
	
	public static WordPattern simpleWayPrep(WayType next){
		return simpleWayPrep(next, false);
	}

	public static WordPattern simpleWayPrep(WayType next, boolean contracted){
		return new WordSimplePattern(new WayPrepositionMatcher().getBuilder()
		.setMatchContractedForm(contracted)
		.setPossibleFollowers(next)
		.build());
	}
	
	public static WordPattern simplePurposePrep(PurposeType next){
		return simplePurposePrep(next, false);
	}

	public static WordPattern simplePurposePrep(PurposeType next, boolean contracted){
		return new WordSimplePattern(new PurposePrepositionMatcher().getBuilder()
		.setMatchContractedForm(contracted)
		.setPossibleFollowers(next)
		.build());
	}

	public static WordPattern simple(WordMatcher matcher){
		return new WordSimplePattern(matcher);
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

		return realSyntaxStartsWith(new WordBuffer(words), new WordSequencePattern(patterns));
	}

	private static boolean realSyntaxStartsWith(WordBuffer words, WordPattern pattern){		
		if(pattern instanceof WordOrPattern){
			WordOrPattern or = (WordOrPattern)pattern;
			boolean patternMatch = false;

			for(WordPattern pat: or.choices){
				words.start();

				if(realSyntaxStartsWith(words, pat)){
					patternMatch = true;
					words.end();
					break;
				}

				words.cancel();
			}

			if(!patternMatch){
				return false;
			}                
		} else if (pattern instanceof WordSimplePattern){
			WordSimplePattern simple = (WordSimplePattern)pattern;

			if(words.getCurrentIndex() >= words.size()){
				return false;
			}

			/*for(int i=0; i<simple.types.length; i++){
				if(!words.getCurrentElement().isOfType(simple.types[i])){
					return false;
				}
			}

			try{
				/*if(simple.valuePattern != null && !words.getCurrentElement().getValue().matches(simple.valuePattern)){
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
			}*/

			if(!simple.matcher.matches(words.getCurrentElement())){
				return false;
			}

			words.next();
		} else if(pattern instanceof WordOptionalPattern){
			WordOptionalPattern optional = (WordOptionalPattern)pattern;

			words.start();

			if(!realSyntaxStartsWith(words, optional.pattern)){
				words.cancel();
				return true;
			}

			words.end();
		} else if(pattern instanceof WordSequencePattern){
			WordSequencePattern sequence = (WordSequencePattern)pattern;

			words.start();

			for(WordPattern pat: sequence.parts){
				if(!realSyntaxStartsWith(words, pat)){
					words.cancel();
					return false;
				}
			}

			words.end();
		} else if(pattern instanceof WordListPattern){
			WordListPattern list = (WordListPattern)pattern;

			words.start();

			if(!realSyntaxStartsWith(words, list.element)){
				words.cancel();
				return false;
			}

			words.end();

			while(true){
				if(words.getCurrentIndex() >= words.size()){
					break;
				}

				words.start();

				if(!realSyntaxStartsWith(words, list.element)){
					words.cancel();
					break;
				}

				words.end();
			}
		} else if(pattern instanceof WordSeparatedListPattern){
			WordSeparatedListPattern list = (WordSeparatedListPattern)pattern;
			WordPattern separatorSequence = WordPattern.sequence(list.separator, list.element);

			words.start();

			if(!realSyntaxStartsWith(words, list.element)){
				words.cancel();
				return false;
			}			

			words.end();

			while(true){
				if(words.getCurrentIndex() >= words.size()){
					break;
				}

				words.start();

				if(!realSyntaxStartsWith(words, separatorSequence)){
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
