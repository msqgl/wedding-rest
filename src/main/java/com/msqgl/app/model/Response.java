package com.msqgl.app.model;

import com.google.gson.Gson;

public class Response {

  private Object data;
  private boolean error;

  public Response(final Object data, final boolean error) {
    this.data = data;
    this.error = error;
  }

  public static Response OK() {
    return new Response(null, Boolean.FALSE);
  }

  public static Response OK(final Object data) {
    return new Response(data, Boolean.FALSE);
  }

  public static Response KO() {
    return new Response(null, Boolean.TRUE);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }
}
