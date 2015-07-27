package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.Action;

public interface IVerbalContent {
	void setAction(Action.VerbAction action);

	Action.VerbAction getAction();
}
