package com.rokuan.calliopecore.sentence;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.sentence.IAction.Tense;

public class ActionObject {
	private final Tense tense;
	private final List<IAction> prefixVerbs = new ArrayList<IAction>();
	private final IAction mainVerb;

	public ActionObject(Tense groupTense, IAction verb){
		tense = groupTense;
		mainVerb = verb;
	}
	
	public ActionObject(Tense groupTense, IAction main, List<IAction> surroundingVerbs){
		this(groupTense, main);
		prefixVerbs.addAll(surroundingVerbs);
	}

	public Tense getTense(){
		return tense;
	}
	
	public IAction getMainAction(){
		return mainVerb;
	}
	
	public List<IAction> getPrefixActions(){
		return prefixVerbs;
	}
}
