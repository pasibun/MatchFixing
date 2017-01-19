package com.matchfixing.minor.matchfixing;

import java.util.ArrayList;
import java.util.List;

public class Groups_Object{
    private List<String> groupinvite;
    private String groupName;
    private int groupID;

    public Groups_Object(String groupName, int groupID){
        groupinvite = new ArrayList<>();
        this.groupName = groupName;
        this.groupID = groupID;
    }

    public List<String> getGroupinvite() {
        return groupinvite;
    }
    public int getGroupID(){return groupID;}

    public void addMember(String member){groupinvite.add(member); }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupinvite(List<String> groupinvite) {
        this.groupinvite = groupinvite;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
