package org.jt.model.service;

import org.jt.model.service.impl.InfoServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final InfoService INFO_SERVICE = new InfoServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public InfoService getInfoService() {
        return INFO_SERVICE;
    }
}
