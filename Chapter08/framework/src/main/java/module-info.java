module framework {
    //declaring module dependencies
    requires domain;
    requires application;
    requires static lombok;

    requires org.eclipse.persistence.core;
    requires java.sql;
    requires jakarta.persistence;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    //enable access to all public types
    exports dev.davivieira.topologyinventory.framework.adapters.output.h2.data;
    opens dev.davivieira.topologyinventory.framework.adapters.output.h2.data;       //allow runtime reflective access to the output adapters
}