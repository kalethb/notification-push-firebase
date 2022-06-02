package com.kbm.notification.core.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessLowRequestService {


    public int adding(final int a , final int b) throws InterruptedException {
        log.info("Ingresando a un porcesamiento lento");
        Thread.sleep(5000);
        return a + b;
    }
}
