package com.cottoncandy.SwipingSammiesBackend.models;
import org.hibernate.type.UUIDBinaryType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@IdClass(LikerId.class)
public class Likes {


    @Id
    private String liker;

    @Id
    private String liked;

    private boolean matched;

    public Likes(){}

    public Likes( String liker, String liked, boolean matched) {

        this.liker = liker;
        this.liked = liked;
        this.matched = matched;
    }

    public String getLiker(){
        return this.liker;
    }

    public String getLiked(){
        return this.liked;
    }

    public void setMatched(boolean  matched){
        this.matched = matched;
    }

    public boolean getMatched(){
        return this.matched;
    }

    /*
    public void addMatch(String likerEmail, String likedEmail) {

        this.likerEmail = likerEmail;
        this.likedEmail = likedEmail;
    }
    */
}



