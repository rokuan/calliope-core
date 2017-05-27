package com.rokuan.calliopecore.sentence.structure.data.purpose;

import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

public abstract class PurposeAdverbial implements IPurposeObject {
    public enum PurposeContext {
        TO
    }

    public enum PurposeType {
        VERBAL,
        INFINITIVE_VERB,
        CONJUGATED_VERB
    }

    public static Class<? extends IPurposeObject> getClassFromPurposeType(PurposeType ty) {
        Class<? extends IPurposeObject> clazz = null;

        switch (ty) {
            case VERBAL:
                clazz = VerbalGroup.class;
                break;
            default:
                break;
        }

        return clazz;
    }
}
