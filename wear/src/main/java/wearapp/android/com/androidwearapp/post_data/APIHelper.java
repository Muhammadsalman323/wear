package wearapp.android.com.androidwearapp.post_data;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    private static final String BASE_URL = "http://192.168.1.105:8000/";
    private static APIHelper instance = new APIHelper();
    private RefitAPI refitService;


    private APIHelper() {
        Retrofit retrofit = createAdapter().build();
        refitService = retrofit.create(RefitAPI.class);
    }

    public static APIHelper getInstance() {
        return instance;
    }


    private Retrofit.Builder createAdapter() {
        // Define the interceptor, add authentication headers
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<TestResponse> postData(String valueOf) {
        return refitService.postData(BASE_URL,valueOf);
    }
}