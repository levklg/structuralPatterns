package processor.homework;

import model.Message;
import processor.Processor;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class ProcessorExceptionEvenSecond implements Processor {
    private final Supplier<LocalDateTime> currentTimeSupplier;

    public ProcessorExceptionEvenSecond() {
        this(LocalDateTime::now);
    }

    public ProcessorExceptionEvenSecond(Supplier<LocalDateTime> currentTimeSupplier) {
        this.currentTimeSupplier = currentTimeSupplier;
    }

    @Override
    public Message process(Message message) {
        if (currentTimeSupplier.get().getSecond() % 2 == 0) {
            throw new RuntimeException("Exception on even second");
        }
        return message;
    }
}
