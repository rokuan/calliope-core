package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.ISecondObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class AdditionalObject extends NominalGroup implements ISecondObject {
	@Expose
	public CountObject count;

	@Expose
	public CustomObject object;
	
	@Expose
	public INominalObject of;
	
	@Expose
	public IVerbalObject which;

	@Override
	public GroupType getGroupType() {
		return GroupType.OBJECT;
	}

	@Override
	public void setNominalSecondObject(INominalObject nObject) {
		of = nObject;
	}

	@Override
	public void setVerbalSecondObject(IVerbalObject vObject) {
		which = vObject;
	}

	@Override
	public INominalObject getNominalSecondObject() {
		return of;
	}

	@Override
	public IVerbalObject getVerbalSecondObject() {
		return which;
	}
}
