package com.rokuan.calliopecore.sentence.structure.data.count;

import com.rokuan.calliopecore.sentence.IPronoun;

/**
 * Created by LEBEAU Christophe on 01/03/2015.
 */
public abstract class CountObject {
	public enum Range {
		FIRST,
		LAST
		//FIXED
	}

	public enum CountType {
		ALL,
		LIMIT,
		INTERVAL,
		MULTIPLE,
		FIXED,
		QUANTITY
	}

	public enum ArticleType {
		NONE,
		DEFINITE,
		INDEFINITE,
		DEMONSTRATIVE,
		POSSESSIVE
	}

	private CountType countType = CountType.ALL;
	public ArticleType definition = ArticleType.NONE;
	public IPronoun possessiveTarget = new IPronoun() {
		@Override
		public String getValue() {
			return "";
		}
		
		@Override
		public PronounSource getSource() {
			return PronounSource.UNDEFINED;
		}		
	};

	// TODO: ajouter les intervalles du type "du 5eme au dernier"
	protected CountObject(CountType ty){
		countType = ty;
	}

	public CountType getType(){
		return countType;
	}	
}
