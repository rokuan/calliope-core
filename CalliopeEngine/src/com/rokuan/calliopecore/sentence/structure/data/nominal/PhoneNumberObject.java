package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;

public class PhoneNumberObject extends NominalGroup {
	@Expose
	public String number;

	@Override
	public GroupType getGroupType() {
		return GroupType.PHONE_NUMBER;
	}
}
