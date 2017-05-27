package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.ICustomObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.ISecondObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class AdditionalObject extends NominalGroup implements ISecondObject {
    public CountObject count;
    public ICustomObject object;
    public INominalObject of;
    public IVerbalObject which;

    @Override
    public GroupType getGroupType() {
        return GroupType.OBJECT;
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
