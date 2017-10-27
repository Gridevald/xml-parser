package org.jt.model.dao;

import org.jt.model.dao.impl.InfoDAOImpl;

public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

    private final InfoDAO INFO_DAO = new InfoDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public InfoDAO getInfoDAO() {
        return INFO_DAO;
    }
}
