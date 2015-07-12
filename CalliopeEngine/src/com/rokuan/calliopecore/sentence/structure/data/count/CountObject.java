package com.rokuan.calliopecore.sentence.structure.data.count;

import com.rokuan.calliopecore.sentence.Type.Pronoun;

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
        FIXED
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
    public Pronoun possessiveTarget = Pronoun.UNDEFINED;
    
    // TODO: ajouter les intervalles du type "du 5eme au dernier"
    protected CountObject(CountType ty){
    	countType = ty;
    }
    
    public CountType getType(){
    	return countType;
    }
}
