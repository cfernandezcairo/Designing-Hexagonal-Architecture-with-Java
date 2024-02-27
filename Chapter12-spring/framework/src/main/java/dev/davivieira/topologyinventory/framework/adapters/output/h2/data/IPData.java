package dev.davivieira.topologyinventory.framework.adapters.output.h2.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class IPData {

    //@Column(name="router_ip_address")
    private String address;

    //@Embedded
    @Enumerated(EnumType.STRING)
    //@Column(name="router_ip_protocol")
    private ProtocolData protocol;

    private IPData(String routerIpAddress){
        if(routerIpAddress == null)
            throw new IllegalArgumentException("Null IP address");
        this.address = routerIpAddress;
        if(routerIpAddress.length()<=15) {
            this.protocol = ProtocolData.IPV4;
        } else {
            this.protocol = ProtocolData.IPV6;
        }
    }

    public IPData() {

    }

    public static IPData fromAddress(String address){
        return new IPData(address);
    }
}
