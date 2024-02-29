package io.everyone.travel.batch.job.sample;

import org.springframework.batch.item.*;

import java.util.Iterator;
import java.util.List;

public class SampleReader implements ItemStreamReader<String> {

    private final List<String> items = List.of("this", "is", "sample");
    private Iterator<String> iterator;

    @Override
    public String read()  {
        if ((iterator.hasNext())) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        iterator = items.iterator();
    }
}
