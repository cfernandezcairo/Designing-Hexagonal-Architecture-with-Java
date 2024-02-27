package dev.davivieira.topologyinventory.framework.adapters.input.rest.request.router;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.RouterType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CreateRouter implements Serializable {

    @JsonProperty
    private Vendor vendor;

    @JsonProperty
    private Model model;

    @JsonProperty
    private String ip;

    @JsonProperty
    private Location location;

    //@JsonProperty
    private RouterType routerType;
}
