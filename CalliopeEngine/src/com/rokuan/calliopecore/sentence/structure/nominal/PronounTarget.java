package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Type.Pronoun;

public class PronounTarget extends NominalGroup {
	@Expose
	public Pronoun pronoun;

	public PronounTarget(Type.Pronoun pro){
		super(GroupType.PRONOUN);
		pronoun = pro;
    }
}
