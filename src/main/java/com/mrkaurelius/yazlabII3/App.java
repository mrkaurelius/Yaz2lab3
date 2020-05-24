package com.mrkaurelius.yazlabII3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.math3.util.Pair;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.sync.SourceTime;

import scala.collection.parallel.ParIterableLike.Foreach;

import static com.mrkaurelius.yazlabII3.VisualHelper.*;

public class App {
    public static void main(String args[]) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        GraphBuilder gb = new GraphBuilder("maxflowmincut", "graphs/graph2.tgf");
        Graph graph = gb.getGraph();
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // printGraphNodeAttributes(graph);

        // TODO:
        // for testing graph generators maybe useful
        // in vertices, input equal to autput
        // find different paths to sink
        // add residual edges to graph after augmenting path founded
        // implement bfs returns augmented path
        // implement edmun karps method
        // retrive edges from nodes or track edges

        Node start = graph.getNode("S");
        edmondsKarp(start);
        graph.display();
    }

    // first implement with naive method (dont use residual edges)
    // naive alhoritm works
    private static int edmondsKarp(Node start) {
        int flow = 0;
        List<Pair<Integer, Edge>> bottleneckPairs = new ArrayList<>();

        // store edges not nodes
        // bfs implicitly returns path
        List<Node> path = bfs(start);
        while (!path.isEmpty()) {
            // we found an augmenting path
            List<Edge> pathEdges = retriveEdges(path);
            System.out.println(pathEdges.toString());

            // find bottleneck
            Pair<Integer, Edge> bottleneck = findBottleNeck(pathEdges);
            System.out.println("boottleneck: " + bottleneck.toString());
            int bottleneckFlow = bottleneck.getKey();
            for (Edge edge : pathEdges) {
                int currentFlow = edge.getAttribute("flow");
                edge.setAttribute("flow", currentFlow + bottleneckFlow);
                updateEdgeLabel(edge, currentFlow + bottleneckFlow);
            }

            // printGraphEdgeAttributes(start.getGraph());
            // add bottlenect edge to mincut list
            // alter flow according to path
            // look for another augmenting path
            path = bfs(start);
        }
        // no augmenting path avaliable
        System.out.println("yollarin sonu");
        return flow;
    }


    // upgrade method for accepting residual edge logic
    private static void updateEdgeLabel(Edge edge, int newFlow) {
        System.out.println("update label");
        System.out.println(String.valueOf(newFlow));
        edge.setAttribute("ui.label", edge.getAttribute("capacity") + "/" + String.valueOf(newFlow));
    }

    // upgrade method for accepting residual edge logic
    private static Pair<Integer, Edge> findBottleNeck(List<Edge> edges) {
        int bottleneck = Integer.MAX_VALUE;
        Edge bottleneckEdge = null;
        for (Edge edge : edges) {
            int diff = (int) edge.getAttribute("capacity") - (int) edge.getAttribute("flow");
            if (diff < bottleneck) {
                bottleneck = diff;
                bottleneckEdge = edge;
            }
        }
        return new Pair<Integer, Edge>(bottleneck, bottleneckEdge);
    }
    // modify for augmenting edges
    private static boolean canHaveFlow(Edge e) {
        int cap = e.getAttribute("capacity");
        int flow = e.getAttribute("flow");
        // System.out.println(cap+","+ flow);
        printEdgeAttributes(e);
        if (cap > flow)
            return true;
        return false;
    }

    private static List<Node> bfs(Node start) {
        Graph g = start.getGraph();
        for (Node node : g) {
            node.removeAttribute("parent");
            markunVisited(node);
        }
        Queue<Node> queue = new LinkedList<>();
        markVisited(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            System.out.println(queue.toString());
            Node n = queue.remove();
            if (checkisEnd(n)) {
                // return path here
                return retrivePath(n);
            }
            for (Edge edge : n.getEachLeavingEdge()) {
                Node next = edge.getNode1();
                // check visited, cap > flow
                if (!checkVisited(next) && canHaveFlow(edge)) {
                    markVisited(next);
                    queue.add(next);
                    setParent(next, n);
                }
            }
        }
        return Collections.emptyList();
    }


    private static List<Edge> retriveEdges(List<Node> path) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            edges.add(path.get(i).getEdgeToward(path.get(i + 1)));
        }
        return edges;
    }

    private static List<Node> retrivePath(Node node) {
        Graph graph = node.getGraph();
        List<Node> path = new ArrayList<>();
        path.add(node);
        while (node.hasAttribute("parent")) {
            String parentId = node.getAttribute("parent");
            node = graph.getNode(parentId);
            path.add(node);
        }
        // dont really need reversing
        Collections.reverse(path);
        System.out.println("path retrived: " + path.toString());
        return path;
    }

    private static void dfs_iter(Node start) {
        Graph g = start.getGraph();
        Stack<Node> stack = new Stack<>();
        System.out.println("dfs iter started");
        for (Node node : g) {
            start.removeAttribute("parent");
            markunVisited(node);
        }
        stack.push(start);
        while (!stack.isEmpty()) {
            System.out.println(stack.toString());
            Node n = stack.pop();
            markNodeUI(n, "important");
            sleep();

            if (!checkVisited(n)) {
                markVisited(n);
                markNodeUI(n, "visited");
                sleep();
                System.out.println(n);
                for (Edge edge : n.getEachLeavingEdge()) {
                    Node next = edge.getNode1();
                    if (!checkVisited(next)) {
                        stack.push(next);
                    }
                }

            }
        }
    }

    private static void setParent(Node child, Node parent) {
        child.setAttribute("parent", parent.getId());
    }

    private static void markVisited(Node node) {
        node.addAttribute("visited", true);
    }

    private static void markunVisited(Node node) {
        node.addAttribute("visited", false);
    }

    private static boolean checkVisited(Node node) {
        return node.getAttribute("visited");
    }

    private static boolean checkisEnd(Node node) {
        if (node.getAttribute("type").equals("target"))
            return true;
        return false;
    }

    private static void printNodeAttributes(Node node) {
        for (String keys : node.getAttributeKeySet()) {
            System.out.println(keys + ", " + node.getAttribute(keys));
        }
    }

    private static void printEdgeAttributes(Edge edge) {
        System.out.println(edge.toString());
        System.out.println("cap: " + edge.getAttribute("capacity"));
        System.out.println("flow: " + edge.getAttribute("flow"));
        System.out.println("type: " + edge.getAttribute("type"));
    }

    private static void printGraphNodeAttributes(Graph graph) {
        for (Node node : graph) {
            for (String keys : node.getAttributeKeySet()) {
                System.out.println(keys + ", " + node.getAttribute(keys));
            }
            System.out.println();
        }
    }

    private static void printGraphEdgeAttributes(Graph graph) {
        for (Edge edge : graph.getEdgeSet()) {
            System.out.println(edge.getId());
            System.out.println("cap: " + edge.getAttribute("capacity"));
            System.out.println("flow: " + edge.getAttribute("flow"));
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
