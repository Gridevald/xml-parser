package org.jt.model.dao;

import org.jt.model.dao.exception.DAOException;
import org.jt.model.entity.Node;

public interface InfoDAO {

    Node getXml(String xmlName) throws DAOException;
}
