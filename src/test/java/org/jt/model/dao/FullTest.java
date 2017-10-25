package org.jt.model.dao;

import org.jt.model.entity.Node;
import org.jt.model.service.ServiceFactory;
import org.jt.model.service.exception.ServiceException;
import org.jt.view.NodePrinter;
import org.junit.Test;

public class FullTest {

    @Test
    public void test01() {
        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("emptyTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("simpleTag.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        try {
            Node node = ServiceFactory.getInstance().getInfoService().getNode("test01.xml");
            NodePrinter.consolePrint(node);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
