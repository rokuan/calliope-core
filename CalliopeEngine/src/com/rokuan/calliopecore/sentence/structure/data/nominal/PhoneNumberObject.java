package com.rokuan.calliopecore.sentence.structure.data.nominal;


public class PhoneNumberObject extends NominalGroup {
    public final String number;

    public PhoneNumberObject(String n) {
        number = n;
    }

    @Override
    public GroupType getGroupType() {
        return GroupType.PHONE_NUMBER;
    }
}
