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

    //Declares services we want to provide from output port interface through output adaptar implementation
    provides dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort
            with dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
    provides dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort
            with dev.davivieira.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

    //Declares services we want to consume (all provided services in the system) (application or framework hexagon)
    uses dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
    uses dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
    uses dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase;
    uses dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort;
    uses dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort;
}