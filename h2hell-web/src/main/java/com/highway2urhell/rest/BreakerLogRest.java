package com.highway2urhell.rest;

import com.highway2urhell.dao.BreakerLogDao;
import com.highway2urhell.domain.BreakerLog;
import com.highway2urhell.rest.domain.MessageBreakerLog;
import com.highway2urhell.service.BreakerLogService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

@Named
@Path("/BreakerLog")
@Api(value = "/BreakerLog", description = "BreakerLog management")
public class BreakerLogRest {

    private static final Logger LOG = LoggerFactory.getLogger(BreakerLogRest.class);

	@Inject
	private BreakerLogDao breakerLogDao;
	@Inject
	private BreakerLogService breakerLogService;

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
		List<MessageBreakerLog> breaker = breakerLogService.findByToken(token);
		return Response.status(Status.ACCEPTED).entity(breaker).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@ApiOperation("sendCsv")
	@Path("/sendCsv/{token}")
	public Response sendCsv(@PathParam("token") String token) {
		LOG.info("Call sendCsv ");
		String fileName = "fileExport"+System.currentTimeMillis()+".csv";
		breakerLogService.extractBreakerWithTokenToCsv(fileName,token);
		File file = new File(fileName);
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"") //optional
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("sendJson")
	@Path("/sendJson/{token}")
	public Response sendJson(@PathParam("token") String token) {
		LOG.info("Call sendJson ");
		String csv = breakerLogService.extractBreakerWithTokenToJson(token);
		return Response.status(Status.ACCEPTED).entity(csv).build();
	}

}
