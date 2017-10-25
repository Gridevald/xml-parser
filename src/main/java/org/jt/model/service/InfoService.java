package org.jt.model.service;

import org.jt.model.entity.Node;
import org.jt.model.service.exception.ServiceException;

public interface InfoService {

    Node getNode(String xmlName) throws ServiceException;
}
