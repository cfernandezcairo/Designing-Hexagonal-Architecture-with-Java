module application {
    requires domain;
    requires static lombok;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;


    exports dev.davivieira.topologyinventory.application.ports.input;
    exports dev.davivieira.topologyinventory.application.ports.output;
    exports dev.davivieira.topologyinventory.application.usecases;
    opens dev.davivieira.topologyinventory.application.ports.input; //open to spring.core

    provides dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.RouterManagementInputPort;
    /*provides dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.SwitchManagementInputPort;
    provides dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.NetworkManagementInputPort;*/
}