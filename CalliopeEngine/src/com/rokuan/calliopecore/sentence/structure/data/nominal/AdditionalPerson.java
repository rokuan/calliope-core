package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.ICustomPerson;

public class AdditionalPerson extends NominalGroup {
    public final ICustomPerson person;

    public AdditionalPerson(ICustomPerson p) {
        person = p;
    }

    @Override
    public GroupType getGroupType() {
        return GroupType.ADDITIONAL_PERSON;
    }
}
