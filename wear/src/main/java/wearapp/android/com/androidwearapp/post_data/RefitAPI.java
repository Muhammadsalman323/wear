package wearapp.android.com.androidwearapp.post_data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

interface RefitAPI {


    @POST
    @FormUrlEncoded
    Call<TestResponse> postData(@Url String baseUrl, @Field("message") String valueOf);
}
