package com.kruchy.atmservice.service;

import com.kruchy.atmservice.model.ATM;
import com.kruchy.atmservice.model.RequestType;
import com.kruchy.atmservice.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ATMServiceTest {

    @Autowired
    private ATMService atmService;

    @Test
    public void testCalculateOrder() {
        List<Task> inputTasks = Arrays.asList(
                new Task(4, RequestType.STANDARD, 1),
                new Task(1, RequestType.STANDARD, 1),
                new Task(2, RequestType.STANDARD, 1),
                new Task(3, RequestType.PRIORITY, 2),
                new Task(3, RequestType.STANDARD, 1),
                new Task(2, RequestType.SIGNAL_LOW, 1),
                new Task(5, RequestType.STANDARD, 2),
                new Task(5, RequestType.FAILURE_RESTART, 1)
        );

        List<ATM> expectedOrder = Arrays.asList(
                new ATM(1, 1),
                new ATM(2, 1),
                new ATM(3, 2),
                new ATM(3, 1),
                new ATM(4, 1),
                new ATM(5, 1),
                new ATM(5, 2)
        );

        List<ATM> calculatedOrder = atmService.calculateOrder(inputTasks);
        assertEquals(expectedOrder, calculatedOrder, "Calculated order should match the expected order");
    }

    @Test
    void testCalculateOrderCase2() {
        List<Task> tasks = List.of(
                new Task(1, RequestType.STANDARD, 2),
                new Task(1, RequestType.STANDARD, 1),
                new Task(2, RequestType.PRIORITY, 3),
                new Task(3, RequestType.STANDARD, 4),
                new Task(4, RequestType.STANDARD, 5),
                new Task(5, RequestType.PRIORITY, 2),
                new Task(5, RequestType.STANDARD, 1),
                new Task(3, RequestType.SIGNAL_LOW, 2),
                new Task(2, RequestType.SIGNAL_LOW, 1),
                new Task(3, RequestType.FAILURE_RESTART, 1)
        );

        List<ATM> expectedOrder = List.of(
                new ATM(1, 2),
                new ATM(1, 1),
                new ATM(2, 3),
                new ATM(2, 1),
                new ATM(3, 1),
                new ATM(3, 2),
                new ATM(3, 4),
                new ATM(4, 5),
                new ATM(5, 2),
                new ATM(5, 1)
        );

        ATMService atmService = new ATMService();
        List<ATM> order = atmService.calculateOrder(tasks);

        assertEquals(expectedOrder, order);
    }
}
