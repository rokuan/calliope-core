package com.rokuan.calliopecore.sentence;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Verb.Tense;

public class ActionObject {
	@Expose
	private Tense tense = Tense.PRESENT;
	
	@Expose
	private List<VerbConjugation> prefixVerbs = new ArrayList<VerbConjugation>();
	
	@Expose
	private VerbConjugation mainVerb;

	public ActionObject(Tense groupTense, VerbConjugation main, List<VerbConjugation> surroundingVerbs){
		this(groupTense, main);
		prefixVerbs.addAll(surroundingVerbs);
	}

	public ActionObject(Tense groupTense, VerbConjugation verb){
		tense = groupTense;
		mainVerb = verb;
	}

	public Tense getTense(){
		return tense;
	}

	public boolean prefixDoes(Action.ActionType action){
		for(VerbConjugation vi: prefixVerbs){
			if(vi.does(action)){
				return true;
			}
		}

		return false;
	}

	public boolean does(Action.ActionType action){
		return mainVerb != null && mainVerb.does(action);
	}
}
