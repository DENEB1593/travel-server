package io.everyone.travel.batch.job.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class SampleWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        for (String item : chunk) {
            log.info("write item: {}", item);
        }
    }
}
