package ru.hogwarts.school02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service

public class InfoService {
    Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private int port;

    public int getPort() {

        logger.debug("get port{}",port);
        return port;

    }

}
