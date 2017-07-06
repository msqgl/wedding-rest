package com.msqgl.app;

import com.msqgl.app.data.Gift;
import com.msqgl.app.data.Msg;
import com.msqgl.app.data.Response;
import com.msqgl.app.service.WeddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    port(1234);
    final WeddingService service = new WeddingService();

    get("/", (request, response) -> "Hello Raspberry!");

    get("/log", (request, response) -> {
      LOG.info("Hello Log!");
      return "Hello Log!";
    });

    get("/getAllGift", (req, res) -> {
      final List<Gift> allGift = service.getAllGift();
      return Response.OK(allGift).toJson();
    });

    put("/saveMsg", (req, res) -> {
      final String idGift = req.params("idGift");
      final String msgString = req.queryParams("msg");
      final Msg msg = new Msg();
      final String sender = req.params("sender");
      final BigDecimal amount = new BigDecimal(req.params("amount"));
      msg.setIdGift(idGift);
      msg.setMsg(msgString);
      msg.setSender(sender);
      msg.setAmount(amount);

      service.saveMsg(msg);
      return Response.OK().toJson();
    });

    put("/updateGift", (request, response) -> {
      final Map<String, String> params = request.params();
      final String idGift = params.get("idGift");
      final String giftAmount = params.get("giftAmount");
      return null;
    });

    get("getAllMsg", (request, response) -> {
      final List<Msg> allMsg = service.getAllMsg();
      return Response.OK(allMsg).toJson();
    });

    after((req, res) -> {
      res.type("application/json");
    });
  }

}