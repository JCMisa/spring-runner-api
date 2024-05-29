package dev.jcmisa.spring_api.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    @PostConstruct //an initializer for adding records in a memory list
    private void init()
    {
        runs.add(new Run(1,
                "first run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(18, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));

        runs.add(new Run(2,
                "second run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.OUTDOOR));
    }



    // get all
    List<Run> findAll()
    {
        return runs;
    }

    // get
    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    // post
    void create(Run run) {
        runs.add(run);
    }

    //update
    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    //delete
    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }
}
