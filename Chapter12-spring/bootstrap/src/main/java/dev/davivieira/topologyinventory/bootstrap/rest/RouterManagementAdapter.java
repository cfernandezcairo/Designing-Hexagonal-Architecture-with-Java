package dev.davivieira.topologyinventory.bootstrap.rest;

import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.bootstrap.rest.request.router.AddRouter;
import dev.davivieira.topologyinventory.bootstrap.rest.request.router.CreateRouter;
import dev.davivieira.topologyinventory.domain.entity.CoreRouter;
import dev.davivieira.topologyinventory.domain.entity.Router;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/router")
@Tag(name = "Router Operations", description = "Router management operations")
public class RouterManagementAdapter {

    @Autowired
    private RouterManagementUseCase routerManagementUseCase;

    /**
     * GET /router/retrieve/{id}
     */
    @GetMapping("/retrieve/{id}")
    @Operation(operationId = "retrieveRouter", description = "Retrieve a router from the network inventory")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity> retrieveRouter(@PathVariable("id") Id id) {
        return Mono.justOrEmpty(routerManagementUseCase.retrieveRouter(id))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }


    /**
     * GET /router/remove/{id}
     * */
    @DeleteMapping("/{id}")
    @Operation(operationId = "removeRouter", description = "Remove a router from the network inventory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity> removeRouter(@PathVariable("id") Id id) {
        return Mono.justOrEmpty(routerManagementUseCase.removeRouter(id))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }

    /**
     * POST /router/create
     * */
    @PostMapping("/")
    @Operation(operationId = "createRouter", description = "Create and persist a new router on the network inventory")
    public Mono<ResponseEntity> createRouter(CreateRouter createRouter) {
        var router = routerManagementUseCase.createRouter(
                null,
                createRouter.getVendor(),
                createRouter.getModel(),
                IP.fromAddress(createRouter.getIp()),
                createRouter.getLocation(),
                createRouter.getRouterType()

        );

        return Mono.justOrEmpty(routerManagementUseCase.persistRouter(router))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }

    /**
     * POST /router/add
     * */
    @PostMapping("/add")
    @Operation(operationId = "addRouterToCoreRouter", description = "Add a router into a core router")
    public Mono<ResponseEntity> addRouterToCoreRouter(AddRouter addRouter) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getRouterId()));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getCoreRouterId()));

        return Mono.justOrEmpty(routerManagementUseCase.addRouterToCoreRouter(router, coreRouter))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }


    /**
     * POST /router/remove
     * */
    @DeleteMapping("/{routerId}/from/{coreRouterId}")
    @Operation(operationId = "removeRouterFromCoreRouter", description = "Remove a router from a core router")
    public Mono<ResponseEntity> removeRouterFromCoreRouter(@PathVariable("routerId") String routerId, @PathVariable("coreRouterId") String coreRouterId) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(coreRouterId));

        return Mono.justOrEmpty(routerManagementUseCase.removeRouterFromCoreRouter(router, coreRouter))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }

}
