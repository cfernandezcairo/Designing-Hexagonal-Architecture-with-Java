module domain {
    //declaring module dependencies
    requires static lombok;

    //enable access to public types in the domain
    exports dev.davivieira.topologyinventory.domain.entity;
    exports dev.davivieira.topologyinventory.domain.service;
    exports dev.davivieira.topologyinventory.domain.specification;
    exports dev.davivieira.topologyinventory.domain.vo;
    exports dev.davivieira.topologyinventory.domain.entity.factory;
}
