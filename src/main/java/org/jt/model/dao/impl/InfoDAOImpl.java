package org.jt.model.dao.impl;

import org.jt.model.dao.InfoDAO;
import org.jt.model.dao.exception.DAOException;
import org.jt.model.dao.parser.Parser;
import org.jt.model.entity.Node;

import java.io.InputStream;
import java.util.Scanner;

public class InfoDAOImpl implements InfoDAO {

    private static final String BLANKS_BETWEEN_TAGS = "(?<=>)\\p{Blank}+(?=<)";

    private static final String XML_VERSION = "<\\?[\\p{Graph}\\p{Blank}]+\\?>";

    @Override
    public Node getXml(String xmlName) throws DAOException {

        InputStream is = InfoDAOImpl.class.getClassLoader().getResourceAsStream(xmlName);
        if (is == null) {
            throw new DAOException("File not found");
        }

        try (Scanner sc = new Scanner(is)) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine().trim());
            }
            return new Parser().parseXml(prepareXml(sb.toString()));
        }
    }

    private String prepareXml(String xml) {
        String out = xml.replaceAll(BLANKS_BETWEEN_TAGS, "");
        out = out.replaceAll(XML_VERSION, "");
        return out;
    }
}
