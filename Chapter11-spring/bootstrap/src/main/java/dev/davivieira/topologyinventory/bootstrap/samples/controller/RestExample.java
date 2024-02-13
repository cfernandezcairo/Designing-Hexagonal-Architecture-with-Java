package dev.davivieira.topologyinventory.bootstrap.samples.controller;

import dev.davivieira.topologyinventory.bootstrap.samples.dto.BeanExample;
import dev.davivieira.topologyinventory.bootstrap.samples.Service.PersistenceExample;
import dev.davivieira.topologyinventory.bootstrap.samples.dto.SampleObject;
import dev.davivieira.topologyinventory.bootstrap.samples.model.SampleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Path("/app")
@RestController
@RequestMapping("/app")
public class RestExample {

    @Autowired
    BeanExample beanExample;

    @Autowired
    PersistenceExample persistenceExample;

    /*
    @GET
    @Path("/simple-rest")
    @Produces(MediaType.TEXT_PLAIN)
    */
    @GetMapping(value = "/simple-rest", produces = MediaType.TEXT_PLAIN_VALUE)
    public String simpleRest() {
        return "This REST endpoint is provided by Quarkus";
    }

    /*
    @GET
    @Path("/simple-bean")
    @Produces(MediaType.TEXT_PLAIN)
    */
    @GetMapping(value = "/simple-bean", produces = MediaType.TEXT_PLAIN_VALUE)
    public String simpleBean() {
        return beanExample.simpleBean();
    }

    /*@POST
    @Path("/request-validation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)*/
    @PostMapping(value = "/request-validation", produces = "application/json", consumes = "application/json")
    public Result validation(@Valid @RequestBody SampleObject sampleObject) {
        try {
            return new Result("The request data is valid!");
        } catch (ConstraintViolationException e) {
            return new Result(e.getConstraintViolations());
        }
    }

    /*@POST
    @Path("/create-entity")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(APPLICATION_JSON)*/
    @PostMapping(value = "/create-entity", produces = "application/json", consumes = "application/json")
    public String persistData(@Valid @RequestBody SampleObject sampleObject) {
        return persistenceExample.createEntity(sampleObject);
    }

    /*@GET
    @Path("/get-all-entities")*/
    @GetMapping(value = "/get-all-entities")
    public List<SampleEntity> retrieveAllEntities() {
        return persistenceExample.getAllEntities();
    }

    public static class Result {

        private String message;
        private boolean success;

        Result(String message) {
            this.success = true;
            this.message = message;
        }

        Result(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                    .map(cv -> cv.getMessage())
                    .collect(Collectors.joining(", "));
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

    }
}