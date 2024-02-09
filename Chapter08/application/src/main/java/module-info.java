module application {
    //declaring module dependencies
    requires domain;
    requires static lombok;

    //enable access to use cases and input/output ports
    exports dev.davivieira.topologyinventory.application.usecases;
    exports dev.davivieira.topologyinventory.application.ports.output;
    exports dev.davivieira.topologyinventory.application.ports.input;
}
