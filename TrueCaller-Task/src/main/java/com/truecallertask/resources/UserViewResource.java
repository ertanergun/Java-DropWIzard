package com.truecallertask.resources;

import com.truecallertask.data.UserDAO;
import com.truecallertask.data.UserViewDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/view")
public class UserViewResource {

    private final UserDAO userDAO;
    private final UserViewDAO userViewDAO;

    public UserViewResource(UserDAO userDAO, UserViewDAO userViewDAO)
    {
        this.userDAO = userDAO;
        this.userViewDAO = userViewDAO;
    }

    @GET
    @Path("/viewer={viewerId}&viewing={viewingId}")
    public String getUserView(@PathParam("viewerId") Long viewer, @PathParam("viewingId") Long viewed) {

        return "";
    }

    @GET
    @Path("/listviewerfor={userId}")
    public void listUserViews(@PathParam("userId") Long userId){

    }


}
