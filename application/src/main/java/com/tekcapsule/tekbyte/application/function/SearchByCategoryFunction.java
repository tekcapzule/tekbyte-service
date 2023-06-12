package com.tekcapsule.tekbyte.application.function;

import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.tekbyte.application.function.input.SearchByCategoryInput;
import com.tekcapsule.tekbyte.application.function.input.SearchByTopicInput;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;
import com.tekcapsule.tekbyte.domain.service.TekbyteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import com.tekcapsule.tekbyte.application.config.AppConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class SearchByCategoryFunction implements Function<Message<SearchByCategoryInput>, Message<List<Tekbyte>>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public SearchByCategoryFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Tekbyte>> apply(Message<SearchByCategoryInput> findByCategoryInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        List<Tekbyte> tekbytes =new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            SearchByCategoryInput searchByCategoryInput = findByCategoryInputMessage.getPayload();
            log.info(String.format("Entering search by topic Function topics %s", searchByCategoryInput.getCategory()));
            tekbytes = tekbyteService.findByCategory(searchByCategoryInput.getCategory(), searchByCategoryInput.getTopicCode());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(tekbytes, responseHeaders);
    }
}