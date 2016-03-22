/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Utility to serialize objects and collections back and forth using json protocol 
 * @author georgemullen
 */
public class Converter {

    Gson gson = new Gson();

    public String serialize(Collection<Integer> data) throws IOException {
        return gson.toJson(data);
    }

    public String serialize(int[] data) {
        return gson.toJson(data);
    }

    public int[] deSerialize(String jsString) throws IOException {    
        return gson.fromJson(jsString, int[].class);
    }
}
