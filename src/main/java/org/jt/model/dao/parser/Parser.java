package org.jt.model.dao.parser;

import org.jt.model.entity.ComplexNode;
import org.jt.model.entity.Node;
import org.jt.model.entity.NodeFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Simplified patterns of open tag, close tag,
     * tag without inner tags and tag attributes.
     */
    private static final Pattern OPEN_TAG =
            Pattern.compile("<([\\p{Alnum}-_.]+)\\p{Blank}*" +
                    "(\\p{Blank}*[\\p{Alnum}-_.]+\\p{Blank}*=\\p{Blank}*\"[\\p{Graph}]+\")*>");

    private static final Pattern CLOSE_TAG =
            Pattern.compile("</\\p{Blank}*[\\p{Alnum}-_.]+\\p{Blank}*>");

    private static final Pattern SIMPLE_NODE =
            Pattern.compile("<([\\p{Alnum}-_.]+)\\p{Blank}*" +
                    "(\\p{Blank}*[\\p{Alnum}-_.]+\\p{Blank}*=\\p{Blank}*\"[\\p{Graph}]+\")*>" +
                    "([\\p{Graph}\\p{Blank}]+)" +
                    "</\\p{Blank}*[\\p{Alnum}-_.]+\\p{Blank}*>");

    private static final Pattern ATTRIBUTE =
            Pattern.compile("([\\p{Alnum}-_.]+)\\p{Blank}*=\\p{Blank}*\"([\\p{Graph}]+)\"");

    private static final String OPEN_BRACKET = "<";

    private static final int NAME_GROUP = 1;

    private static final int VALUE_GROUP = 3;

    private static final int ATTR_NAME_GROUP = 1;

    private static final int ATTR_VALUE_GROUP = 2;

    /**
     * Parses String into tree-like-Node
     */
    public Node parseXml(String xmlLine) {
        String copy = "";
        String nextChar = "";
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < xmlLine.length() - 1; i++) {
            copy += xmlLine.charAt(i);
            nextChar = String.valueOf(xmlLine.charAt(i + 1));

            if (OPEN_TAG.matcher(copy).matches() && nextChar.equals(OPEN_BRACKET)) {
                stack.push(makeComplexNode(copy));
                copy = "";
            } else {
                if (SIMPLE_NODE.matcher(copy).matches()) {
                    Node node = makeSimpleNode(copy);
                    putInNode(stack, node);
                    copy = "";
                } else {
                    if (CLOSE_TAG.matcher(copy).matches()) {
                        Node node = stack.pop();
                        putInNode(stack, node);
                        copy = "";
                    }
                }
            }
        }

        if (SIMPLE_NODE.matcher(copy + nextChar).matches()) {
            return makeSimpleNode(copy + nextChar);
        }

        return stack.pop();
    }

    private Node makeComplexNode(String xmlPiece) {
        Matcher matcher = OPEN_TAG.matcher(xmlPiece);
        matcher.find();
        String name = matcher.group(NAME_GROUP);
        return NodeFactory.makeNode(name, makeAttributes(xmlPiece));
    }

    private Node makeSimpleNode(String xmlPiece) {
        Matcher matcher = SIMPLE_NODE.matcher(xmlPiece);
        matcher.find();
        String name = matcher.group(NAME_GROUP);
        String value = matcher.group(VALUE_GROUP);
        return NodeFactory.makeNode(name, value, makeAttributes(xmlPiece));
    }

    private Map<String, String> makeAttributes(String xmlPiece) {
        Map<String, String> attributes = new HashMap<>();

        Matcher matcher = ATTRIBUTE.matcher(xmlPiece);
        while (matcher.find()) {
            attributes.put(matcher.group(ATTR_NAME_GROUP), matcher.group(ATTR_VALUE_GROUP));
        }

        return attributes;
    }

    private void putInNode(Stack<Node> stack, Node node) {
        ComplexNode outerNode = (ComplexNode) stack.pop();
        outerNode.addInnerNode(node);
        stack.push(outerNode);
    }
}
