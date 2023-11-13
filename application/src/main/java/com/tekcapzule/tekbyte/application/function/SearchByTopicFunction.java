package com.tekcapzule.tekbyte.application.function;

import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.tekbyte.application.config.AppConfig;
import com.tekcapzule.tekbyte.application.function.input.SearchByTopicInput;
import com.tekcapzule.tekbyte.domain.model.Tekbyte;
import com.tekcapzule.tekbyte.domain.service.TekbyteService;
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

    public SearchByTopicFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Tekbyte>> apply(Message<SearchByTopicInput> findByTopicInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        List<Tekbyte> tekbytes =new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            SearchByTopicInput searchByTopicInput = findByTopicInputMessage.getPayload();
            log.info(String.format("Entering search by topic Function topics %s", searchByTopicInput.getSubscribedTopic()));
            tekbytes = tekbyteService.findByTopic(searchByTopicInput.getSubscribedTopic());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(tekbytes, responseHeaders);
    }
}