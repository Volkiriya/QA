package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.calculate.Calculate;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Запуск приложения");
        int result = Calculate.sum(2, 3);
        log.info("Результат: {}", result);
    }
}