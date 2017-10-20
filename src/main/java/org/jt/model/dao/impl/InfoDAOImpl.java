package org.jt.model.dao.impl;

import org.jt.model.dao.InfoDAO;
import org.jt.model.exception.DAOException;

import java.io.InputStream;
import java.util.Scanner;

public class InfoDAOImpl implements InfoDAO {

    @Override
    public String getXml(String xmlName) throws DAOException {

        InputStream is = InfoDAOImpl.class.getClassLoader().getResourceAsStream(xmlName);
        if (is == null) {
            throw new DAOException("File not found");
        }

        Scanner sc = new Scanner(is);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            sb.append(sc.nextLine().trim());
        }

        return sb.toString();
    }
}
