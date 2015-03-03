package com.rokuan.calliopecore.parser;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by LEBEAU Christophe on 24/02/2015.
 */
public class TokenBuffer<T> extends ArrayList<T> {
    private int currentIndex = 0;
    private Stack<Integer> tmpIndexes = new Stack<Integer>();
    
    public TokenBuffer(){
    	super();
    }
    
    public TokenBuffer(TokenBuffer<T> other){
    	super(other);
    	this.currentIndex = other.currentIndex;
    }

    public boolean hasPrevious(){
    	return currentIndex > 0;
    }
    
    public boolean hasNext(){
    	return currentIndex < this.size() - 1;
    }
    
    public void previous(){
        /*if(!hasPrevious()){
            throw new NoSuchElementException();
        }*/

        currentIndex--;
    }

    public void next(){
        /*if(!hasNext()){
            throw new NoSuchElementException();
        }*/
        currentIndex++;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public void moveTo(int index){
        currentIndex = Math.max(0, index);
    }

    public T getCurrentElement(){
        return this.get(currentIndex);
    }
    
    public void start(){
    	tmpIndexes.push(currentIndex);
    }
    
    public void cancel(){
    	if(!tmpIndexes.empty()){
    		currentIndex = tmpIndexes.pop();
    	}
    }
}
