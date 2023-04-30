package listener.homework;

import model.Message;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processor.homework.ProcessorExceptionEvenSecond;
import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.when;

public class ProcessorExceptionEvenSecondTest {

    private ProcessorExceptionEvenSecond processor;

    @Mock
    private Supplier<LocalDateTime> currentTimeSupplier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processThrowsExceptionOnEvenSecond() {

        when(currentTimeSupplier.get()).thenReturn(LocalDateTime.of(2022, 1, 1, 1, 1, 2));

        processor = new ProcessorExceptionEvenSecond(currentTimeSupplier);

        assertThatThrownBy(() -> processor.process(new Message.Builder(1).build()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Exception on even second");
    }

    @Test
    void processDoesNotThrowExceptionOnOddSecond() {

        when(currentTimeSupplier.get()).thenReturn(LocalDateTime.of(2022, 1, 1, 1, 1, 3));

        processor = new ProcessorExceptionEvenSecond(currentTimeSupplier);

        assertThatCode(() -> processor.process(new Message.Builder(1).build())).doesNotThrowAnyException();
    }
}