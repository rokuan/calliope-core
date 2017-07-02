package com.rokuan.calliopecore.sentence.structure.data.nominal;

public class TextObject extends NominalGroup {
    public String text;

    public TextObject(String s) {
        text = s;
    }

    @Override
    public GroupType getGroupType() {
        return GroupType.TEXT;
    }
}
