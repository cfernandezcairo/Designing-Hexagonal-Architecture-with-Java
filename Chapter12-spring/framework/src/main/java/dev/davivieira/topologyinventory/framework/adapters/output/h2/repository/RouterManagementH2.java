package dev.davivieira.topologyinventory.framework.adapters.output.h2.repository;

import dev.davivieira.topologyinventory.framework.adapters.output.h2.data.RouterData;
//import net.lecousin.reactive.data.relational.repository.LcR2dbcRepository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouterManagementH2 extends JpaRepository<RouterData, UUID> {
}
