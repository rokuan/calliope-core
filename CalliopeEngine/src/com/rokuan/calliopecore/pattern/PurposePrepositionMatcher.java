package com.rokuan.calliopecore.pattern;

import com.rokuan.calliopecore.sentence.PurposePreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;

public class PurposePrepositionMatcher extends PrepositionMatcher<PurposeType> {
	public static class PurposePrepositionMatcherBuilder extends PrepositionMatcherBuilder<PurposeType> {
		
	}	

	@Override
	public boolean matches(Word word) {
		if(matchContractedForm && !word.isOfType(WordType.CONTRACTED)){
			return false;
		}

		if(possibleFollowers != null){
			PurposePreposition prep = word.getPurposePreposition();

			for(PurposeType ty: possibleFollowers){
				if(!prep.canBeFollowedBy(ty)){
					return false;
				}
			}
		}

		return true;
	}
}
