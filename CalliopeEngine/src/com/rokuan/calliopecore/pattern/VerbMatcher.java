package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.Verb.Form;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class VerbMatcher implements WordMatcher {
	private boolean auxiliary = false;
	private String infiniteVerb = null;
	private String conjugatedVerb = null;
	private Form form = null;
	
	public static class VerbMatcherBuilder {
		private VerbMatcher matcher;
		
		private VerbMatcherBuilder(){
			matcher = new VerbMatcher();
		}
		
		public VerbMatcherBuilder setVerbRegex(String verb){
			matcher.infiniteVerb = verb;
			return this;
		}
		
		public VerbMatcherBuilder setAuxiliary(boolean aux){
			matcher.auxiliary = aux;
			return this;
		}
		
		public VerbMatcherBuilder setConjugatedVerbRegex(String conjugatedForm){
			matcher.conjugatedVerb = conjugatedForm;
			return this;
		}
		
		public VerbMatcherBuilder setForm(Form form){
			matcher.form = form;
			return this;
		}
		
		public VerbMatcher build(){
			return matcher;
		}
	}
	
	public VerbMatcher(){
		
	}
	
	public static VerbMatcherBuilder builder(){
		return new VerbMatcherBuilder();
	}

	@Override
	public boolean matches(Word word) {
		if(!word.isOfType(WordType.VERB)){
			return false;
		}
		
		if(auxiliary && !word.isOfType(WordType.AUXILIARY)){
			return false;
		}

		if(conjugatedVerb != null && !word.getValue().matches(conjugatedVerb)){
			return false;
		}
			
		if(infiniteVerb != null && !word.getVerbInfo().getVerb().getVerb().matches(infiniteVerb)){
			return false;
		}
		
		if(form != null && word.getVerbInfo().getForm() != form){
			return false;
		}
		
		return true;
	}
}
