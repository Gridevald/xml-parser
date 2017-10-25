package org.jt.controller;

import org.jt.model.entity.Node;
import org.jt.model.service.exception.ServiceException;
import org.jt.model.service.ServiceFactory;
import org.jt.view.NodePrinter;

public class Main {

    public static void main(String[] args) {

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("task02.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("emptyTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("simpleTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("test01.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
