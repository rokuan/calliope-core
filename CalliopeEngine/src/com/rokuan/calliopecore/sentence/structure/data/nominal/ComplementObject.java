package com.rokuan.calliopecore.sentence.structure.data.nominal;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class ComplementObject extends NominalGroup implements IWayObject {
	@Expose
	private WayContext wayPreposition;
	
	@Expose
	public CountObject count = new AllItemsObject();

	@Expose
	public List<Adjective> adjectives = new ArrayList<Adjective>();

	@Expose
    public String object = "";

	@Expose
    public ComplementObject of;

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
    	return object;
    }

	@Override
	public GroupType getGroupType() {
		return GroupType.COMPLEMENT;
	}

	@Override
	public WayType getWayType() {
		return WayType.NOMINAL;
	}

	@Override
	public WayContext getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		wayPreposition = prep;
	}
}