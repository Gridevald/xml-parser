package org.jt.model.entity;

import java.util.Map;
import java.util.Objects;

public class SimpleNode implements Node {

    private String name;

    private String value;

    private Map<String, String> attributes;

    public SimpleNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleNode)) return false;
        SimpleNode that = (SimpleNode) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getAttributes(), that.getAttributes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue(), getAttributes());
    }

    @Override
    public String toString() {
        return "SimpleNode{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
