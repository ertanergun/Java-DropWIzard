package com.truecallertask.resources;

import com.truecallertask.core.UserView;
import com.truecallertask.data.UserViewDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/view")
public class UserViewResource {

    private final UserViewDAO userViewDAO;

    public UserViewResource(UserViewDAO userViewDAO)
    {
        this.userViewDAO = userViewDAO;
    }

    @GET
    @UnitOfWork
    @Path("/viewer={viewerId}&viewing={viewedId}")
    public String getUserView(@PathParam("viewerId") Long viewerId, @PathParam("viewedId") Long viewedId) {

        String message;
        try
        {
            UserView userView = new UserView(viewerId,viewedId, DateTime.now());
            this.userViewDAO.create(userView);
            message = String.format("User with Id: %d is view the User with id: %d on %s",userView.getViewerId(),userView.getViewerId(), userView.getViewDate().toString());
        }
        catch (Exception ex)
        {
            message = ex.getMessage();
        }

        return message;
    }

    @GET
    @UnitOfWork
    @Path("/listviewerfor={viewedId}")
    public String listUserViews(@PathParam("viewedId") long viewedId) {

        StringBuilder builder = new StringBuilder();
        List<UserView> viewList  = new ArrayList<UserView>();
        try {
            viewList = this.userViewDAO.getViewList(viewedId);

            if(viewList.size() == 0)
            {
                builder.append("No records to display");
            }

            for (int i = 0; i < viewList.size(); i++) {
                String row = String.format("User with Id: %d is view the User with id: %d on %s %n",viewList.get(i).getViewerId(),viewList.get(i).getViewerId(), viewList.get(i).getViewDate().toString());
                builder.append(row);

            }
        }
        catch (Exception ex)
        {
            builder = new StringBuilder();
            builder.append(ex.getMessage());
        }

        return builder.toString();
    }
}
