package org.onosproject.openstacknetworking.api;

import org.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import org.openstack4j.openstack.networking.domain.ext.NeutronMemberV2;

public interface OpenstackLbaasAdminService {

    void createLbaasLoadBalancer(NeutronLoadBalancerV2 loadBalancer);

    void updateLbaasLoadBalancer(NeutronLoadBalancerV2 loadBalancer);

    void deleteLbaasLoadBalancer(String loadbalancerId);

    void createLbaasListener(NeutronListenerV2 listener);

    void updateLbaasListener(NeutronListenerV2 listener);

    void deleteLbaasListener(String listenerId);

    void createLbaasMember(NeutronMemberV2 member);

    void updateLbaasMember(NeutronMemberV2 member);

    void deleteLbaasMember(String memberId);

    void createLbaasPool(NeutronLbPoolV2 pool);

    void updateLbaasPool(NeutronLbPoolV2 pool);

    void deleteLbaasPool(String poolId);
}
