package com.msqgl.app;

import com.msqgl.app.data.Gift;
import com.msqgl.app.data.Msg;
import com.msqgl.app.data.ResponseAjax;
import com.msqgl.app.service.WeddingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {

  public static void main(String[] args) {

    port(1234);
    final WeddingService service = new WeddingService();

/*
    http://localhost:1234/getAllGift
*/
    get("/getAllGift", (req, res) -> {
      final List<Gift> allGift = service.getAllGift();
      return ResponseAjax.OK(allGift).toJson();
    });

/*
    http://localhost:1234/saveGiftMsg?idGift=1&msg=Auguri!!&sender=Miky&amount=70
*/
    get("/saveGiftMsg", (req, res) -> {
      final Msg msg = new Msg();
      msg.setIdGift(req.queryParams("idGift"));
      msg.setMsg(req.queryParams("msg"));
      msg.setSender(req.queryParams("sender"));
      msg.setAmount(new BigDecimal(req.queryParams("amount")));
      service.saveGiftMsg(msg);
      return ResponseAjax.OK().toJson();
    });

    put("/updateGift", (request, response) -> {
      final Map<String, String> params = request.params();
      final String idGift = params.get("idGift");
      final String giftAmount = params.get("giftAmount");

      return null;
    });

    after((req, res) -> {
      res.type("application/json");
    });
  }

}