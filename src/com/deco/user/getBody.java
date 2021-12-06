package com.deco.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class getBody extends HttpServlet {

    public static String readBody(HttpServletRequest req) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
    
    public static String getReqData(HttpServletRequest req) throws Exception{
    	//post로 온 body를 받는 영역
		String postBody = getBody.readBody(req);

		JSONParser jsonParser = new JSONParser();
		JSONObject postBodyObject = (JSONObject) jsonParser.parse(postBody);

    	return (String)postBodyObject.get("reqData"); 
    }
    
    public static JSONObject getJsonObj(HttpServletRequest req) throws Exception{
    	//post로 온 body를 받는 영역
    	String postBody = getBody.readBody(req);
    	
    	JSONParser jsonParser = new JSONParser();
		JSONObject postBodyObject = (JSONObject) jsonParser.parse(postBody);
    	
    	return postBodyObject;
    }
}
