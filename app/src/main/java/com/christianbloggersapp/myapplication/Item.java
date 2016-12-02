package com.christianbloggersapp.myapplication;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by neil on 12/1/16.
 */
@Root(name = "item", strict = false) //strict=false keeps us from having to put everything
public class Item {
    //https://jeaniesjourneys.com/feed/?paged=3
    @Element(name = "title")
    public String title;

    @Element(name = "link")
    public String link;


    @Element(name = "creator", data=true)
    public String creator;

    @Element(name = "description", data=true)
    public String description;






    @Element(name = "pubDate")
    public String pubDate;



}