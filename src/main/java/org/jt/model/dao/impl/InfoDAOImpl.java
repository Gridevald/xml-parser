package org.jt.model.dao.impl;

import org.jt.model.dao.InfoDAO;
import org.jt.model.exception.DAOException;

import java.util.Scanner;

public class InfoDAOImpl implements InfoDAO {

    @Override
    public String getXml(String xmlName) throws DAOException {
        try (Scanner sc = new Scanner(InfoDAOImpl.class.getClassLoader().getResourceAsStream(xmlName))) {
            StringBuilder sb = new StringBuilder();

            while (sc.hasNextLine()) {
                sb.append(sc.nextLine().trim());
            }

            return sb.toString();
        } catch (NullPointerException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
