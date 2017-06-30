package com.msqgl.app;

import com.msqgl.app.dao.WeddingDAO;
import com.msqgl.app.data.Gift;
import com.msqgl.app.data.GiftMsg;
import com.msqgl.app.data.ResponseAjax;

import java.math.BigDecimal;
import java.util.List;

import static spark.Spark.*;

public class Main {

  public static void main(String[] args) {

    port(1234);
    final WeddingDAO dao = new WeddingDAO();

/*
    http://localhost:1234/getAllGift
*/
    get("/getAllGift", (req, res) -> {
      final List<Gift> allGift = dao.getAllGift();
      return ResponseAjax.OK(allGift).toJson();
    });

/*
    http://localhost:1234/saveGiftMsg?idGift=1&msg=Auguri!!&sender=Miky&amount=70
*/
    get("/saveGiftMsg", (req, res) -> {
      final GiftMsg giftMsg = new GiftMsg();
      giftMsg.setIdGift(req.queryParams("idGift"));
      giftMsg.setMsg(req.queryParams("msg"));
      giftMsg.setSender(req.queryParams("sender"));
      giftMsg.setAmount(new BigDecimal(req.queryParams("amount")));
      dao.saveGiftMsg(giftMsg);
      return ResponseAjax.OK().toJson();
    });

    after((req, res) -> {
      res.type("application/json");
    });
  }

}