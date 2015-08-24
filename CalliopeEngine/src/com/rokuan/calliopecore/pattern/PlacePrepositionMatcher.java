package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.PlacePreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class PlacePrepositionMatcher implements WordMatcher {
	private boolean matchContractedForm = false;
	private PlaceType[] possibleFollowers = null;

	public static class PlacePrepositionMatcherBuilder {
		private PlacePrepositionMatcher matcher;
		
		private PlacePrepositionMatcherBuilder(){
			matcher = new PlacePrepositionMatcher();
		}
		
		public PlacePrepositionMatcherBuilder setMatchContractedForm(boolean shouldMatch){
			matcher.matchContractedForm = shouldMatch;
			return this;
		}
		
		public PlacePrepositionMatcherBuilder setPossibleFollowers(PlaceType... types){
			matcher.possibleFollowers = types;
			return this;
		}
		
		public PlacePrepositionMatcher build(){
			return matcher;
		}
	}
	
	public PlacePrepositionMatcher() {

	}
	
	public static PlacePrepositionMatcherBuilder builder(){
		return new PlacePrepositionMatcherBuilder();
	}
	
	@Override
	public boolean matches(Word word) {
		if(matchContractedForm && !word.isOfType(WordType.CONTRACTED)){
			return false;
		}
		
		if(possibleFollowers != null){
			PlacePreposition prep = word.getPlacePreposition();
			
			for(PlaceType ty: possibleFollowers){
				if(!prep.canBeFollowedBy(ty)){
					return false;
				}
			}
		}
		
		return true;
	}
}
