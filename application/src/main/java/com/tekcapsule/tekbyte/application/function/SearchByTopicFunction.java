package com.tekcapsule.tekbyte.application.function;

import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.tekbyte.application.config.AppConfig;
import com.tekcapsule.tekbyte.application.function.input.SearchByTopicInput;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;
import com.tekcapsule.tekbyte.domain.service.TekbyteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class SearchByTopicFunction implements Function<Message<SearchByTopicInput>, Message<List<Tekbyte>>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public SearchByTopicFunction(final TekbyteService capsuleService, final AppConfig appConfig) {
        this.tekbyteService = capsuleService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Tekbyte>> apply(Message<SearchByTopicInput> findByTopicInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        List<Tekbyte> tekbytes =new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            SearchByTopicInput searchByTopicInput = findByTopicInputMessage.getPayload();
            log.info(String.format("Entering search by topic Function topics %s", searchByTopicInput.getTekbyteTopic()));
            tekbytes = tekbyteService.findByTopic(searchByTopicInput.getTekbyteTopic());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(tekbytes, responseHeaders);
    }
}