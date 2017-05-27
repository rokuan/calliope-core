package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.IPronoun;

public class PronounSubject extends NominalGroup {
	public final IPronoun pronoun;

	public PronounSubject(IPronoun pro){
		pronoun = pro;
    }

	@Override
	public GroupType getGroupType() {
		return GroupType.PRONOUN;
	}
}
