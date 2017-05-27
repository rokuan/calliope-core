package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class PersonObject implements INominalObject {
    public final String name;

    public PersonObject(String n) {
        name = n;
    }

    @Override
    public GroupType getGroupType() {
        return GroupType.PERSON;
    }
}
