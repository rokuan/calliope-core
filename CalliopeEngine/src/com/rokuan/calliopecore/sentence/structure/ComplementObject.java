package com.rokuan.calliopecore.sentence.structure;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class ComplementObject extends NominalGroup {
	public CountObject count = new CountObject();
	public List<Adjective> acjectives = new ArrayList<Adjective>();
    public String object = "";
    public ComplementObject of;
    public TimeObject when;
    public ComplementObject why;
    public PlaceObject where;
    public String how;
    public List<CriterionObject> criteria = new ArrayList<CriterionObject>();

    public ComplementObject(){
    	super(TargetType.NOMINAL_GROUP);
    }
}
