package com.rokuan.calliopecore.sentence;

public interface IPronoun {
	public enum PronounSource {
        I,
        YOU,
        HE,
        SHE,
        IT,
        HE_SHE,
        WE,
        YOU_,
        HE_,
        SHE_,
        HE__SHE__,
        UNDEFINED
    }
	
	PronounSource getSource();
}
