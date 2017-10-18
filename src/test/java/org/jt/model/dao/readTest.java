package org.jt.model.dao;

import org.jt.model.exception.DAOException;
import org.junit.Test;

public class readTest {

    @Test
    public void readTestOne() throws DAOException {
        InfoDAO infoDAO = DAOFactory.getInstance().getInfo();
        System.out.println(infoDAO.getXml("text.xml"));
    }

    @Test(expected = DAOException.class)
    public void readFailTest() throws DAOException {
        InfoDAO infoDAO = DAOFactory.getInstance().getInfo();
        infoDAO.getXml("wrong.xml");
    }
}
