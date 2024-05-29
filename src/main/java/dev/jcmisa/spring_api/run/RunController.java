package dev.jcmisa.spring_api.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController //making this class as a rest controller that returns json
@RequestMapping("/api/runs")
@CrossOrigin(origins = "*")
public class RunController {
    //this is where the routing goes (just add annotation based on the http request associated with the method)
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/") //this method is a get method where if the user access the route "/", this will execute the home() method and return the "Home Page" text in the browser
    String home(){
        return "Home Page";
    }

    @GetMapping("")
    List<Run> findAll()
    {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);

        if(run.isEmpty()) {
            throw new RunNotFoundException();
        }

        return runRepository.findById(id).get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

}
