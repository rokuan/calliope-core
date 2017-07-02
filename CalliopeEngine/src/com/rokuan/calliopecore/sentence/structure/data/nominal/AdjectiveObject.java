package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.IAdjectiveInfo;

/**
 * Created by Christophe on 02/07/2017.
 */
public class AdjectiveObject extends NominalGroup {
    public final IAdjectiveInfo adjective;

    public AdjectiveObject(IAdjectiveInfo a) {
        adjective = a;
    }

    @Override
    public GroupType getGroupType() {
        return NominalGroup.GroupType.ADJECTIVE;
    }
}
