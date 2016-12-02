package com.christianbloggersapp.myapplication;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by neil on 12/2/16.
 */

@Root(name = "channel")
public class Channel {
    @Element
    public String title;
}
/*
@Root(name = "channel", strict = false)
    static class Channel {

        @ElementList(inline = true, name="item")
        public List<Item> items;
    }

 */