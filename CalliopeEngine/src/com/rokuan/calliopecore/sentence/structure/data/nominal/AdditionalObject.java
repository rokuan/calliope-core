package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class AdditionalObject extends NominalGroup {
	@Expose
	public CountObject count;

	@Expose
	public CustomObject object;

	@Override
	public GroupType getGroupType() {
		return GroupType.OBJECT;
	}
}
