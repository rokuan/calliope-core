package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public interface ISubjectContent {
	void setSubject(NominalGroup subj);
	
	NominalGroup getSubject();
}
