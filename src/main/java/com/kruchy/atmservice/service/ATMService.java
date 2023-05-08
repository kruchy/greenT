package com.kruchy.atmservice.service;

import com.kruchy.atmservice.model.ATM;
import com.kruchy.atmservice.model.Task;
import org.springframework.stereotype.Service;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ATMService {
    /**
     * This method calculates the order of ATMs based on the given list of tasks.
     * It first sorts the tasks by region and then by request type priority.
     * Next, it maps each task to a unique key-value pair, where the key is a combination of region and atmId,
     * and the value is an ATM object.
     * The method then collects unique ATM objects in the order they appear, using a LinkedHashMap.
     * Finally, it returns a list of unique ATMs in the calculated order.
     */
    public List<ATM> calculateOrder(List<Task> tasks) {

        return new ArrayList<>(tasks.stream()
                .sorted(Comparator.comparing(Task::region)
                        .thenComparing((task) -> task.requestType().getPriority()))
                .map(task -> new SimpleEntry<>(task.region() + "-" + task.atmId(), new ATM(task.region(), task.atmId())))
                .collect(Collectors.toMap(
                        SimpleEntry::getKey,
                        SimpleEntry::getValue,
                        (atm1, atm2) -> atm1,
                        LinkedHashMap::new))
                .values());
    }


}
