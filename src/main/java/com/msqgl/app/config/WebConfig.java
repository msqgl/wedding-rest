package com.msqgl.app.config;

import com.google.common.net.MediaType;
import com.msqgl.app.model.Gift;
import com.msqgl.app.model.Msg;
import com.msqgl.app.model.Response;
import com.msqgl.app.service.WeddingService;
import com.msqgl.app.utils.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.io.BufferedOutputStream;
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
      manageResponse(request, response);
    });

  }

  private void manageResponse(final Request request, final spark.Response response) {
    final String url = request.url();
    final String[] split = url.split("\\.");
    String respType = StringUtils.EMPTY;
    if (split.length > 0) {
      final String extension = split[split.length - 1];
      switch (extension) {
        case "json":
          respType = MediaType.JSON_UTF_8.toString();
          break;
        case "pdf":
          respType = MediaType.PDF.toString();
          // comment this lines if you don't want to download the file
//          final String format = new SimpleDateFormat("dd-MM-yy_hh:mm:ss").format(new Date());
//          response.header("Content-Disposition", "attachment; " + format + "=.pdf");
          break;
      }
    }
    response.type(respType);
    response.header("Access-Control-Allow-Origin", "*");
  }

  private void setupRoutes() {
    get("/", (request, response) -> "Hello Spring!");

    get("/", (request, response) -> "Hello Raspberry!");

    get("/log", (request, response) -> {
      LOG.info("Hello Log!");
      return "Hello Log!";
    });

    get("/getAllGift.json", (req, res) -> {
      final List<Gift> allGift = service.getAllGift();
      return Response.OK(allGift).toJson();
    });

    post("/saveGiftMsg.json", (request, response) -> {
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

    get("/getAllMsg.json", (request, response) -> {
      final List<Msg> allMsg = service.getAllMsg();
      return Response.OK(allMsg).toJson();
    });

    get("/getPdf.pdf", (request, response) -> {
      service.createPDF(new BufferedOutputStream(response.raw().getOutputStream()));
      return null;
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
