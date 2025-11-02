package util;

import com.google.gson.*;

import model.Graph;

import java.io.*;
import java.nio.file.*;

public class GraphLoader {
    public static Graph load(String resourcePath) throws IOException {
        String path = GraphLoader.class.getResource(resourcePath).getPath();
        String json = Files.readString(Path.of(path));
        Gson gson = new Gson();
        return gson.fromJson(json, Graph.class);
    }
}
