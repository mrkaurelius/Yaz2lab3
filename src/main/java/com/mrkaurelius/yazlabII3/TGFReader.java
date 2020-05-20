package com.mrkaurelius.yazlabII3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.*;

import com.mrkaurelius.yazlabII3.BasicGraph.BasicNode;
import com.mrkaurelius.yazlabII3.BasicGraph.BasicEdge;

/*
 * Trivial Graph Format Reader
 * Initilize node and edges here
 */

public class TGFReader {
    private List<BasicNode> nodes = new ArrayList<>();
    private List<BasicEdge> edges = new ArrayList<>();
    private String filename;

    // private final String NODE_ATTRIBUTE_KEY = "type";
    // private final String EDGE_ATTRIBUTE_KEY = "capacity";

    public TGFReader(String filename) {
        this.filename = filename;
    }

    public TGFReader() { }

    public int parseFile() {
        // search file and find # else throw exception
        BufferedReader reader;

        boolean isNode = true;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                if (line.charAt(0) == '#') {
                    isNode = false;
                    line = reader.readLine();
                    continue;
                }

                if (isNode) {
                    nodes.add(parseNodeLine(line));
                } else {
                    edges.add(parseEdgeLine(line));
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            ;
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }

        for (BasicNode node : nodes) {
            //System.out.println(node.toString());
        }
        for (BasicEdge edge : edges) {
            //System.out.println(edge.toString());
        }

        return 1;
    }

    private BasicEdge parseEdgeLine(String line) {
        BasicEdge edge = new BasicEdge();
        String[] parts = line.split(" ");
        edge.setId(parts[0]+parts[1]);
        edge.setSourceID(parts[0]);
        edge.setDestinationID(parts[1]);
        edge.setAttribute(parts[2]);
        return edge;
    }

    private BasicNode parseNodeLine(String line) {
        BasicNode node = new BasicNode();
        String[] parts = line.split(" ");
        node.setId(parts[0]);

        if (parts.length == 2) {
            node.setAttribute(parts[1]);
        }
        return node;
    }

    public List<BasicNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<BasicNode> nodes) {
        this.nodes = nodes;
    }

    public List<BasicEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<BasicEdge> edges) {
        this.edges = edges;
    }

    /*
     * 1 First node 2 Second node # 1 2 Edge between the two node:
     * node: <node-id> <node-attribute> 
     * edge: <start-node> <end-node><edge-attribute>
     * 
     * // if reasonable do this 
     * node: <node-id> <node-attribute-key>:<node=attribute-value>...
     * edge: <start-node><end-node> <edge-attribute>...
     */
}