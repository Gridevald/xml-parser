package org.jt.model.dao;

import org.jt.model.exception.DAOException;

public interface InfoDAO {

    public String getXml(String xmlName) throws DAOException;
}
