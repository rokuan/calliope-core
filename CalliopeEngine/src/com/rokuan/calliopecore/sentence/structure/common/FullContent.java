package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.ActionObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;

public abstract class FullContent implements IVerbalObject {
	public INominalObject subject;
	public ActionObject action;
	public INominalObject what;
	public INominalObject target;
	public IPlaceObject where;
	public ITimeObject when;
	public IWayObject how;
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
