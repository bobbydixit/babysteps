package com.bobbydixit.babysteps.resources;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/base")
@Api("Base api")
public class BaseResource {

  public BaseResource() {

  }

  @GET
  @Timed
  @Path("/")
  public void hello() {

  }

}
