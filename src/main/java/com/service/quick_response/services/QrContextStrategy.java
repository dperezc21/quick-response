package com.service.quick_response.services;

import com.service.quick_response.repositories.QrGenerateRepository;
import lombok.Getter;

@Getter
public class QrContextStrategy {

    private final QrGenerateRepository strategy;

    public QrContextStrategy(QrGenerateRepository quickResponseStrategy) {
        this.strategy = quickResponseStrategy;
    }

}
