package com.mensaappserver.Scrapper;

import com.mensaappserver.Service.Implementation.MensaService;
import org.springframework.stereotype.Component;

@Component
public class ScrapperScript {
 private final MensaService mensaService;

    public ScrapperScript(MensaService mensaService) {
        this.mensaService = mensaService;
    }
}
