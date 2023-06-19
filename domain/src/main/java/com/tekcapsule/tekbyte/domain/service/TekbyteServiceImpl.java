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
import java.util.UUID;

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

        log.info(String.format("Entering create tekbyte service - Tekbyte Name :%s", createCommand.getTitle()));
        String id = UUID.randomUUID().toString();

        Tekbyte tekbyte = Tekbyte.builder()
                .code(getCode(createCommand.getTopicCode(),createCommand.getCategory(),id))
                .title(createCommand.getTitle())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .imageUrl(createCommand.getImageUrl())
                .topicCode(createCommand.getTopicCode())
                .category(createCommand.getCategory())
                .status("ACTIVE")
                .aliases(createCommand.getAliases())
                .goldenCircle(createCommand.getGoldenCircle())
                .keyConcepts(createCommand.getKeyConcepts())
                .timeline(createCommand.getTimeline())
                .wayForward(createCommand.getWayForward())
                .didYouKnow(createCommand.getDidYouKnow())
                .applications(createCommand.getApplications())
                .illustrationUrl(createCommand.getIllustrationUrl())
                .recommendations(createCommand.getRecommendations())
                .prizingModel(createCommand.getPrizingModel())
                .featured(createCommand.isFeatured())
                .promoted(createCommand.isPromoted())
                .build();

        tekbyte.setAddedOn(createCommand.getExecOn());
        tekbyte.setUpdatedOn(createCommand.getExecOn());
        tekbyte.setAddedBy(createCommand.getExecBy().getUserId());

        tekbyteDynamoRepository.save(tekbyte);
    }

    private String getCode(String topicCode, String category, String id) {
        return topicCode + "-" + category + "-" + id;
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update tekbyte service - Tekbyte id:%s", updateCommand.getCode()));

        Tekbyte tekbyte = tekbyteDynamoRepository.findBy(updateCommand.getCode());
        if (tekbyte != null) {
            tekbyte.setTitle(updateCommand.getTitle());
            tekbyte.setStatus("ACTIVE");
            tekbyte.setSummary(updateCommand.getSummary());
            tekbyte.setAliases(updateCommand.getAliases());
            tekbyte.setImageUrl(updateCommand.getImageUrl());
            tekbyte.setDescription(updateCommand.getDescription());
            tekbyte.setUpdatedOn(updateCommand.getExecOn());
            tekbyte.setUpdatedBy(updateCommand.getExecBy().getUserId());
            tekbyte.setKeyConcepts(updateCommand.getKeyConcepts());
            tekbyte.setGoldenCircle(updateCommand.getGoldenCircle());
            tekbyte.setTimeline(updateCommand.getEvents());
            tekbyte.setWayForward(updateCommand.getWayForward());
            tekbyte.setDidYouKnow(updateCommand.getDidYouKnow());
            tekbyte.setApplications(updateCommand.getApplications());
            tekbyte.setIllustrationUrl(updateCommand.getIllustrationUrl());
            tekbyte.setRecommendations(updateCommand.getRecommendations());
            tekbyte.setPromoted(updateCommand.isPromoted());
            tekbyte.setFeatured(updateCommand.isFeatured());
            tekbyte.setPrizingModel(updateCommand.getPrizingModel());

            tekbyteDynamoRepository.save(tekbyte);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable tekbyte service - Tekbyte Code:%s", disableCommand.getCode()));

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

        log.info("Entering findAll tekbyte service");

        return tekbyteDynamoRepository.findAll();
    }

    @Override
    public Tekbyte findBy(String id) {

        log.info(String.format("Entering findBy tekbyte service - Tekbyte id:%s", id));

        return tekbyteDynamoRepository.findBy(id);
    }

    @Override
    public int getAllTekbytesCount() {
        log.info("Entering get all Tekbytes count service");
        return tekbyteDynamoRepository.getAllTekbytesCount();
    }


}
