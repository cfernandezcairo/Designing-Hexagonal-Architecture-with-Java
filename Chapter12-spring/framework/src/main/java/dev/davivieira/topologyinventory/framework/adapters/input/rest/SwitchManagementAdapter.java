package dev.davivieira.topologyinventory.framework.adapters.input.rest;

import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.EdgeRouter;
import dev.davivieira.topologyinventory.domain.entity.Router;
import dev.davivieira.topologyinventory.domain.entity.Switch;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.RouterType;
import dev.davivieira.topologyinventory.framework.adapters.input.rest.request.aswitch.CreateSwitch;
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
@RequestMapping("/switch")
@Tag(name = "Switch Operations", description = "Operations for switch management")
public class SwitchManagementAdapter {

    @Autowired
    private SwitchManagementUseCase switchManagementUseCase;
    @Autowired
    private RouterManagementUseCase routerManagementUseCase;

    /**
     * GET /switch/retrieve/{id}
     * */
    /*@GetMapping("/{id}")
    @Operation(operationId = "retrieveSwitch", description = "Retrieve a switch from an edge router")
    public Mono<ResponseEntity> retrieveSwitch(@PathVariable("id") Id switchId) {
        return Mono.justOrEmpty(switchManagementUseCase.retrieveSwitch(switchId))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * GET /switch/retrieve/{id}
     * */
    @GetMapping("/{id}")
    @Operation(operationId = "retrieveSwitch", description = "Retrieve a switch from an edge router")
    public ResponseEntity<Switch> retrieveSwitch(@PathVariable("id") Id switchId) {

        Switch s = switchManagementUseCase.retrieveSwitch(switchId);
        Optional<Switch> result =  s == null ? Optional.empty() : Optional.of(s);

        return result
                .map(aswitch -> {
                    return new ResponseEntity<>(aswitch, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * POST /switch/create
     * */
    /*@PostMapping("/create/{edgeRouterId}")
    @Operation(operationId = "createAndAddSwitchToEdgeRouter", description = "Create switch and add to an edge router")
    public Mono<ResponseEntity> createAndAddSwitchToEdgeRouter(
            CreateSwitch createSwitch, @PathVariable("edgeRouterId") String edgeRouterId
    ) {
        Switch newSwitch = switchManagementUseCase.
                createSwitch(
                        createSwitch.getVendor(),
                        createSwitch.getModel(),
                        IP.fromAddress(createSwitch.getIp()),
                        createSwitch.getLocation(),
                        createSwitch.getSwitchType());
        Router edgeRouter = routerManagementUseCase.retrieveRouter(Id.withId(edgeRouterId));

        if(!edgeRouter.getRouterType().equals(RouterType.EDGE))
            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
        Router router = switchManagementUseCase.addSwitchToEdgeRouter(newSwitch, (EdgeRouter) edgeRouter);

        return Mono.justOrEmpty((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * POST /switch/create
     * */
    @PostMapping("/create/{edgeRouterId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createAndAddSwitchToEdgeRouter", description = "Create switch and add to an edge router")
    public ResponseEntity<Router> createAndAddSwitchToEdgeRouter(
            CreateSwitch createSwitch, @PathVariable("edgeRouterId") String edgeRouterId
    ) {
        Switch newSwitch = switchManagementUseCase.
                createSwitch(
                        createSwitch.getVendor(),
                        createSwitch.getModel(),
                        IP.fromAddress(createSwitch.getIp()),
                        createSwitch.getLocation(),
                        createSwitch.getSwitchType());
        Router edgeRouter = routerManagementUseCase.retrieveRouter(Id.withId(edgeRouterId));

        if(!edgeRouter.getRouterType().equals(RouterType.EDGE))
            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
        Router router = switchManagementUseCase.addSwitchToEdgeRouter(newSwitch, (EdgeRouter) edgeRouter);

        return Optional.of((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .map(r -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_CREATED")).build());
    }

    /**
     * POST /switch/remove
     * */
    /*@DeleteMapping("/{switchId}/from/{edgeRouterId}")
    @Operation(operationId = "removeSwitch", description = "Retrieve a router from the network inventory")
    public Mono<ResponseEntity> removeSwitchFromEdgeRouter(
            @PathVariable("switchId") String switchId, @PathVariable("edgeRouterId") String edgeRouterId) {
        EdgeRouter edgeRouter = (EdgeRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(edgeRouterId));
        Switch networkSwitch = edgeRouter.getSwitches().get(Id.withId(switchId));
        Router router = switchManagementUseCase
                .removeSwitchFromEdgeRouter(networkSwitch, edgeRouter);

        return Mono.justOrEmpty((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * POST /switch/remove
     * */
    @DeleteMapping("/{switchId}/from/{edgeRouterId}")
    @Operation(operationId = "removeSwitch", description = "Retrieve a router from the network inventory")
    public ResponseEntity<Router> removeSwitchFromEdgeRouter(
            @PathVariable("switchId") String switchId, @PathVariable("edgeRouterId") String edgeRouterId) {
        EdgeRouter edgeRouter = (EdgeRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(edgeRouterId));
        Switch networkSwitch = edgeRouter.getSwitches().get(Id.withId(switchId));
        Router router = switchManagementUseCase
                .removeSwitchFromEdgeRouter(networkSwitch, edgeRouter);

        return Optional.of((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .map(r -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_REMOVED")).build());
    }

}
