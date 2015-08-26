package com.rokuan.calliopecore.pattern;


import com.rokuan.calliopecore.sentence.PlacePreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class PlacePrepositionMatcher extends PrepositionMatcher<PlaceType> {
	public static class PlacePrepositionMatcherBuilder extends PrepositionMatcherBuilder<PlaceType> {
		protected PlacePrepositionMatcherBuilder(PrepositionMatcher<PlaceType> m) {
			super(m);
		}
	}
	
	public PlacePrepositionMatcherBuilder getBuilder(){
		return new PlacePrepositionMatcherBuilder(this);
	}

	@Override
	public boolean matches(Word word) {
		if(matchContractedForm && !word.isOfType(WordType.CONTRACTED)){
			return false;
		}

		if(possibleFollowers != null){
			PlacePreposition prep = word.getPlacePreposition();

			if(prep == null){
				return false;
			}
			
			for(PlaceType ty: possibleFollowers){
				if(!prep.canBeFollowedBy(ty)){
					return false;
				}
			}
		}

		return true;
	}
}
