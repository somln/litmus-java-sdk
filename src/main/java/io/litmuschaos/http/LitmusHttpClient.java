package io.litmuschaos.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class LitmusHttpClient implements AutoCloseable{

    private final OkHttpClient okHttpClient;

    public LitmusHttpClient() {
        this.okHttpClient = new OkHttpClient();
    }

    public <T> T get(String url, Class<T> returnType) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return transform(request, returnType);
    }

    public  <T> T  get(String url, String token, Class<T> returnType) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", "Bearer " + token) // Authorization 헤더 추가
                .build();
        return transform(request, returnType);
    }

    public <T> T post(String url, Object object, Class<T> returnType) throws IOException {
        RequestBody body = RequestBody.create(toJson(object), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();
        return transform(request, returnType);
    }

    public <T> T post(String url, String token, Object object, Class<T> returnType) throws IOException {
        RequestBody body = RequestBody.create(toJson(object), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        return transform(request, returnType);
    }

    private <T> T transform(Request request, Class<T> returnType) throws IOException {
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();

        // JSON 응답에서 "data" 필드를 추출
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonElement dataElement = jsonObject.get("data");

        // "data" 필드가 있는 경우 이를 returnType으로 변환
        if (dataElement != null) {
            return new Gson().fromJson(dataElement, returnType);
        }

        // "data" 필드가 없고, returnType이 String인 경우 문자열로 반환
        if (returnType.equals(String.class)) {
            return returnType.cast(responseBody);
        }

        // 기본적으로 전체 응답을 returnType으로 변환
        return new Gson().fromJson(responseBody, returnType);
    }

    private String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Override
    public void close() throws Exception {
        // TODO
    }
}
