package org.jt.model.dao;

import org.jt.model.dao.exception.DAOException;
import org.junit.Test;

public class ReadTest {

    @Test
    public void readTestOne() throws DAOException {
        InfoDAO infoDAO = DAOFactory.getInstance().getInfoDAO();
        System.out.println(infoDAO.getXml("task02.xml"));
    }

    @Test(expected = DAOException.class)
    public void readFailTest() throws DAOException {
        InfoDAO infoDAO = DAOFactory.getInstance().getInfoDAO();
        infoDAO.getXml("wrong.xml");
    }
}
