package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomPerson;

public class AdditionalPerson extends NominalGroup {
	@Expose
	public CustomPerson person;

	@Override
	public GroupType getGroupType() {
		return GroupType.PERSON;
	}
}
