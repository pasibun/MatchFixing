package com.matchfixing.minor.matchfixing;

import java.util.ArrayList;
import java.util.List;

public class Groups_Object{
    private List<String> groupinvite;
    private String groupName;

    public Groups_Object(String groupName){
        groupinvite = new ArrayList<>();
        this.groupName = groupName;
    }

    public List<String> getGroupinvite() {
        return groupinvite;
    }

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
