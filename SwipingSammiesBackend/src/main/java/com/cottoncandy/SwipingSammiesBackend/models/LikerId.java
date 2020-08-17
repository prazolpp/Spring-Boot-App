package com.cottoncandy.SwipingSammiesBackend.models;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import com.cottoncandy.SwipingSammiesBackend.models.Likes;


public class LikerId implements Serializable {

    private String liker;

    private String liked;

    public LikerId() {
    }

    public LikerId(String liker, String liked) {
        this.liker = liker;
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikerId likerId = (LikerId) o;
        return liker.equals(likerId.liker) &&
               liked.equals(likerId.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liker, liked);
    }
}