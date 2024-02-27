package dev.davivieira.topologyinventory.framework.adapters.input.rest.request.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AddRouter {

    @JsonProperty
    String routerId;

    @JsonProperty
    String coreRouterId;

}
