package com.rokuan.calliopecore.sentence.structure.data.purpose;

public class VerbalPurposeObject extends PurposeAdverbial {
	public PurposeContext purposePreposition;

	@Override
	public PurposeType getPurposeType() {
		return PurposeType.VERBAL;
	}
}
