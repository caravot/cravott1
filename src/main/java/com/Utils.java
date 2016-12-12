package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by carrie on 12/12/16.
 */
public class Utils {

    public static InputStream openConnection(String url) throws IOException {
        URL endpoint = new URL(url);

        System.out.println("Printing endpoint:");
        System.out.println(endpoint);

        URLConnection urlCon = endpoint.openConnection();

        return urlCon.getInputStream();
    }
}
