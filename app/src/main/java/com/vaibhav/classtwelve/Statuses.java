package com.vaibhav.classtwelve;

import java.util.ArrayList;

/**
 * Created by Vaibhavv Beri on 08-07-2017.
 */

public class Statuses {
    String link, name;
    ArrayList<User> statuses;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statuses(String link, String name) {

        this.link = link;
        this.name = name;
    }

    public ArrayList<User> getUserArrayList(){
        return statuses;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.statuses= userArrayList;
    }

    public Statuses(ArrayList<User> userArrayList){
        this.statuses= userArrayList;
    }
}
