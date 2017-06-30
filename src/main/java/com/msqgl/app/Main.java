package com.msqgl.app;

import com.google.gson.Gson;
import com.msqgl.app.dao.WeddingDAO;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;

public class Main {

  public static void main(String[] args) {

    port(1234);
    final WeddingDAO dao = new WeddingDAO();
    get("/getAllGift", (req, res) -> {
      final Gson gson = new Gson();
      return gson.toJson(dao.getAllGift());
    });

    after((req, res) -> {
      res.type("application/json");
    });
  }

}