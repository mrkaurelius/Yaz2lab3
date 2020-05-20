package com.mrkaurelius.yazlabII3.BasicGraph;

// class for data represantion, not for logic. At least now
// nodes and edges separated each other
// if have time make attribute string -> string hashmap

public class BasicEdge {
    String id;
    String sourceID;
    String destinationID;
    String attribute;

    public BasicEdge(String id, String sourceID, String destinationID, String attribute) {
        this.id = id;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.attribute = attribute;
    }

    public BasicEdge() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Edge [attribute=" + attribute + ", destinationID=" + destinationID + ", id=" + id + ", sourceID="
                + sourceID + "]";
    }

}