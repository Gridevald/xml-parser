package org.jt.model.entity;

import java.util.*;

public class ComplexNode implements Node {

    private String name;

    private List<Node> innerNodes;

    private Map<String, String> attributes;

    public ComplexNode() {
        innerNodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void addInnerNode(Node node) {
        innerNodes.add(node);
    }

    public void removeInnerNode(Node node) {
        innerNodes.remove(node);
    }

    public List<Node> getInnerNodes() {
        return innerNodes;
    }

    public boolean isInnerNodes() {
        return !innerNodes.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNode)) return false;
        ComplexNode that = (ComplexNode) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(innerNodes, that.innerNodes) &&
                Objects.equals(getAttributes(), that.getAttributes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), innerNodes, getAttributes());
    }

    @Override
    public String toString() {
        return "ComplexNode{" +
                "name='" + name + '\'' +
                ", innerNodes=" + innerNodes +
                ", attributes=" + attributes +
                '}';
    }
}
