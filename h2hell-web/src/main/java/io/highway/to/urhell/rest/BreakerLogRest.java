package io.highway.to.urhell.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.highway.to.urhell.dao.BreakerLogDao;
import io.highway.to.urhell.domain.BreakerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Named
@Path("/BreakerLog")
@Api(value = "/BreakerLog", description = "BreakerLog management")
public class BreakerLogRest {

    private static final Logger LOG = LoggerFactory.getLogger(ThunderStatRest.class);

	@Inject
	private BreakerLogDao breakerLogDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Find all Breaker Log")
	@Path("/findAllBreaker")
	public Response findAll() {
		LOG.info("Call findAllBreaker ");
		List<BreakerLog> listBreakerLog = breakerLogDao.findAll();
		return Response.status(Status.ACCEPTED).entity(listBreakerLog).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Find Breaker Log by Token")
	@Path("/findBreakerWithToken/{token}")
	public Response findBreakerWithToken(@PathParam("token") String token) {
		LOG.info("Call findBreakerWithToken ");
		List<BreakerLog> breaker = breakerLogDao.findByToken(token);
		return Response.status(Status.ACCEPTED).entity(breaker).build();
	}

}
