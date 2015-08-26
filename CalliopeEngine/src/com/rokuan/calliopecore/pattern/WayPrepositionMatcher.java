package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.WayPreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class WayPrepositionMatcher extends PrepositionMatcher<WayType> {
	public static class WayPrepositionMatcherBuilder extends PrepositionMatcherBuilder<WayType> {
		protected WayPrepositionMatcherBuilder(PrepositionMatcher<WayType> m) {
			super(m);
		}
	}

	public WayPrepositionMatcherBuilder getBuilder(){
		return new WayPrepositionMatcherBuilder(this);
	}	

	@Override
	public boolean matches(Word word) {
		if(matchContractedForm && !word.isOfType(WordType.CONTRACTED)){
			return false;
		}

		if(possibleFollowers != null){
			WayPreposition prep = word.getWayPreposition();

			if(prep == null){
				return false;
			}
			
			for(WayType ty: possibleFollowers){
				if(!prep.canBeFollowedBy(ty)){
					return false;
				}
			}
		}

		return true;
	}
}
