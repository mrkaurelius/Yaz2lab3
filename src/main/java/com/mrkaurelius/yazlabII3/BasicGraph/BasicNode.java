package com.mrkaurelius.yazlabII3.BasicGraph;

// class for data represantion, not for logic. At least now
// nodes and edges separated each other
// if have time make attribute string -> string hashmap
public class BasicNode {
    String id;
    String attribute;

    public BasicNode(String id, String attribute) {
        this.id = id;
        this.attribute = attribute;
    }

    public BasicNode() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Node [attribute=" + attribute + ", id=" + id + "]";
    }
    
}