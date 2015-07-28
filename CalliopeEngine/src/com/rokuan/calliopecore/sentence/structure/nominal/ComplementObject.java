package com.rokuan.calliopecore.sentence.structure.nominal;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class ComplementObject extends NominalGroup {
	@Expose
	public CountObject count = new AllItemsObject();

	@Expose
	public List<Adjective> adjectives = new ArrayList<Adjective>();

	@Expose
    public String object = "";

	@Expose
    public ComplementObject of;

	@Expose
    public TimeAdverbial when;

	@Expose
    public ComplementObject why;

	@Expose
    public PlaceAdverbial where;

	@Expose
    public ComplementObject how;

	@Expose
    public List<CriterionObject> criteria = new ArrayList<CriterionObject>();

    public ComplementObject(){
    	super(GroupType.COMPLEMENT);
    }
    
    @Override
    public String toString(){
    	// TODO: ajouter les autres attributs si besoin
    	return object;
    }
}
