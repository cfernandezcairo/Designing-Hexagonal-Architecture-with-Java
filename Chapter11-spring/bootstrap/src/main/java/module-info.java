module dev.davivieira.bootstrap {
    requires domain;
    requires application;

    //requires framework;
    requires spring.web;
    requires spring.beans;
    requires spring.tx;

    requires jakarta.validation;
    requires jakarta.persistence;
    requires lombok;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    //requires org.slf4j;
}