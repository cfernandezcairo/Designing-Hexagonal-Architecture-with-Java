import dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;

module framework {
    requires domain;
    requires application;
    requires static lombok;

    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.tx;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.web;
    requires spring.webflux;

    requires spring.data.jpa;
    requires spring.data.commons;
    /*requires spring.data.r2dbc;
    requires spring.r2dbc;
    requires r2dbc.spi;*/
    requires jakarta.persistence;
    requires jakarta.validation;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.annotation;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    requires reactor.core;
    requires org.reactivestreams;

    requires io.swagger.v3.oas.models;
    requires io.swagger.v3.oas.annotations;

    requires com.google.common;
    requires org.apache.commons.lang3;

    exports dev.davivieira.topologyinventory.framework.adapters.input.rest.request.router; //to module spring.beans
    exports dev.davivieira.topologyinventory.framework.adapters.input.rest;
    opens dev.davivieira.topologyinventory.framework.adapters.input.rest;

    opens dev.davivieira;                                       //open to spring.core
    opens dev.davivieira.topologyinventory.framework.config;
    exports dev.davivieira.topologyinventory.framework.adapters.output.h2.data;
    opens dev.davivieira.topologyinventory.framework.adapters.output.h2.data;
    exports dev.davivieira.topologyinventory.framework.adapters.output.h2;
    opens dev.davivieira.topologyinventory.framework.adapters.output.h2;    //open to spring.core

    provides dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort
            with dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
    provides dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort
            with dev.davivieira.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

    uses dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
    uses dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
    uses dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase;
    uses dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort;
    uses dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort;
}