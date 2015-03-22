package com.truecallertask.core;

import org.joda.time.DateTime;
import javax.persistence.*;


@Entity
@Table(name = "UserViews")
@NamedQueries({
        @NamedQuery(
                name = "com.truecallertask.core.UserView.findAll",
                query = "SELECT u FROM UserView u"
        )
})
public class UserView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", nullable = false)
    private long Id;



    /*
        @OneToOne(fetch = FetchType.LAZY)
        @PrimaryKeyJoinColumn(name = "ViewerId", referencedColumnName = "Id")
        private User Viewer;

        @OneToOne(fetch = FetchType.LAZY)
        @PrimaryKeyJoinColumn(name = "ViewedId", referencedColumnName = "Id")
        private User Viewed;
    */
    @Column(name="ViewerId", nullable = false)
    private long viewerId;

    @Column(name="ViewedId", nullable = false)
    private long viewedId;

    public long getViewerId() {
        return viewerId;
    }

    public void setViewerId(long viewerId) {
        this.viewerId = viewerId;
    }

    public long getViewedId() {
        return viewedId;
    }

    public void setViewedId(long viewedId) {
        this.viewedId = viewedId;
    }



    @Column(name = "ViewDate", nullable = false)
    private DateTime ViewDate;

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
/*
    public User getViewer() {
        return Viewer;
    }

    public void setViewer(User viewer) {
        Viewer = viewer;
    }

    public User getViewed() {
        return Viewed;
    }

    public void setViewed(User viewed) {
        Viewed = viewed;
    }
*/
    public DateTime getViewDate() {
        return ViewDate;
    }

    public void setViewDate(DateTime viewDate) {
        ViewDate = viewDate;
    }


}
