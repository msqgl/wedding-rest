package com.msqgl.app.data;

import com.google.gson.Gson;

public class ResponseAjax {

  private Object data;
  private boolean error;

  public ResponseAjax(final Object data, final boolean error) {
    this.data = data;
    this.error = error;
  }

  public static ResponseAjax OK() {
    return new ResponseAjax(null, Boolean.FALSE);
  }

  public static ResponseAjax OK(final Object data) {
    return new ResponseAjax(data, Boolean.FALSE);
  }

  public static ResponseAjax KO() {
    return new ResponseAjax(null, Boolean.TRUE);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

}
