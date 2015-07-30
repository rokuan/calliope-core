package com.rokuan.calliopecore.sentence.structure.data.purpose;

public abstract class PurposeAdverbial {
	public enum PurposeContext {
		TO
	}
	
	public enum PurposeType {
		VERBAL
	}
	
	private PurposeType purposeType;
	
	protected PurposeAdverbial(PurposeType ty){
		purposeType = ty;
	}

	public PurposeType getPurposeType() {
		return purposeType;
	}
}
