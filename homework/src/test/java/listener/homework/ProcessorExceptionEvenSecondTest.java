package listener.homework;

import model.Message;

import org.junit.jupiter.api.Test;
import processor.homework.ProcessorExceptionEvenSecond;
import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ProcessorExceptionEvenSecondTest {


    private ProcessorExceptionEvenSecond processor;

    @Test
    void processThrowsExceptionOnEvenSecond() throws InterruptedException {

        processor = new ProcessorExceptionEvenSecond(LocalDateTime::now);

        if (LocalDateTime.now().getSecond() % 2 != 0) {
            Thread.sleep(1000);
        }

        assertThatThrownBy(() -> processor.process(new Message.Builder(1).build()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Exception on even second");
    }

    @Test
    void processDoesNotThrowExceptionOnOddSecond() throws InterruptedException {

        processor = new ProcessorExceptionEvenSecond(LocalDateTime::now);
        if (LocalDateTime.now().getSecond() % 2 == 0) {
            Thread.sleep(1000);
        }
        assertThatCode(() -> processor.process(new Message.Builder(1).build())).doesNotThrowAnyException();
    }
}