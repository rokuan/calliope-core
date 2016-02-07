package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.ICustomPerson;

public class AdditionalPerson extends NominalGroup {
	public ICustomPerson person;

	@Override
	public GroupType getGroupType() {
		return GroupType.ADDITIONAL_PERSON;
	}
}
