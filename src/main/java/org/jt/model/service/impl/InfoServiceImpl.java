package org.jt.model.service.impl;

import org.jt.model.dao.DAOFactory;
import org.jt.model.dao.InfoDAO;
import org.jt.model.entity.Node;
import org.jt.model.dao.exception.DAOException;
import org.jt.model.service.exception.ServiceException;
import org.jt.model.service.InfoService;

public class InfoServiceImpl implements InfoService {

    public Node getNode(String xmlName) throws ServiceException {
        try {
            InfoDAO infoDAO = DAOFactory.getInstance().getInfoDAO();
            return infoDAO.getXml(xmlName);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
