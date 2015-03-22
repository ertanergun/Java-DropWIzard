package com.truecallertask.core;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(
                name = "com.truecallertask.core.User.findAll",
                query = "SELECT u FROM User u"
        )
})
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", nullable = false)
    private long Id;

    @Column(name = "FullName", nullable = false)
    private String FullName;

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFullName(){
        return FullName;
    }

    public void setFullName(String fullName){
        FullName = fullName;
    }
}
