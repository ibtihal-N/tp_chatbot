package ma.enset.tp_chatbot.api;

import ma.enset.tp_chatbot.model.BrainShopeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface BrainShopApi {
    @GET
    Call<BrainShopeResponse> getMessage(@Url String url);


}
