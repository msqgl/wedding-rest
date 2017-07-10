package com.msqgl.app.config;

import com.msqgl.app.model.Gift;
import com.msqgl.app.model.Msg;
import com.msqgl.app.model.Response;
import com.msqgl.app.service.WeddingService;
import com.msqgl.app.utils.ValidationUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static spark.Spark.*;

public class WebConfig {

  private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

  private WeddingService service;

  public WebConfig(WeddingService service) {
    this.service = service;
    setupConfig();
    setupFilters();
    setupRoutes();
    setupError();
  }

  private void setupConfig() {
    port(1234);
  }

  private void setupFilters() {
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
      res.header("Access-Control-Allow-Origin", "*");
    });
  }

  private void setupRoutes() {
    get("/", (request, response) -> "Hello Spring!");

    get("/", (request, response) -> "Hello Raspberry!");

    get("/log", (request, response) -> {
      LOG.info("Hello Log!");
      return "Hello Log!";
    });

    get("/getAllGift", (req, res) -> {
      final List<Gift> allGift = service.getAllGift();
      return Response.OK(allGift).toJson();
    });

    post("/saveGiftMsg", (request, response) -> {
      final String idGift = request.queryParams("idGift");
      final String msg = request.queryParams("msg");
      final String sender = request.queryParams("sender");
      final String amount = request.queryParams("amount");

      Response resp;
      if (ValidationUtils.validateString(idGift, sender, amount)) {
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
  }

  private void setupError() {

    exception(Exception.class, (e, request, response) -> {
      LOG.error("Exception", e);
      response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
      response.body(Response.KO().toJson());
    });

  }

}
