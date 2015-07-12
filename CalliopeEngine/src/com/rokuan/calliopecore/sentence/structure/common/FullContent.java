package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public abstract class FullContent implements ISubjectContent, IVerbalContent, IComplementContent {
	public Action.VerbAction action;
	public NominalGroup subject;
	public NominalGroup what;
	public NominalGroup target;
	public NominalGroup where;
	public NominalGroup when;
	public NominalGroup how;
	public NominalGroup why;

	@Override
	public void setSubject(NominalGroup source){
		subject = source;
	}
	
	@Override
	public void setAction(Action.VerbAction verb){
		action = verb;
	}
	
	@Override
	public void setDirectObject(NominalGroup direct){
		what = direct;
	}

	@Override
	public void setIndirectObject(NominalGroup indirect){
		target = indirect;
	}

	@Override
	public void setPlaceAdverbial(NominalGroup place){
		where = place;
	}

	@Override
	public void setTimeAdverbial(NominalGroup time){
		when = time;
	}

	@Override
	public void setWayAdverbial(NominalGroup way){
		how = way;
	}

	@Override
	public void setPurposeAdverbial(NominalGroup purpose){
		why = purpose;
	}

	@Override
	public NominalGroup getSubject(){
		return subject;
	}
	
	@Override
	//public Enum<?> getAction(){
	public Action.VerbAction getAction(){
		return action;
	}
	
	@Override
	public NominalGroup getDirectObject(){
		return what;
	}

	@Override
	public NominalGroup getIndirectObject(){
		return target;
	}

	@Override
	public NominalGroup getPlaceAdverbial(){
		return where;
	}

	@Override
	public 	NominalGroup getTimeAdverbial(){
		return when;
	}

	@Override
	public NominalGroup getWayAdverbial(){
		return how;
	}

	@Override
	public NominalGroup getPurposeAdverbial(){
		return why;
	}
}
