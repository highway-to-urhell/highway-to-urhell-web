package com.highway2urhell.rest;

import com.highway2urhell.domain.H2hConfig;
import com.highway2urhell.domain.MessageBreaker;
import com.highway2urhell.domain.MessageMetrics;
import com.highway2urhell.domain.MessageThunderApp;
import com.highway2urhell.exception.exception.NotExistThunderAppException;
import com.highway2urhell.service.BreakerLogService;
import com.highway2urhell.service.MetricsTimerService;
import com.highway2urhell.service.ThunderAppService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Named
@Path("/ThunderEntry")
@Api(value = "/ThunderEntry", description = "ThunderEntry management")
public class ThunderServerEntryRest {

	private static final Logger LOG = LoggerFactory
			.getLogger(ThunderServerEntryRest.class);

	@Inject
	private ThunderAppService thunderAppService;
	@Inject
	private BreakerLogService breakerLogService;
	@Inject
	private MetricsTimerService metricsTimerService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Create ThunderApp")
	@Path("/createThunderApp/")
	public Response createThunderApp(H2hConfig config) {
		LOG.info("Call createThunderApp with {}", config.getNameApplication());
		String token = thunderAppService.createThunderApp(config);
		return Response.status(Status.ACCEPTED).entity(token).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Init ThunderApp")
	@Path("/initThunderApp")
	public Response initThunderAppAndStat(MessageThunderApp msg) {
		LOG.info("Call createThunderApp with token {} and list size {}",
				msg.getToken(), msg.getListentryPathData().size());
		try {
			thunderAppService.initThunderAppAndStat(msg.getToken(),
					msg.getListentryPathData());
		} catch (NotExistThunderAppException ne) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.ACCEPTED).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation("Add Breaker from Agent Thunder")
	@Path("/addBreaker")
	public Response addBreaker(List<MessageBreaker> listBreaker) {
		breakerLogService.addListBreaker(listBreaker);
		return Response.status(Status.ACCEPTED).entity("OK").build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation("Add indicator Performance Breaker from Agent Thunder")
	@Path("/addPerformance")
	public Response addPerformance(List<MessageMetrics> listPerf) {
		metricsTimerService.addListPerformance(listPerf);
		return Response.status(Status.ACCEPTED).entity("OK").build();
	}

}
