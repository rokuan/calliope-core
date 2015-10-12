package com.rokuan.calliopecore.sentence;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.IVerbConjugation.Tense;

public class ActionObject {
	@Expose
	private Tense tense = Tense.PRESENT;
	
	@Expose
	private List<IVerbConjugation> prefixVerbs = new ArrayList<IVerbConjugation>();
	
	@Expose
	private IVerbConjugation mainVerb;

	public ActionObject(Tense groupTense, IVerbConjugation main, List<IVerbConjugation> surroundingVerbs){
		this(groupTense, main);
		prefixVerbs.addAll(surroundingVerbs);
	}

	public ActionObject(Tense groupTense, IVerbConjugation verb){
		tense = groupTense;
		mainVerb = verb;
	}

	public Tense getTense(){
		return tense;
	}

	public boolean surroundingDo(Action.ActionType action){
		for(IVerbConjugation vi: prefixVerbs){
			if(vi.does(action)){
				return true;
			}
		}

		return false;
	}

	public boolean does(Action.ActionType action){
		return mainVerb != null && mainVerb.does(action);
	}
	
	public boolean isAFieldAction(){
		return mainVerb.getVerb().isAFieldAction();
	}
	
	public String getBoundField(){
		return mainVerb.getVerb().getBoundField();
	}
}
