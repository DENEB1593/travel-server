package io.everyone.travel.batch.job.sample;

import org.springframework.batch.item.ItemProcessor;

public class SampleProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item.toUpperCase();
    }

}
