package com.example.happytownclone.entrypoints.job;

import com.example.happytownclone.core.use_cases.AttribuerCadeaux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AttributionCadeauxJob {

    private final AttribuerCadeaux attribuerCadeaux;

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributionCadeauxJob.class);

    public AttributionCadeauxJob(AttribuerCadeaux attribuerCadeaux) {
        this.attribuerCadeaux = attribuerCadeaux;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void attributionCadeaux() {
        LOGGER.info("Start Task execute");
        attribuerCadeaux.execute();
        LOGGER.info("End Task execute");
    }
}
