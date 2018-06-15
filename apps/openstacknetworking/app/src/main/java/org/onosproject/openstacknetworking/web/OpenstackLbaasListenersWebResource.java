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
import org.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
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
 * Handles Rest API call from Neutron ML2 plugin (LBaaS Listener).
 */
@Path("lbaas/listeners")
public class OpenstackLbaasListenersWebResource extends AbstractWebResource {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private static final String MESSAGE = "Received LBaaS listeners %s request";
    private static final String LBAAS = "lbaas/listeners";

    private final OpenstackLbaasAdminService lbaasAdminService = get(OpenstackLbaasAdminService.class);

    @Context
    private UriInfo uriInfo;

    /**
     * Creates a listener from the JSON input stream.
     *
     * @param input listener JSON input stream
     * @return 201 CREATED if the JSON is correct, 400 BAD_REQUEST if the JSON
     * is invalid or duplicated listener already exists
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createListener(InputStream input) {
        log.trace(String.format(MESSAGE, "CREATE"));

        final NeutronListenerV2 listener = (NeutronListenerV2)
                jsonToModelEntity(input, NeutronListenerV2.class);

        lbaasAdminService.createLbaasListener(listener);

        UriBuilder locationBuilder = uriInfo.getBaseUriBuilder()
                .path(LBAAS)
                .path(listener.getId());

        return created(locationBuilder.build()).build();
    }

    /**
     * Updates the listener with the specified identifier.
     *
     * @param id    listener identifier
     * @param input listener JSON input stream
     * @return 200 OK with the updated listener, 400 BAD_REQUEST if the requested
     * subnet does not exist
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateListener(@PathParam("id") String id, InputStream input) {
        log.trace(String.format(MESSAGE, "UPDATE " + id));

        final NeutronListenerV2 listener = (NeutronListenerV2)
                jsonToModelEntity(input, NeutronListenerV2.class);

        lbaasAdminService.updateLbaasListener(listener);

        return status(Response.Status.OK).build();
    }

    /**
     * Removes the listener.
     *
     * @param id listener identifier
     * @return 204 NO_CONTENT, 400 BAD_REQUEST if the listener does not exist
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteListener(@PathParam("id") String id) {
        log.trace(String.format(MESSAGE, "DELETE " + id));

        lbaasAdminService.deleteLbaasListener(id);

        return status(Response.Status.OK).build();
    }

}
