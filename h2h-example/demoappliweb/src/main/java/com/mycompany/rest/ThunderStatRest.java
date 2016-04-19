package com.highway2urhell.rest;

import com.highway2urhell.dao.ThunderStatDao;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.domain.ThunderStat;
import com.highway2urhell.rest.domain.MessageStat;
import com.highway2urhell.service.ThunderAppService;
import com.highway2urhell.service.ThunderStatService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Named
@Path("/ThunderStat")
@Api(value = "/ThunderStat", description = "ThunderStat management")
public class ThunderStatRest {
    private static final Logger LOG = LoggerFactory.getLogger(ThunderStatRest.class);

    @Inject
	private ThunderStatService thunderStatService;
	@Inject
	private ThunderAppService thunderAppService;
	@Inject
	private ThunderStatDao thunderStatDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Find ThunderStat By Token")
	@Path("/findThunderStatByToken/{token}")
	public Response findThunderStatByToken(@PathParam("token") String token) {
		LOG.info("Call findThunderStatByToken ");
		MessageStat ms = thunderStatService.analysisStat(token);
		//TODO dirty clean this
		ThunderApp app = thunderAppService.findAppByToken(token);
		ms.setAnalysis(app.getAnalysis());
		ms.setAppName(app.getNameApp());
		return Response.status(Status.ACCEPTED).entity(ms).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Update ThunderStat false positive")
	@Path("/updateThunderStatFalsePositive/{id}/{status}")
	public Response updateThunderStatFalsePositive(@PathParam("id") String id,@PathParam("status") String status) {
		thunderStatService.updateThunderStatFalsePositive(id, status);
		return Response.status(Status.ACCEPTED).build();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("findBytoken")
	@Path("/findByToken/{token}")
	public Response updateThunderStatFalsePositive(@PathParam("token") String token) {
		List<ThunderStat> res = thunderStatDao.findByToken(token);
		return Response.status(Status.ACCEPTED).entity(res).build();
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation("Find ThunderStat By Token")
	@Path("/findThunderStatByTokenAndFilter/{token}")
	public Response findThunderStatByTokenAndFilter(@PathParam("token") String token) {
		LOG.info("Call findThunderStatByTokenAndFilter ");
		MessageStat ms = thunderStatService.analysisStat(token);
		//filter
		thunderStatService.filterFramework(ms.getListThunderStat());
		//TODO dirty clean this
		ThunderApp app = thunderAppService.findAppByToken(token);
		ms.setAnalysis(app.getAnalysis());
		ms.setAppName(app.getNameApp());
		return Response.status(Status.ACCEPTED).entity(ms).build();
	}

	

		
}
