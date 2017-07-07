package com.msqgl.app;

import com.msqgl.app.data.Gift;
import com.msqgl.app.data.Msg;
import com.msqgl.app.data.Response;
import com.msqgl.app.service.WeddingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

    put("/saveGiftMsg", (request, response) -> {
      final String idGift = request.queryParams("idGift");
      final String msg = request.queryParams("msg");
      final String sender = request.queryParams("sender");
      final String amount = request.queryParams("amount");

      Response resp;
      if (validateString(idGift, sender, amount)) {
        service.saveGiftMsg(idGift, msg, sender, new BigDecimal(amount));
        resp = Response.OK();
      } else {
        resp = Response.KO();
      }
      return resp.toJson();
    });

    get("getAllMsg", (request, response) -> {
      final List<Msg> allMsg = service.getAllMsg();
      return Response.OK(allMsg).toJson();
    });

    before((request, response) -> {
      LOG.info("Called {}", request.uri());
      final Set<String> queryParams = request.queryParams();
      StringBuilder stringBuilder = new StringBuilder();
      for (String queryParam : queryParams) {
        if (stringBuilder.length() > 0) {
          stringBuilder.append(", ");
        }
        stringBuilder.append(queryParam);
        stringBuilder.append(" '");
        stringBuilder.append(request.queryParams(queryParam));
        stringBuilder.append("'");
      }
      if (stringBuilder.length() > 0) {
        LOG.info("Request parameters: {}", stringBuilder.toString());
      }
    });

    after((req, res) -> {
      res.type("application/json");
    });

    exception(Exception.class, (e, request, response) -> {
      LOG.error("Exception", e);
    });

  }

  private static boolean validateString(String... strings) {
    for (String str : strings) {
      if (StringUtils.isEmpty(str)) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

}