package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomPerson;

public class AdditionalPerson extends NominalGroup {
	@Expose
	public CustomPerson person;
	
	public AdditionalPerson() {
		super(GroupType.PERSON);
	}
}
