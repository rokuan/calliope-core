package com.rokuan.calliopecore.sentence.structure.data.count;

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
    
    private CountType countType = CountType.ALL;
    
    // TODO: ajouter les intervalles du type "du 5eme au dernier"
    protected CountObject(CountType ty){
    	countType = ty;
    }
    
    public CountType getType(){
    	return countType;
    }
}
