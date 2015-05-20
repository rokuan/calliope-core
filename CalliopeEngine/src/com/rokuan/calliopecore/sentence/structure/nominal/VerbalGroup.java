package com.rokuan.calliopecore.sentence.structure.nominal;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;

public class VerbalGroup {
	public Enum<?> action;
    public NominalGroup target;	
    public CountObject count = new CountObject();
	public List<Adjective> acjectives = new ArrayList<Adjective>();
	public NominalGroup what;
    public TimeObject when;
    //public ComplementObject why;
    public PlaceObject where;
    public NominalGroup how;
}
