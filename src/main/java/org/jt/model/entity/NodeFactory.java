package org.jt.model.entity;

import java.util.Map;

public class NodeFactory {

    public static Node makeNode(String name, Map<String, String> attributes) {
        ComplexNode node = new ComplexNode();
        node.setName(name);
        node.setAttributes(attributes);
        return node;
    }

    public static Node makeNode(String name, String value, Map<String, String> attributes) {
        SimpleNode node = new SimpleNode();
        node.setName(name);
        node.setValue(value);
        node.setAttributes(attributes);
        return node;
    }
}
