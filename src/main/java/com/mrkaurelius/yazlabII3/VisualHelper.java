package com.mrkaurelius.yazlabII3;

import org.graphstream.graph.*;

public class VisualHelper {
    public static void markNodeUI(Node node, String type) {
        node.addAttribute("ui.class", type);
    }

    public static void markEdgeUI(Edge edge, String type) {
        edge.addAttribute("ui.class", type);
    }

    // print consol with color
    public static void printInfo(String s) {
        System.out.println("\u001B[33;9m" + s + "\033[0m");
    }
}