package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.ISecondObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class CharacterObject extends NominalGroup implements ISecondObject {
    public enum CharacterType {
        SON,
        DAUGHTER,
        FATHER,
        MOTHER,
        BROTHER,
        SISTER,
        COUSIN,
        UNCLE,
        AUNT,
        NEPHEW,
        NIECE,
        GRAND_FATHER,
        GRAND_MOTHER,
        GREAT_GRAND_FATHER,
        GREAT_GRAND_MOTHER,
        GRAND_SON,
        GRAND_DAUGHTER,
        NEIGHBOUR
    }

    public CountObject count;
    public CharacterType characterType;
    private INominalObject of;
    private IVerbalObject which;

    @Override
    public GroupType getGroupType() {
        return GroupType.CHARACTER;
    }

    @Override
    public void setNominalSecondObject(INominalObject nObject) {
        of = nObject;
    }

    @Override
    public void setVerbalSecondObject(IVerbalObject vObject) {
        which = vObject;
    }

    @Override
    public INominalObject getNominalSecondObject() {
        return of;
    }

    @Override
    public IVerbalObject getVerbalSecondObject() {
        return which;
    }
}
