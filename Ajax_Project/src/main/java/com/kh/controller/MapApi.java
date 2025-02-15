package com.kh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/admin/monitoring/selectGpsInfo.do")
public class MapApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MapApi() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, Object> resultMap = new HashMap<>();

        String clientId = "dKro6AydtqoXTmh8INl8";
        String clientSecret = "l8XkQKVOq5";

        String address = request.getParameter("address");
        String encodedAddress = "";

        try {
            encodedAddress = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + encodedAddress;
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = HttpUtil.get(apiURL, requestHeaders);

        // JSONParser를 사용하여 JSON 파싱
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            resultMap.put("items", jsonArray);
        } catch (ParseException e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }

        // 결과를 JSON 형식으로 변환하여 클라이언트에 전송
        JSONObject resultJson = new JSONObject(resultMap);
        out.print(resultJson.toJSONString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public static class HttpUtil { // HttpUtil을 public static으로 정의
        public static String get(String apiUrl, Map<String, String> requestHeaders) {
            HttpURLConnection con = connect(apiUrl);
            try {
                con.setRequestMethod("GET");
                for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                    return readBody(con.getInputStream());
                } else { // 오류 발생
                    return readBody(con.getErrorStream());
                }
            } catch (IOException e) {
                throw new RuntimeException("API 요청과 응답 실패", e);
            } finally {
                con.disconnect();
            }
        }

        public static HttpURLConnection connect(String apiUrl) {
            try {
                URL url = new URL(apiUrl);
                return (HttpURLConnection) url.openConnection();
            } catch (MalformedURLException e) {
                throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            } catch (IOException e) {
                throw new RuntimeException("연결이 실패하였습니다. : " + apiUrl, e);
            }
        }

        private static String readBody(InputStream body) {
            InputStreamReader streamReader = new InputStreamReader(body);
            StringBuilder responseBody = new StringBuilder();

            try (BufferedReader lineReader = new BufferedReader(streamReader)) {
                String line;
                while ((line = lineReader.readLine()) != null) {
                    responseBody.append(line);
                }
                return responseBody.toString();
            } catch (IOException e) {
                throw new RuntimeException("API 응답을 읽는데 실패했습니다", e);
            }
        }
    }
}
