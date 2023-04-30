import handler.ComplexProcessor;
import listener.ListenerPrinterConsole;
import listener.homework.HistoryListener;
import model.Message;
import model.ObjectForMessage;
import processor.LoggerProcessor;
import processor.ProcessorConcatFields;
import processor.ProcessorUpperField10;
import processor.homework.ProcessorExceptionEvenSecond;
import processor.homework.ProcessorSwap;

import java.util.List;
import java.util.Optional;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var processors = List.of(
                new ProcessorSwap(),
                new ProcessorExceptionEvenSecond(),
                new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10())
        );

        var complexProcessor = new ComplexProcessor(processors, ex -> {
            System.out.println("Exception caught: " + ex.getMessage());
        });

        var listenerPrinter = new ListenerPrinterConsole();
        var historyListener = new HistoryListener();
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(historyListener);

        var objectForMessage = new ObjectForMessage();
        objectForMessage.setData(List.of("data1", "data2"));

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(objectForMessage)
                .build();

        Message result;
        try {
            result = complexProcessor.handle(message);
            System.out.println("result:" + result);
        } catch (RuntimeException ex) {
            System.out.println("Message processing failed.");
        }

        complexProcessor.removeListener(listenerPrinter);

        // Проверяем сохранение истории
        Optional<Message> savedMessage = historyListener.findMessageById(1L);
        savedMessage.ifPresent(m -> System.out.println("Saved message: " + m));
    }


}
