package com.mrkaurelius.yazlabII3;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class App {
    public static void main(String args[]) {
        GraphBuilder gb = new GraphBuilder("Tutorial 1", "graphs/basicgraph.tgf");
        Graph graph = gb.getGraph();
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));

        for (Node node : graph) {
            System.out.println(node.toString());
        }

        // Edge myEdge = graph.getEdge("AB");
        // myEdge.addAttribute("ui.class", "important");
        // Node myNode = graph.getNode("B");
        // myNode.addAttribute("ui.class", "important");


        graph.display();
        graph.toString();

        // Scanner scanner = new Scanner(System.in);
        // System.out.print("> ");
        // String input = scanner.nextLine();
        // System.out.println(input);
        // scanner.close();
    }

}
