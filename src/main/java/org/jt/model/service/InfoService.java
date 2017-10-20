package org.jt.model.service;

import org.jt.model.entity.Node;
import org.jt.model.exception.ParserException;

public interface InfoService {

    Node getNode(String xmlName) throws ParserException;
}
