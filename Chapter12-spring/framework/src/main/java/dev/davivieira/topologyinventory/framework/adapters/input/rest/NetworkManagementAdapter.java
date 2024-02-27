package dev.davivieira.topologyinventory.framework.adapters.input.rest;

import dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase;
import dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.Router;
import dev.davivieira.topologyinventory.domain.entity.Switch;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Network;
import dev.davivieira.topologyinventory.framework.adapters.input.rest.request.network.AddNetwork;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/network")
@Tag(name = "Network Operations", description = "Network management operations")
public class NetworkManagementAdapter {

    @Autowired
    private SwitchManagementUseCase switchManagementUseCase;
    @Autowired
    private NetworkManagementUseCase networkManagementUseCase;

    /**
     * POST /network/add
     * */
    /*@PostMapping("/add/{switchId}")
    @Operation(operationId = "addNetworkToSwitch", description = "Add network to a switch")
    public Mono<ResponseEntity> addNetworkToSwitch(AddNetwork addNetwork, @PathVariable("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        Network network = Network.builder()
                .networkAddress(IP.fromAddress(addNetwork.getNetworkAddress()))
                .networkName(addNetwork.getNetworkName())
                .networkCidr(addNetwork.getNetworkCidr())
                .build();

        return Mono.justOrEmpty(networkManagementUseCase.addNetworkToSwitch(network, networkSwitch))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * POST /network/add
     * */
    @PostMapping("/add/{switchId}")
    @Operation(operationId = "addNetworkToSwitch", description = "Add network to a switch")
    public ResponseEntity<Switch> addNetworkToSwitch(AddNetwork addNetwork, @PathVariable("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        Network network = Network.builder()
                .networkAddress(IP.fromAddress(addNetwork.getNetworkAddress()))
                .networkName(addNetwork.getNetworkName())
                .networkCidr(addNetwork.getNetworkCidr())
                .build();

        return Optional.of(networkManagementUseCase.addNetworkToSwitch(network, networkSwitch))
                .map(aSwitch -> {
                    return new ResponseEntity<>(aSwitch, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_ADDED")).build());
    }

    /**
     * POST /network/remove
     * */
    /*@DeleteMapping("/{networkName}/from/{switchId}")
    @Operation(operationId = "removeNetworkFromSwitch", description = "Remove network from a switch")
    public Mono<ResponseEntity> removeNetworkFromSwitch(@PathVariable("networkName") String networkName, @PathVariable("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        return Mono.justOrEmpty(networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));

    }*/

    /**
     * POST /network/remove
     * */
    @DeleteMapping("/{networkName}/from/{switchId}")
    @Operation(operationId = "removeNetworkFromSwitch", description = "Remove network from a switch")
    public ResponseEntity<Switch> removeNetworkFromSwitch(@PathVariable("networkName") String networkName, @PathVariable("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        return Optional.of(networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch))
                .map(aSwitch -> {
                    return new ResponseEntity<>(aSwitch, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_REMOVED")).build());

    }

}
