package com.rokuan.calliopecore.sentence.structure.common;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ActionObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;

//public abstract class FullContent implements ISubjectContent, IVerbalContent, IComplementContent {
public abstract class FullContent implements IVerbalObject {
	@Expose
	public INominalObject subject;

	@Expose
	public ActionObject action;

	@Expose
	public INominalObject what;

	@Expose
	public INominalObject target;

	@Expose
	public IPlaceObject where;

	@Expose
	public ITimeObject when;

	@Expose
	public IWayObject how;

	@Expose
	public IPurposeObject why;

	@Override
	public void setSubject(INominalObject source){
		subject = source;
	}
	
	@Override
	public void setAction(ActionObject verb){
		action = verb;
	}
	
	@Override
	public void setDirectObject(INominalObject direct){
		what = direct;
	}

	@Override
	//public void setIndirectObject(INominalObject indirect){
	public void setTarget(INominalObject indirect){
		target = indirect;
	}

	@Override
	public void setPlaceAdverbial(IPlaceObject place){
		where = place;
	}

	@Override
	public void setTimeAdverbial(ITimeObject time){
		when = time;
	}

	@Override
	public void setWayAdverbial(IWayObject way){
		how = way;
	}

	@Override
	public void setPurposeAdverbial(IPurposeObject purpose){
		why = purpose;
	}

	@Override
	public INominalObject getSubject(){
		return subject;
	}
	
	@Override
	public ActionObject getAction(){
		return action;
	}
	
	@Override
	public INominalObject getDirectObject(){
		return what;
	}

	@Override
	//public INominalObject getIndirectObject(){
	public INominalObject getTarget(){
		return target;
	}

	@Override
	public IPlaceObject getPlaceAdverbial(){
		return where;
	}

	@Override
	public ITimeObject getTimeAdverbial(){
		return when;
	}

	@Override
	public IWayObject getWayAdverbial(){
		return how;
	}

	@Override
	public IPurposeObject getPurposeAdverbial(){
		return why;
	}
}
