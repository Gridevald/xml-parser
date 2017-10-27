package org.jt.view;

import org.jt.model.entity.ComplexNode;
import org.jt.model.entity.Node;
import org.jt.model.entity.SimpleNode;

import java.util.List;

public class NodePrinter {

    public static void consolePrint(Node node) {
        StringBuilder sb = new StringBuilder();

        appendNode(sb, node, 0);

        System.out.println(sb);
    }

    private static void appendNode(StringBuilder sb, Node node, int dashNumber) {

        for(int i = 0; i < dashNumber; i++) {
            sb.append("-");
        }

        if (node instanceof SimpleNode) {
            SimpleNode sn = (SimpleNode) node;
            sb.append(sn.getName());
            sb.append(": ");
            sb.append(sn.getValue());
            if (!sn.getAttributes().isEmpty()) {
                sb.append(sn.getAttributes());
            }
            sb.append("\n");
        } else {
            ComplexNode cn = (ComplexNode) node;
            sb.append(cn.getName());
            if (!cn.getAttributes().isEmpty()) {
                sb.append(cn.getAttributes());
            }
            sb.append("\n");

            if (cn.isInnerNodes()) {
                List<Node> innerNodes = cn.getInnerNodes();
                for (Node innerNode : innerNodes) {
                    appendNode(sb, innerNode, dashNumber + 2);
                }
            }
        }
    }
}
