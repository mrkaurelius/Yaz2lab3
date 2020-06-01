package com.mrkaurelius.yazlabII3;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import com.mrkaurelius.yazlabII3.BasicGraph.BasicEdge;
import com.mrkaurelius.yazlabII3.BasicGraph.BasicNode;

/**
 * GraphBuilder Initilize Graph
 */

// TODO
// add css attributes
// where to put css files
public class GraphBuilder {

    // private final String NODE_ATTRIBUTE_KEY = "type";
    // private final String EDGE_ATTRIBUTE_KEY = "capacity";

    private Graph graph;
    private TGFReader graphReader;
    private Node startingNode;
    // private String TGFFilename;

    public GraphBuilder(String graphName, String filename) {
        this.graph = new MultiGraph(graphName);
        this.graph.addAttribute("ui.quality");
        this.graph.addAttribute("ui.antialias");
        this.graph.setStrict(false);
        this.graph.setAutoCreate(true);
        this.graphReader = new TGFReader(filename);

        // looking for relative path
        this.graph.addAttribute("ui.stylesheet", "url('file:src/main/resources/css/ui.css')");
        initGraph();
    }

    public int initGraph() {
        graphReader.parseFile();
        List<BasicEdge> edges = graphReader.getEdges();
        List<BasicNode> nodes = graphReader.getNodes();

        for (BasicNode n : nodes) {
            Node newNode = graph.addNode(n.getId());
            if (n.getAttribute() != null) {
                if (n.getAttribute().equals("T")) {
                    newNode.addAttribute("type", "target");
                    newNode.addAttribute("ui.class", "target");
                    newNode.addAttribute("ui.label", n.getId());
                    continue;
                }
                if (n.getAttribute().equals("S")) {
                    newNode.addAttribute("type", "source");
                    newNode.addAttribute("ui.class", "source");
                    newNode.addAttribute("ui.label", n.getId());
                    setStartingNode(newNode);
                    continue;
                }
            }
            newNode.addAttribute("ui.label", n.getId());
            newNode.addAttribute("type", "normal");
        }

        // edge logic incomplete finis after doin graph things
        for (BasicEdge e : edges) {
            Edge edge = graph.addEdge(e.getId(), e.getSourceID(), e.getDestinationID(), true);
            edge.addAttribute("ui.label",  "0/" + e.getAttribute());
            edge.addAttribute("capacity", Integer.parseInt(e.getAttribute()));
            edge.addAttribute("flow", 0);
            edge.addAttribute("type", "normal");
            Edge resudialEdge = graph.addEdge(e.getId()+"R", e.getDestinationID(), e.getSourceID(), true);
            resudialEdge.addAttribute("ui.label", "0/0");
            resudialEdge.addAttribute("capacity", 0);
            resudialEdge.addAttribute("flow", 0);
            resudialEdge.addAttribute("type", "residual");
            resudialEdge.addAttribute("ui.class", "residual");
        }

        return 0;
    }


    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public TGFReader getGraphReader() {
        return graphReader;
    }

    public void setGraphReader(TGFReader graphReader) {
        this.graphReader = graphReader;
    }

    public Node getStartingNode() {
        return startingNode;
    }

    public void setStartingNode(Node startingNode) {
        this.startingNode = startingNode;
    }

}