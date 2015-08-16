package com.rokuan.calliopecore.parser;

import java.util.List;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;

/**
 * Created by LEBEAU Christophe on 01/03/2015.
 */
public class WordBuffer extends TokenBuffer<Word> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -403337949871603827L;

	public WordBuffer(){
		super();
	}
	
	public WordBuffer(List<Word> ws){
		super(ws);
	}
	
	public WordBuffer(WordBuffer wb){
		super(wb);
	}
	
	public WordBuffer cut(){
		return new WordBuffer(this.subList(getCurrentIndex(), this.size()));
	}
	
    public boolean matchesSyntax(Word.WordType... types){
        if(types.length + this.getCurrentIndex() != this.size()){
            return false;
        }
        
        return matchesSyntax(types.length, types);
    }

    private boolean matchesSyntax(int length, Word.WordType... types){
        for(int i=0; i<length; i++){
            if(!this.get(i + this.getCurrentIndex()).isOfType(types[i])){
                return false;
            }
        }

        return true;
    }

    public boolean syntaxStartsWith(Word.WordType... types){
        if(types.length + this.getCurrentIndex() > this.size()){
            return false;
        }

        return matchesSyntax(types.length, types);
    }
    
    public boolean syntaxStartsWith(WordPattern... patterns){
    	return WordPattern.syntaxStartsWith(this, patterns);
    }
}
