package com.example.application.test;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Map;

public class Task extends AsyncTask<Map<String, String>, Integer, String> {

    public static String ip = "localhost"; // 자신의 IP주소를 쓰시면 됩니다.

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터

        // Http 요청 준비 작업

        HttpClient.Builder http = new HttpClient.Builder
                ("POST", "http://" + ip + ":8090/spring_mybatis/vision"); //포트번호,서블릿주소 ??

        // Parameter 를 전송한다.
        http.addAllParameters(maps[0]);

        //Http 요청 전송
        HttpClient post = http.create();
        post.request();

        // 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();

        // 응답 본문 가져오기
        String body = post.getBody();

        return body;
    }

    @Override
    protected void onPostExecute(String s) { //서블릿으로부터 값을 받을 함수

        Gson gson = new Gson();
        Vision data = gson.fromJson(s, Vision.class);

        System.out.println("번호 : "+data.getNum());
        System.out.println("이름 : "+data.getName());
        System.out.println("장소 : "+data.getLocation());
        System.out.println("위도 : "+data.getLatitude());
        System.out.println("경도 : "+data.getLongitude());
        System.out.println("날짜 : "+data.getDay());
        System.out.println("내용 : "+data.getContent());
    }
}