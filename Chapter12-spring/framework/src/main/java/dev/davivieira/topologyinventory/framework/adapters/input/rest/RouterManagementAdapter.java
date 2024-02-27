package dev.davivieira.topologyinventory.framework.adapters.input.rest;


import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.CoreRouter;
import dev.davivieira.topologyinventory.domain.entity.Router;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.framework.adapters.input.rest.request.router.AddRouter;
import dev.davivieira.topologyinventory.framework.adapters.input.rest.request.router.CreateRouter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/router")
@Tag(name = "Router Operations", description = "Router management operations")
public class RouterManagementAdapter {

    @Autowired
    private RouterManagementUseCase routerManagementUseCase;

    /**
     * GET /router/retrieve/{id}
     */
    /*@GetMapping("/retrieve/{id}")
    @Operation(operationId = "retrieveRouter", description = "Retrieve a router from the network inventory")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity> retrieveRouter(@PathVariable("id") String id) {
        return Mono.justOrEmpty(routerManagementUseCase.retrieveRouter(Id.withId(id)))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * GET /router/retrieve/{id}
     */
    @GetMapping("/retrieve/{id}")
    @Operation(operationId = "retrieveRouter", description = "Retrieve a router from the network inventory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Router> retrieveRouter(@PathVariable("id") String id) {

        Router r = routerManagementUseCase.retrieveRouter(Id.withId(id));
        Optional<Router> result =  r == null ? Optional.empty() : Optional.of(r);

        return result
                .map(router -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    /**
     * GET /router/remove/{id}
     * */
    /*@DeleteMapping("/{id}")
    @Operation(operationId = "removeRouter", description = "Remove a router from the network inventory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity> removeRouter(@PathVariable("id") Id id) {
        return Mono.justOrEmpty(routerManagementUseCase.removeRouter(id))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * GET /router/remove/{id}
     * */
    @DeleteMapping("/remove/{id}")
    @Operation(operationId = "removeRouter", description = "Remove a router from the network inventory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Router> removeRouter(@PathVariable("id") String id) {
        return Optional.of(routerManagementUseCase.removeRouter(Id.withId(id)))
                .map(router -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /router/create
     * */
    /*@PostMapping("/")
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
    }*/

    /**
     * POST /router/create
     * */
    @PostMapping("/create/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createRouter", description = "Create and persist a new router on the network inventory")
    public ResponseEntity<Router> createRouter(@RequestBody CreateRouter createRouter) {
        var router = routerManagementUseCase.createRouter(
                null,
                createRouter.getVendor(),
                createRouter.getModel(),
                IP.fromAddress(createRouter.getIp()),
                createRouter.getLocation(),
                createRouter.getRouterType()
        );

        return Optional.of(routerManagementUseCase.persistRouter(router))
                .map(r -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_CREATED")).build());

    }

    /**
     * POST /router/add
     * */
    /*@PostMapping("/add")
    @Operation(operationId = "addRouterToCoreRouter", description = "Add a router into a core router")
    public Mono<ResponseEntity> addRouterToCoreRouter(AddRouter addRouter) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getRouterId()));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getCoreRouterId()));

        return Mono.justOrEmpty(routerManagementUseCase.addRouterToCoreRouter(router, coreRouter))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * POST /router/add
     * */
    @PostMapping("/add")
    @Operation(operationId = "addRouterToCoreRouter", description = "Add a router into a core router")
    public ResponseEntity<Router> addRouterToCoreRouter(@RequestBody AddRouter addRouter) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getRouterId()));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(addRouter.getCoreRouterId()));

        return Optional.of(routerManagementUseCase.addRouterToCoreRouter(router, coreRouter))
                .map(r -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_ADDED")).build());

    }


    /**
     * POST /router/remove
     * */
    /*@DeleteMapping("/{routerId}/from/{coreRouterId}")
    @Operation(operationId = "removeRouterFromCoreRouter", description = "Remove a router from a core router")
    public Mono<ResponseEntity> removeRouterFromCoreRouter(@PathVariable("routerId") String routerId, @PathVariable("coreRouterId") String coreRouterId) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(coreRouterId));

        return Mono.justOrEmpty(routerManagementUseCase.removeRouterFromCoreRouter(router, coreRouter))
                .transform(f -> f != null ? (Publisher<ResponseEntity>) ResponseEntity.ok(f) : (Publisher<ResponseEntity>) ResponseEntity.ok(null));
    }*/

    /**
     * POST /router/remove
     * */
    @DeleteMapping("/{routerId}/from/{coreRouterId}")
    @Operation(operationId = "removeRouterFromCoreRouter", description = "Remove a router from a core router")
    public ResponseEntity<Router> removeRouterFromCoreRouter(@PathVariable("routerId") String routerId, @PathVariable("coreRouterId") String coreRouterId) {
        Router router = routerManagementUseCase
                .retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(coreRouterId));

        return Optional.of(routerManagementUseCase.removeRouterFromCoreRouter(router, coreRouter))
                .map(r -> {
                    return new ResponseEntity<>(router,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.valueOf("NOT_REMOVED")).build());
    }

}
