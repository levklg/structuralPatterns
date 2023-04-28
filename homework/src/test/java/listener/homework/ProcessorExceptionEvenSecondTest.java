package listener.homework;

import model.Message;

import org.junit.jupiter.api.Test;
import processor.homework.ProcessorExceptionEvenSecond;
import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ProcessorExceptionEvenSecondTest {

    private ProcessorExceptionEvenSecond processor;

    @Test
    void processThrowsExceptionOnEvenSecond() {

        LocalDateTime evenSecondDateTime = LocalDateTime.of(2023, 4, 28, 12, 0, 2);
        processor = new ProcessorExceptionEvenSecond(() -> evenSecondDateTime);

        assertThatThrownBy(() -> processor.process(new Message.Builder(1).build()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Exception on even second");
    }

    @Test
    void processDoesNotThrowExceptionOnOddSecond() {

        LocalDateTime oddSecondDateTime = LocalDateTime.of(2023, 4, 28, 12, 0, 3);
        processor = new ProcessorExceptionEvenSecond(() -> oddSecondDateTime);

        assertThatThrownBy(() -> processor.process(new Message.Builder(1).build())).doesNotThrowAnyException();
    }

}
