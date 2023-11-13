package com.tekcapzule.tekbyte.application.function;

import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.tekbyte.application.config.AppConfig;
import com.tekcapzule.tekbyte.application.function.input.GetInput;
import com.tekcapzule.tekbyte.domain.model.Tekbyte;
import com.tekcapzule.tekbyte.domain.service.TekbyteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetFunction implements Function<Message<GetInput>, Message<Tekbyte>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public GetFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Tekbyte> apply(Message<GetInput> getInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Tekbyte tekbyte = new Tekbyte();

        String stage = appConfig.getStage().toUpperCase();

        try {
            GetInput getInput = getInputMessage.getPayload();
            log.info(String.format("Entering get tekbyte Function -Tekbyte ID:%s", getInput.getTekByteId()));
            tekbyte = tekbyteService.findBy(getInput.getTekByteId());
            if (tekbyte == null) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
                tekbyte = Tekbyte.builder().build();
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(tekbyte, responseHeaders);
    }
}