package dev.davivieira.topologyinventory.framework.adapters.output.h2.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "switch_data")
//@MappedSuperclass
public class SwitchData implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="switch_id",
            columnDefinition = "uuid",
            updatable = false )
    private UUID switchId;

    /*@GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")*/
    @Column(name="router_id")
    private UUID routerId;

    //@Embedded
    @Enumerated(EnumType.STRING)
    @Column(name="switch_vendor")
    private VendorData switchVendor;

    //@Embedded
    @Enumerated(EnumType.STRING)
    @Column(name="switch_model")
    private ModelData switchModel;

    //@Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "switch_type")
    private SwitchTypeData switchType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(/*table = "network_data",*/
            name = "switch_id",
            referencedColumnName = "switch_id")
    private List<NetworkData> networks;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "address",
                    column = @Column(
                            name = "switch_ip_address")),
            @AttributeOverride(
                    name = "protocol",
                    column = @Column(
                            name = "switch_ip_protocol")),
    })
    private IPData ip;

    @ManyToOne
    @JoinColumn(name="location_id")
    private LocationData switchLocation;
}
