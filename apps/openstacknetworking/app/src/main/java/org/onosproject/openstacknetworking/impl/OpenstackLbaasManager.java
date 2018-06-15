package org.onosproject.openstacknetworking.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.onlab.util.KryoNamespace;
import org.onosproject.core.ApplicationId;
import org.onosproject.openstacknetworking.api.OpenstackLbaasAdminService;
import org.onosproject.store.serializers.KryoNamespaces;
import org.onosproject.store.service.Serializer;
import org.openstack4j.model.network.Network;
import org.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronMemberV2;

import static org.onosproject.openstacknetworking.api.Constants.OPENSTACK_NETWORKING_APP_ID;

@Service
@Component(immediate = true)
public class OpenstackLbaasManager implements OpenstackLbaasAdminService {


    private static final KryoNamespace SERIALIZER_NEUTRON_LBAAS = KryoNamespace.newBuilder()
            .register(KryoNamespaces.API)
            .register(

    @Activate
    protected void activate() {
        ApplicationId appId = coreService.registerApplication(OPENSTACK_NETWORKING_APP_ID);

        osLbaasStore = storageService.<String, Network>consistentMapBuilder()
                .withSerializer(Serializer.using(SERIALIZER_NEUTRON_LBAAS))
                .withName("openstack-networkstore")
                .withApplicationId(appId)
                .build();
    }

    @Override
    public void createLbaasLoadBalancer(NeutronLoadBalancerV2 loadBalancer) {

    }

    @Override
    public void updateLbaasLoadBalancer(NeutronLoadBalancerV2 loadBalancer) {

    }

    @Override
    public void deleteLbaasLoadBalancer(String loadbalancerId) {

    }

    @Override
    public void createLbaasListener(NeutronListenerV2 listener) {

    }

    @Override
    public void updateLbaasListener(NeutronListenerV2 listener) {

    }

    @Override
    public void deleteLbaasListener(String listenerId) {

    }

    @Override
    public void createLbaasMember(NeutronMemberV2 member) {

    }

    @Override
    public void updateLbaasMember(NeutronMemberV2 member) {

    }

    @Override
    public void deleteLbaasMember(String memberId) {

    }

    @Override
    public void createLbaasPool(NeutronLbPoolV2 pool) {

    }

    @Override
    public void updateLbaasPool(NeutronLbPoolV2 pool) {

    }

    @Override
    public void deleteLbaasPool(String poolId) {

    }
}
