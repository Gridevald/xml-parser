package org.jt.view;

import org.jt.model.entity.ComplexNode;
import org.jt.model.entity.Node;
import org.jt.model.entity.SimpleNode;

import java.util.List;

public class NodePrinter {

    private static final String DASH = "--";

    private static final int START_DASH_NUMBER = 0;

    private static final int ADD_DASH_COEFFICIENT = 1;

    private static final String NAME_VALUE_SEPARATOR = ": ";

    public static void consolePrint(Node node) {
        StringBuilder builder = new StringBuilder();

        appendNode(builder, node, START_DASH_NUMBER);

        System.out.println(builder);
    }

    private static void appendNode(StringBuilder builder, Node node, int dashNumber) {
        for (int i = 0; i < dashNumber; i++) {
            builder.append(DASH);
        }

        if (node instanceof SimpleNode) {
            SimpleNode simpleNode = (SimpleNode) node;
            appendSimpleNode(builder, simpleNode);
        } else {
            ComplexNode complexNode = (ComplexNode) node;
            appendComplexNode(builder, complexNode);

            if (complexNode.isInnerNodes()) {
                List<Node> innerNodes = complexNode.getInnerNodes();
                for (Node innerNode : innerNodes) {
                    appendNode(builder, innerNode, dashNumber + ADD_DASH_COEFFICIENT);
                }
            }
        }
    }

    private static void appendSimpleNode(StringBuilder builder, SimpleNode node) {
        builder.append(node.getName());
        builder.append(NAME_VALUE_SEPARATOR);
        builder.append(node.getValue());
        if (!node.getAttributes().isEmpty()) {
            builder.append(node.getAttributes());
        }
        builder.append("\n");
    }

    private static void appendComplexNode(StringBuilder builder, ComplexNode node) {
        builder.append(node.getName());
        if (!node.getAttributes().isEmpty()) {
            builder.append(node.getAttributes());
        }
        builder.append("\n");
    }
}
