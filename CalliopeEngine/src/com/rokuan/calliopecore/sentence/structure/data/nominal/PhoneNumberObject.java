package com.rokuan.calliopecore.sentence.structure.data.nominal;


public class PhoneNumberObject extends NominalGroup {
    public String number;

    @Override
    public GroupType getGroupType() {
        return GroupType.PHONE_NUMBER;
    }
}
