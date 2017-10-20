package org.jt.model.service.impl;

import org.jt.model.dao.DAOFactory;
import org.jt.model.entity.ComplexNode;
import org.jt.model.entity.Node;
import org.jt.model.entity.NodeFactory;
import org.jt.model.exception.DAOException;
import org.jt.model.exception.ParserException;
import org.jt.model.service.InfoService;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoServiceImpl implements InfoService {

    private static final Pattern OPEN_TAG = Pattern.compile("<[\\p{Alnum}-_.]+>");

    private static final Pattern SIMPLE_NODE = Pattern.compile("<[\\p{Alnum}-_.]+>[\\p{Graph}\\p{Blank}]+</[\\p{Alnum}-_.]+>");

    private static final Pattern CLOSE_TAG = Pattern.compile("</[\\p{Alnum}-_.]+>");

    private static final Pattern TAG_NAME = Pattern.compile("(?<=<)\\p{Graph}+(?=>)");

    private static final Pattern TAG_VALUE = Pattern.compile("(?<=>)[\\p{Graph}\\p{Blank}]+(?=<)");

    private static final Pattern ONLY_SIMPLE_NODE = Pattern.compile("<[\\p{Alnum}-_.]+>[\\p{Graph}\\p{Blank}]+</[\\p{Alnum}-_.]+");

    private static final String OPEN_BRACKET = "<";

    public Node getNode(String xmlName) throws ParserException {
        try {
            String xmlLine = DAOFactory.getInstance().getInfoDAO().getXml(xmlName);
            return parseXml(xmlLine);
        } catch (DAOException e) {
            throw new ParserException(e.getMessage(), e.getCause());
        }
    }

    private Node parseXml(String xmlLine) {

        String copy = "";
        String nextChar;
        Node node;
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < xmlLine.length() - 3; i++) {
            copy += xmlLine.charAt(i);
            nextChar = String.valueOf(xmlLine.charAt(i + 1));

            if (OPEN_TAG.matcher(copy).matches() && nextChar.equals(OPEN_BRACKET)) {
                node = makeNode(copy);
                stack.push(node);
                copy = "";
            } else {
                if (SIMPLE_NODE.matcher(copy).matches()) {
                    node = makeNode(copy);
                    if (!stack.empty()) {
                        ComplexNode outerNode = (ComplexNode) stack.pop();
                        outerNode.addInnerNode(node);
                        stack.push(outerNode);
                        copy = "";
                    } else {
                        return node;
                    }
                } else {
                    if (CLOSE_TAG.matcher(copy).matches()) {
                        node = stack.pop();
                        ComplexNode outerNode = (ComplexNode) stack.pop();
                        outerNode.addInnerNode(node);
                        stack.push(outerNode);
                        copy = "";
                    }
                }
            }
        }

        if (ONLY_SIMPLE_NODE.matcher(copy).matches()) {
            return makeNode(copy);
        }

        return stack.pop();

    }

    private Node makeNode(String xmlPiece) {
        if (OPEN_TAG.matcher(xmlPiece).matches()) {
            Matcher m = TAG_NAME.matcher(xmlPiece);
            m.find();
            String name = m.group();
            return NodeFactory.makeNode(name);
        } else {
            Matcher mName = TAG_NAME.matcher(xmlPiece);
            mName.find();
            String name = mName.group();
            Matcher mValue = TAG_VALUE.matcher(xmlPiece);
            mValue.find();
            String value = mValue.group();
            return NodeFactory.makeNode(name, value);
        }
    }
}
