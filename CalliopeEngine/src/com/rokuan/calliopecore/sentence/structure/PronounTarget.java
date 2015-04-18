package com.rokuan.calliopecore.sentence.structure;

import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Type.Pronoun;

public class PronounTarget extends NominalGroup {
	public Pronoun pronoun;

	public PronounTarget(Type.Pronoun pro){
		super(GroupType.PRONOUN);
		pronoun = pro;
    }
}
