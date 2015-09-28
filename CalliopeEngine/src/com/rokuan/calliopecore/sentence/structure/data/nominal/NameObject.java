package com.rokuan.calliopecore.sentence.structure.data.nominal;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo;
import com.rokuan.calliopecore.sentence.INameInfo;
import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ISecondObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class NameObject extends NominalGroup implements IPlaceObject, IWayObject, ISecondObject {
	@Expose
	private IPlacePreposition placePreposition;
	
	@Expose
	private IWayPreposition wayPreposition;
	
	@Expose
	public CountObject count = new AllItemsObject();

	@Expose
	public List<IAdjectiveInfo> adjectives = new ArrayList<IAdjectiveInfo>();

	@Expose
    public INameInfo object;

	@Expose
    private INominalObject of;
	
	@Expose
	private IVerbalObject which;

	@Expose
    public ITimeObject when;

	@Expose
    public IPurposeObject why;

	@Expose
    public IPlaceObject where;

	@Expose
    public IWayObject how;

	@Expose
    public List<CriterionObject> criteria = new ArrayList<CriterionObject>();
	
    @Override
    public String toString(){
    	// TODO: ajouter les autres attributs si besoin
    	return String.valueOf(object);
    }

	@Override
	public GroupType getGroupType() {
		return GroupType.COMMON_NAME;
	}

	@Override
	public WayType getWayType() {
		return WayType.NOMINAL;
	}

	@Override
	public IWayPreposition getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(IWayPreposition prep) {
		wayPreposition = prep;
	}

	@Override
	public void setNominalSecondObject(INominalObject nObject) {
		of = nObject;
	}

	@Override
	public void setVerbalSecondObject(IVerbalObject vObject) {
		which = vObject;
	}

	@Override
	public INominalObject getNominalSecondObject() {
		return of;
	}

	@Override
	public IVerbalObject getVerbalSecondObject() {
		return which;
	}

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.NOMINAL;
	}

	@Override
	public IPlacePreposition getPlacePreposition() {
		return placePreposition;
	}

	@Override
	public void setPlacePreposition(IPlacePreposition prep) {
		placePreposition = prep;
	}
}
