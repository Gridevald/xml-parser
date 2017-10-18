package org.jt.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;

    private List<Node> innerNodes;

    private Map<String, String> parameters;

    public Node() {
        innerNodes = new ArrayList<>();
        parameters = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNode(Node node) {
        innerNodes.add(node);
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }
}
