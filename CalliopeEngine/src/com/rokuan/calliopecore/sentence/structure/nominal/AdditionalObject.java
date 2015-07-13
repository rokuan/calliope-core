package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class AdditionalObject extends NominalGroup {
	public CountObject count;
	public CustomObject object;

	public AdditionalObject() {
		super(GroupType.OBJECT);
	}

}
