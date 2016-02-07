package com.highway2urhell.web.rest;

import com.highway2urhell.repository.MetricsTimerRepository;
import com.highway2urhell.domain.MetricsTimer;
import com.highway2urhell.domain.MessageFilterMetricsModel;
import com.highway2urhell.domain.MessageMetricsData;
import com.highway2urhell.service.MetricsTimerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("/MetricsLog")
@Api(value = "/MetricsLog", description = "MetricsLog management")
public class MetricsLogRest {
    private static final Logger LOG = LoggerFactory.getLogger(MetricsLogRest.class);

    @Inject
    private MetricsTimerRepository metricsTimerRepository;
    @Inject
    private MetricsTimerService metricsTimerService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Find Metrics Log by Token")
    @Path("/findMetricsByToken/{token}")
    public Response findMetricsByToken(@PathParam("token") String token) {
        LOG.info("Call findMetricsByToken ");
        List<MetricsTimer> metrics = metricsTimerRepository.findByToken(token);
        return Response.status(Response.Status.ACCEPTED).entity(metrics).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Find Init Metrics Log by Token")
    @Path("/findMetricsInit/{token}")
    public Response findMetricsInit(@PathParam("token") String token) {
        LOG.info("Call findMetricsInit ");
        MessageMetricsData metrics = metricsTimerService.findMetricsInit(token);
        return Response.status(Response.Status.ACCEPTED).entity(metrics).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Find Metrics Log by Date")
    @Path("/findMetricsFromLastInc/{token}/{lastInc}")
    public Response findMetricsFromLastInc(@PathParam("token") String token,@PathParam("lastInc") Integer lastInc) {
        LOG.info("Call findMetricsFromLastInc ");
        MessageMetricsData metrics = metricsTimerService.findMetricsFromlastInc(token, lastInc);
        return Response.status(Response.Status.ACCEPTED).entity(metrics).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Find Metrics Log by Date")
    @Path("/findMetrics/")
    public Response findMetrics(MessageFilterMetricsModel filterMetricsModel) {
        LOG.info("Call findMetrics ");
        MessageMetricsData metrics = metricsTimerService.findMetricsWithFilter(filterMetricsModel);
        return Response.status(Response.Status.ACCEPTED).entity(metrics).build();
    }



}
