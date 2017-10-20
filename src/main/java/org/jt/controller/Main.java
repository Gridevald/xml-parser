package org.jt.controller;

import org.jt.model.entity.Node;
import org.jt.model.exception.ParserException;
import org.jt.model.service.ServiceFactory;
import org.jt.view.NodePrinter;

public class Main {

    public static void main(String[] args) {

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("test.xml");
            NodePrinter.consolePrint(node);
        } catch (ParserException e) {
            System.err.println(e.getMessage());
        }

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("emptyTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ParserException e) {
            System.err.println(e.getMessage());
        }

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("simpleTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ParserException e) {
            System.err.println(e.getMessage());
        }
    }
}
