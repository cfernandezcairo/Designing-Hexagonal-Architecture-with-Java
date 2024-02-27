package dev.davivieira.topologyinventory.framework.adapters.output.h2.repository;

import dev.davivieira.topologyinventory.framework.adapters.output.h2.data.SwitchData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SwitchManagementH2 extends JpaRepository<SwitchData, UUID> {
}
