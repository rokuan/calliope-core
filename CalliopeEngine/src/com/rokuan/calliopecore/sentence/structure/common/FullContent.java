package com.rokuan.calliopecore.sentence.structure.common;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial;

public abstract class FullContent implements ISubjectContent, IVerbalContent, IComplementContent {
	@Expose
	public NominalGroup subject;

	@Expose
	public Action.VerbAction action;

	@Expose
	public NominalGroup what;

	@Expose
	public NominalGroup target;

	@Expose
	public PlaceAdverbial where;

	@Expose
	public TimeAdverbial when;

	@Expose
	public WayAdverbial how;

	@Expose
	public PurposeAdverbial why;

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
	public void setPlaceAdverbial(PlaceAdverbial place){
		where = place;
	}

	@Override
	public void setTimeAdverbial(TimeAdverbial time){
		when = time;
	}

	@Override
	public void setWayAdverbial(WayAdverbial way){
		how = way;
	}

	@Override
	public void setPurposeAdverbial(PurposeAdverbial purpose){
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
	public PlaceAdverbial getPlaceAdverbial(){
		return where;
	}

	@Override
	public TimeAdverbial getTimeAdverbial(){
		return when;
	}

	@Override
	public WayAdverbial getWayAdverbial(){
		return how;
	}

	@Override
	public PurposeAdverbial getPurposeAdverbial(){
		return why;
	}
}
