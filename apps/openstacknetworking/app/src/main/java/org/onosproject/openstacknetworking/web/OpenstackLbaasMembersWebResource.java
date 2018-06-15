/*
 * Copyright 2018-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.openstacknetworking.web;

import org.onosproject.openstacknetworking.api.OpenstackLbaasAdminService;
import org.onosproject.rest.AbstractWebResource;
import org.openstack4j.openstack.networking.domain.ext.NeutronMemberV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;

import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.status;
import static org.onosproject.openstacknetworking.util.OpenstackNetworkingUtil.jsonToModelEntity;

/**
 * Handles Rest API call from Neutron ML2 plugin (LbaaS Member).
 */
@Path("lbaas/pools/{pid}/members")
public class OpenstackLbaasMembersWebResource extends AbstractWebResource {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private static final String MESSAGE = "Received LBaaS pool member %s request";
    private static final String LBAAS = "lbaas/pools";

    private final OpenstackLbaasAdminService lbaasAdminService = get(OpenstackLbaasAdminService.class);

    @Context
    private UriInfo uriInfo;

    /**
     * Creates a member from the JSON input stream.
     *
     * @param  pid member id
     * @param input member JSON input stream
     * @return 201 CREATED if the JSON is correct, 400 BAD_REQUEST if the JSON
     * is invalid or duplicated member already exists
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(@PathParam("pid") String pid, InputStream input) {
        log.trace(String.format(MESSAGE, "CREATE " + pid));

        final NeutronMemberV2 lbMember = (NeutronMemberV2)
                jsonToModelEntity(input, NeutronMemberV2.class);

        lbaasAdminService.createLbaasMember(lbMember);

        UriBuilder locationBuilder = uriInfo.getBaseUriBuilder()
                .path(LBAAS)
                .path(lbMember.getId());

        return created(locationBuilder.build()).build();
    }

    /**
     * Updates the member with the specified identifier.
     *
     * @param  pid member id
     * @param input member JSON input stream
     * @return 200 OK with the updated member, 400 BAD_REQUEST if the requested
     * subnet does not exist
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMember(@PathParam("pid") String pid, InputStream input) {
        log.trace(String.format(MESSAGE, "UPDATE " + pid));

        final NeutronMemberV2 lbMember = (NeutronMemberV2)
                jsonToModelEntity(input, NeutronMemberV2.class);

        lbaasAdminService.updateLbaasMember(lbMember);

        return status(Response.Status.OK).build();
    }

    /**
     * Removes the member.
     *
     * @param id member identifier
     * @return 204 NO_CONTENT, 400 BAD_REQUEST if the member does not exist
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMember(@PathParam("id") String id) {
        log.trace(String.format(MESSAGE, "DELETE " + id));

        lbaasAdminService.deleteLbaasMember(id);

        return status(Response.Status.OK).build();
    }

}
