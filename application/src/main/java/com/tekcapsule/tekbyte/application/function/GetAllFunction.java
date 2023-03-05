package com.tekcapsule.tekbyte.application.function;

import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.tekbyte.application.config.AppConfig;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;
import com.tekcapsule.tekbyte.domain.service.TekbyteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Tekbyte>>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public GetAllFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Tekbyte>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<Tekbyte> tekbytes = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all topics Function");
            tekbytes = tekbyteService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(tekbytes, responseHeaders);
    }
}