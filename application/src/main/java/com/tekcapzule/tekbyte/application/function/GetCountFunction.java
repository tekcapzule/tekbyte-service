package com.tekcapzule.tekbyte.application.function;

import com.tekcapzule.tekbyte.application.config.AppConfig;
import com.tekcapzule.tekbyte.domain.service.TekbyteService;
import com.tekcapzule.core.domain.EmptyFunctionInput;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetCountFunction implements Function<Message<EmptyFunctionInput>, Message<Integer>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public GetCountFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Integer> apply(Message<EmptyFunctionInput> getAllCountMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        int tekbytesCount = 0;
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all tekbytes count Function");
            tekbytesCount = tekbyteService.getAllTekbytesCount();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(tekbytesCount, responseHeaders);
    }
}