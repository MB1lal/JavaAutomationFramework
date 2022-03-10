package utils;

import com.google.gson.Gson;

public interface ToJson {

    default String toJson() { return new Gson().toJson(this);}
}
