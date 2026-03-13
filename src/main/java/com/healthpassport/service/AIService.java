


package com.healthpassport.service;

import okhttp3.*;
        import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final String API_KEY = "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";

    public String analyzeHealth(String prompt) {

        try {

            OkHttpClient client = new OkHttpClient();

            String json = "{"
                    + "\"model\":\"meta-llama/llama-3.1-8b-instruct\","
                    + "\"messages\":["
                    + "{ \"role\":\"user\", \"content\":\"" + prompt.replace("\"","\\\"") + "\" }"
                    + "]"
                    + "}";

            RequestBody body = RequestBody.create(
                    json,
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url("https://openrouter.ai/api/v1/chat/completions")
                    .post(body)
                    .addHeader("Authorization","Bearer " + API_KEY)
                    .addHeader("Content-Type","application/json")
                    .build();

            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();

            System.out.println("AI RESPONSE:");
            System.out.println(responseBody);

            int start = responseBody.indexOf("\"content\":\"");

            if(start == -1){
                return "Health Summary\nAI could not analyze the report.";
            }

            start += 11;

            int end = responseBody.indexOf("\"", start);

            String result = responseBody.substring(start, end);

            return result.replace("\\n","\n");

        }
        catch(Exception e){

            e.printStackTrace();

            return "Health Summary\nAI service temporarily unavailable.\n\nPossible Risks\n- Unknown\n\nSuggestions\n- Try again later";
        }

    }
}