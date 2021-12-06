<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
JSONObject result = (JSONObject)request.getAttribute("Exists");
%>
<%=result%>