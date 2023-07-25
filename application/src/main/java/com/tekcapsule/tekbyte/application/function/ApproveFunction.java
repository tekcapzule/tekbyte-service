package com.tekcapsule.tekbyte.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.tekbyte.application.config.AppConfig;
import com.tekcapsule.tekbyte.application.function.input.ApproveTekbyteInput;
import com.tekcapsule.tekbyte.application.mapper.InputOutputMapper;
import com.tekcapsule.tekbyte.domain.command.ApproveCommand;
import com.tekcapsule.tekbyte.domain.service.TekbyteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class ApproveFunction implements Function<Message<ApproveTekbyteInput>, Message<Void>> {

    private final TekbyteService tekbyteService;

    private final AppConfig appConfig;

    public ApproveFunction(final TekbyteService tekbyteService, final AppConfig appConfig) {
        this.tekbyteService = tekbyteService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveTekbyteInput> approveTekbyteInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveTekbyteInput approveTekbyteInput = approveTekbyteInputMessage.getPayload();
            log.info(String.format("Entering approve tekbyte Function -  tekbyte Id:%s", approveTekbyteInput.getTekByteId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveTekbyteInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveTekbyteInput.apply(approveTekbyteInput, origin);
            tekbyteService.approve(approveCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);

    }
}