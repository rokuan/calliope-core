package com.rokuan.calliopecore.parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by LEBEAU Christophe on 24/02/2015.
 */
public class TokenBuffer<T> extends ArrayList<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7427977638775743861L;
	
	private int currentIndex = 0;
    private Stack<Integer> tmpIndexes = new Stack<Integer>();
    
    public TokenBuffer(){
    	super();
    }
    
    public TokenBuffer(TokenBuffer<T> other){
    	super(other);
    	this.currentIndex = other.currentIndex;
    }
    
    public boolean isIntoBounds(){
    	return (currentIndex >= 0 && currentIndex < this.size());
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
    	if(currentIndex <= 0){
    		throw new IndexOutOfBoundsException();
    	}

        currentIndex--;
    }

    public void next(){
        /*if(!hasNext()){
            throw new NoSuchElementException();
        }*/
    	if(currentIndex >= this.size()){
    		throw new IndexOutOfBoundsException();
    	}
    	
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
    
    public void truncate(){
    	this.removeRange(0, currentIndex);
    }
    
    public void consume(){
    	this.remove(currentIndex);
    }
}
