package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ActionObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;


public class VerbalGroup extends NominalGroup implements IWayObject, ITimeObject, IPurposeObject, IVerbalObject {
	@Expose
	private WayContext wayPreposition;
	
	@Expose
	private TimeContext timePreposition;
	
	@Expose
	private PurposeContext purposePreposition;
	
	@Expose
	private INominalObject subject;

	@Expose
	private ActionObject action = null;

	@Expose
	private INominalObject what;

	@Expose
	private INominalObject target;

	@Expose
	private IPlaceObject where;

	@Expose
	private ITimeObject when;

	@Expose
	private IWayObject how;

	@Expose
	private IPurposeObject why;
	
	@Override
	public GroupType getGroupType() {
		return GroupType.VERB;
	}

	@Override
	public WayType getWayType() {
		return WayType.VERBAL;
	}

	@Override
	public PurposeType getPurposeType() {
		return PurposeType.VERBAL;
	}

	@Override
	public TimeType getTimeType() {
		return TimeType.VERBAL;
	}

	@Override
	public PurposeContext getPurposePreposition() {
		return purposePreposition;
	}

	@Override
	public void setPurposePreposition(PurposeContext prep) {
		purposePreposition = prep;
	}

	@Override
	public TimeContext getTimePreposition() {
		return timePreposition;
	}

	@Override
	public void setTimePreposition(TimeContext prep) {
		timePreposition = prep;
	}

	@Override
	public WayContext getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		wayPreposition = prep;		
	}

	@Override
	public void setSubject(INominalObject source){
		subject = source;
	}
	
	@Override
	public void setAction(ActionObject act){
		action = act;
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
