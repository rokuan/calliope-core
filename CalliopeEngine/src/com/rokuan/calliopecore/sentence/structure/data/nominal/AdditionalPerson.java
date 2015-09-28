package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ICustomPerson;

public class AdditionalPerson extends NominalGroup {
	@Expose
	public ICustomPerson person;

	@Override
	public GroupType getGroupType() {
		return GroupType.PERSON;
	}
}
