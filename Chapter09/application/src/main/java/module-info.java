module application {
    //declaring module dependencies
    requires domain;
    requires static lombok;

    //enable access to use cases and input/output ports
    exports dev.davivieira.topologyinventory.application.ports.input;
    exports dev.davivieira.topologyinventory.application.ports.output;
    exports dev.davivieira.topologyinventory.application.usecases;

    //Declares services we want to provide from use cases interface through input ports implementation
    provides dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.RouterManagementInputPort;
    provides dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.SwitchManagementInputPort;
    provides dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase
            with dev.davivieira.topologyinventory.application.ports.input.NetworkManagementInputPort;
}