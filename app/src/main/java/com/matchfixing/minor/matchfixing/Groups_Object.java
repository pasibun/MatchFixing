package com.matchfixing.minor.matchfixing;

import java.util.ArrayList;
import java.util.List;

public class Groups_Object{
    private List<Users_Object> groupinvite;
    private String groupName;

    public Groups_Object(String groupName){
        groupinvite = new ArrayList<Users_Object>();
        this.groupName = groupName;
    }

    public List<Users_Object> getGroupinvite() {
        return groupinvite;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupinvite(List<Users_Object> groupinvite) {
        this.groupinvite = groupinvite;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
