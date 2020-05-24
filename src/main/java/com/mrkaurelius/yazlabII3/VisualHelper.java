package com.mrkaurelius.yazlabII3;

import org.graphstream.graph.*;

public class VisualHelper {
    public static void markNodeUI(Node node, String type) {
        node.addAttribute("ui.class", type);
    }
}