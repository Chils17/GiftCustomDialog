package com.webmyne.customdialog;

import java.io.Serializable;

/**
 * Created by chiragpatel on 07-11-2017.
 */

public class Gift implements Serializable {
    private String url;
    private String name;
    private String number;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
