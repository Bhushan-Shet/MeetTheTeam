package com.cmb.bhushan.meettheteam;

import java.net.URL;
/**
 * Created by bhshet on 12/17/16.
 */

public class TeamMember {
    String firstName;
    String id;
    String lastName;
    String title;
    String bio;
    String imageURL;

    @Override
    public String toString() {
        return firstName +" " + lastName +", " + title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
