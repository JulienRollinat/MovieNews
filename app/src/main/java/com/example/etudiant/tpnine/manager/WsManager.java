package com.example.etudiant.tpnine.manager;

import android.util.Log;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WsManager {



    public static final String URL = "http://51.15.254.4:9001/ws/resto/";
    public static final String APIKEY = "?api_key=64180860b634f1f680af2178ceb6aaa6";

    public void executeGetList(final Listener listener){

        Fuel.get("https://api.themoviedb.org/3/trending/movie/day" + APIKEY,null).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("MovieNews","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("MovieNews","success : " + s);
                listener.onSuccess(s);
            }
        });
    }

    public void executeGetMovie(String target, final Listener listener){

        Fuel.get("https://api.themoviedb.org/3/movie/" + target + APIKEY,null).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("tpnineWs","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("tpnineWs","success : " + s);
                listener.onSuccess(s);
            }
        });
    }

    public void sendRequest(String target, Map<String, String> params, final Listener wsListener, boolean headerBool){

        if(params == null){

            params = new HashMap<>();

        }

        String jsonString = new Gson().toJson(params);

        Map<String, String> header = new HashMap<>();

        if(headerBool){
            header.put("Content-Type","application/json");
        }

        Fuel.post("http://51.15.254.4:9001/"+target).body(jsonString, Charset.forName("UTF-8")).header(header).responseString(new Handler<String>() {

            @Override

            public void failure(Request request, Response response, FuelError error) {

                //do something when it is failure

                if(wsListener == null){
                    return;
                }

                wsListener.onFailure(error.toString());

            }

            @Override

            public void success(Request request, Response response, String data) {

                //do something when it is successful

                if(wsListener == null){

                    return;
                }

                wsListener.onSuccess(data);

            }

        });

    }

    public interface Listener {

        void onFailure(String errorMessage);

        void onSuccess(String content);
    }
}