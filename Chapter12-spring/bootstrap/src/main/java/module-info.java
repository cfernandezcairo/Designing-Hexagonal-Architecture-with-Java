module dev.davivieira.bootstrap {
    requires domain;
    requires framework;
    requires application;
    requires static lombok;

    //requires framework;
    requires spring.web;
    requires spring.beans;
    requires spring.tx;

    requires jakarta.validation;
    requires jakarta.persistence;
    requires io.swagger.v3.oas.models;
    requires io.swagger.v3.oas.annotations;
    requires com.fasterxml.jackson.annotation;
    requires org.reactivestreams;
    requires reactor.core;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;

}