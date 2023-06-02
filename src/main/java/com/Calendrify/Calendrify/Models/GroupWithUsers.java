package com.Calendrify.Calendrify.Models;

import java.util.List;

public class GroupWithUsers {
    Usergroup userGroup;

    List<Usergroupmapping> userGroupMappingList;

    public GroupWithUsers(Usergroup userGroup, List<Usergroupmapping> userGroupMappingList) {
        this.userGroup = userGroup;
        this.userGroupMappingList = userGroupMappingList;
    }

    public Usergroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Usergroup userGroup) {
        this.userGroup = userGroup;
    }

    public List<Usergroupmapping> getUserGroupMappingList() {
        return userGroupMappingList;
    }

    public void setUserGroupMappingList(List<Usergroupmapping> userGroupMappingList) {
        this.userGroupMappingList = userGroupMappingList;
    }
}
