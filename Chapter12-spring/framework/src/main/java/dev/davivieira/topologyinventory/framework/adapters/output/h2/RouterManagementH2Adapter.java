package dev.davivieira.topologyinventory.framework.adapters.output.h2;

import dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort;
import dev.davivieira.topologyinventory.domain.entity.Router;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.data.RouterData;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.repository.RouterManagementH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouterManagementH2Adapter implements RouterManagementOutputPort {

    @Autowired
    private RouterManagementH2 routerRepository;

    @Override
    public Router retrieveRouter(Id id) {
        var routerData = routerRepository.findById(id.getUuid());
        return routerData.isEmpty() ? null : RouterH2Mapper.routerDataToDomain(routerData.get());
    }

    @Override
    public Router removeRouter(Id id) {
        var routerData = routerRepository.findById(id.getUuid());
        routerRepository.delete(routerData.get());
        return RouterH2Mapper.routerDataToDomain(routerData.get());
    }

    @Override
    public Router persistRouter(Router router) {
        var routerData = RouterH2Mapper.routerDomainToData(router);
        RouterData savedRouter = routerRepository.save(routerData);
        return RouterH2Mapper.routerDataToDomain(savedRouter);
    }

}