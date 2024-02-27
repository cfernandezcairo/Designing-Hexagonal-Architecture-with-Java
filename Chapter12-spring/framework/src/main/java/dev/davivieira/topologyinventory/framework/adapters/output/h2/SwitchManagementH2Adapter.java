package dev.davivieira.topologyinventory.framework.adapters.output.h2;

import dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import dev.davivieira.topologyinventory.domain.entity.Switch;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.repository.SwitchManagementH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwitchManagementH2Adapter implements SwitchManagementOutputPort {

    @Autowired
    private SwitchManagementH2 switchRepository;

    @Override
    public Switch retrieveSwitch(Id id) {
        var switchData = switchRepository.findById((id.getUuid()));
        return switchData.isEmpty() ? null : RouterH2Mapper.switchDataToDomain(switchData.get());
    }

}
