package processor.homework;

import model.Message;
import processor.Processor;

import java.time.LocalDateTime;

public class ProcessorExceptionEvenSecond implements Processor {
    @Override
    public Message process(Message message) {
        if (LocalDateTime.now().getSecond() % 2 == 0) {
            throw new RuntimeException("Exception on even second");
        }
        return message;
    }
}
