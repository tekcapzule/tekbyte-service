package com.tekcapsule.tekbyte.domain.service;

import com.tekcapsule.tekbyte.domain.command.CreateCommand;
import com.tekcapsule.tekbyte.domain.command.DisableCommand;
import com.tekcapsule.tekbyte.domain.command.UpdateCommand;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;
import com.tekcapsule.tekbyte.domain.repository.TekbyteDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TekbyteServiceImpl implements TekbyteService {
    private TekbyteDynamoRepository tekbyteDynamoRepository;

    @Autowired
    public TekbyteServiceImpl(TekbyteDynamoRepository tekbyteDynamoRepository) {
        this.tekbyteDynamoRepository = tekbyteDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create topic service - Topic Code :%s", createCommand.getCode()));

        Tekbyte tekbyte = Tekbyte.builder()
                .code(createCommand.getCode())
                .name(createCommand.getName())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .imageUrl(createCommand.getImageUrl())
                .category(createCommand.getCategory())
                .status("ACTIVE")
                .aliases(createCommand.getAliases())
                .recommendations(createCommand.getRecommendations())
                .promoted(createCommand.isPromoted())
                .featured(createCommand.isFeatured())
                .prizingModel(createCommand.getPrizingModel())
                .build();

        tekbyte.setAddedOn(createCommand.getExecOn());
        tekbyte.setUpdatedOn(createCommand.getExecOn());
        tekbyte.setAddedBy(createCommand.getExecBy().getUserId());

        tekbyteDynamoRepository.save(tekbyte);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update topic service - Topic Code:%s", updateCommand.getCode()));

        Tekbyte tekbyte = tekbyteDynamoRepository.findBy(updateCommand.getCode());
        if (tekbyte != null) {
            tekbyte.setName(updateCommand.getName());
            tekbyte.setStatus("ACTIVE");
            tekbyte.setSummary(updateCommand.getSummary());
            tekbyte.setCategory(updateCommand.getCategory());
            tekbyte.setAliases(updateCommand.getAliases());
            tekbyte.setImageUrl(updateCommand.getImageUrl());
            tekbyte.setDescription(updateCommand.getDescription());
            tekbyte.setUpdatedOn(updateCommand.getExecOn());
            tekbyte.setUpdatedBy(updateCommand.getExecBy().getUserId());
            tekbyte.setRecommendations(updateCommand.getRecommendations());
            tekbyte.setPromoted(updateCommand.isPromoted());
            tekbyte.setFeatured(updateCommand.isFeatured());
            tekbyte.setPrizingModel(updateCommand.getPrizingModel());
            tekbyteDynamoRepository.save(tekbyte);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable topic service - Topic Code:%s", disableCommand.getCode()));

        tekbyteDynamoRepository.findBy(disableCommand.getCode());
        Tekbyte tekbyte = tekbyteDynamoRepository.findBy(disableCommand.getCode());
        if (tekbyte != null) {
            tekbyte.setStatus("INACTIVE");
            tekbyte.setUpdatedOn(disableCommand.getExecOn());
            tekbyte.setUpdatedBy(disableCommand.getExecBy().getUserId());
            tekbyteDynamoRepository.save(tekbyte);
        }
    }

    @Override
    public List<Tekbyte> findAll() {

        log.info("Entering findAll Topic service");

        return tekbyteDynamoRepository.findAll();
    }

    @Override
    public Tekbyte findBy(String code) {

        log.info(String.format("Entering findBy Topic service - Topic code:%s", code));

        return tekbyteDynamoRepository.findBy(code);
    }


}
