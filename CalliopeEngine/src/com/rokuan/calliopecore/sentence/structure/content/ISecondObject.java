package com.rokuan.calliopecore.sentence.structure.content;

public interface ISecondObject {
	void setNominalSecondObject(INominalObject nObject);
	void setVerbalSecondObject(IVerbalObject vObject);
	
	INominalObject getNominalSecondObject();
	IVerbalObject getVerbalSecondObject();
}
