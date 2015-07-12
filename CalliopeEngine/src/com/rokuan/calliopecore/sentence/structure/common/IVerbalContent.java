package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.Action;

public interface IVerbalContent {
	/*void setAction(Enum<?> action);

	Enum<?> getAction();*/
	void setAction(Action.VerbAction action);

	Action.VerbAction getAction();
}
