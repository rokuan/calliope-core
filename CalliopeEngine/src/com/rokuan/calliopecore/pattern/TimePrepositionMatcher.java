package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.TimePreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;

public class TimePrepositionMatcher extends PrepositionMatcher<TimeType> {
	public static class TimePrepositionMatcherBuilder extends PrepositionMatcherBuilder<TimeType> {
		protected TimePrepositionMatcherBuilder(PrepositionMatcher<TimeType> m) {
			super(m);
		}
	}

	public TimePrepositionMatcherBuilder getBuilder(){
		return new TimePrepositionMatcherBuilder(this);
	}	
	
	@Override
	public boolean matches(Word word) {
		if(matchContractedForm && !word.isOfType(WordType.CONTRACTED)){
			return false;
		}
		
		if(possibleFollowers != null){
			TimePreposition prep = word.getTimePreposition();

			if(prep == null){
				return false;
			}
			
			for(TimeType ty: possibleFollowers){
				if(!prep.canBeFollowedBy(ty)){
					return false;
				}
			}
		}
		
		return true;
	}
}
