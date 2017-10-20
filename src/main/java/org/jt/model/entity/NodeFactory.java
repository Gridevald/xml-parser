package org.jt.model.entity;

public class NodeFactory {

    public static Node makeNode(String name) {
        ComplexNode node = new ComplexNode();
        node.setName(name);
        return node;
    }

    public static Node makeNode(String name, String value) {
        SimpleNode node = new SimpleNode();
        node.setName(name);
        node.setValue(value);
        return node;
    }
}
