package com.highway2urhell.rest;

import com.highway2urhell.domain.ASResponse;
import com.highway2urhell.domain.LoginResponse;
import com.highway2urhell.service.ASService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Named
@Path("/appMobile")
public class BouchonRest {
	@Inject
	private ASService asService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/loginTest")
	public ASResponse loginTest(@FormParam("login") String login,
			@FormParam("password") String password) {
		return asService.checklogin(login, password);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/appfleetmanager/authenticate/login")
	public LoginResponse login(@FormParam("login") String login,
			@FormParam("password") String password,
			@FormParam("externaluser") String externaluser) {
		return asService.checkLoginAs24(login, password, externaluser);
	}

}
