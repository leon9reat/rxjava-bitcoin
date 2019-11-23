package com.medialink.rxjavabitcoin.networks;

import com.medialink.rxjavabitcoin.models.Crypto;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCrypto {
    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);
}
