package com.msqgl.app.data;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;

public class ResponseAjax {

  private Object data;
  private int statusCode;
  private boolean error;

  public ResponseAjax(final Object data, final int statusCode, final boolean error) {
    this.data = data;
    this.statusCode = statusCode;
    this.error = error;
  }

  public static ResponseAjax OK() {
    return new ResponseAjax(null, HttpStatus.OK_200, Boolean.FALSE);
  }

  public static ResponseAjax OK(final Object data) {
    return new ResponseAjax(data, HttpStatus.OK_200, Boolean.FALSE);
  }

  public static ResponseAjax KO() {
    return new ResponseAjax(null, HttpStatus.INTERNAL_SERVER_ERROR_500, Boolean.TRUE);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

}
