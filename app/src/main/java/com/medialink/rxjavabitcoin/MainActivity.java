package com.medialink.rxjavabitcoin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medialink.rxjavabitcoin.models.Market;
import com.medialink.rxjavabitcoin.networks.ApiCrypto;
import com.medialink.rxjavabitcoin.networks.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private Unbinder unbinder;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setupRecycler();

        ApiCrypto apiCrypto = RetrofitClient.getClient().create(ApiCrypto.class);

        Observable<List<Market>> btcObservable = apiCrypto.getCoinData("btc")
                .map(result -> Observable.fromIterable(result.getTicker().getMarkets()))
                .flatMap(x -> x).filter(y -> {
                    y.setCoinName("btc");
                    return true;
                }).toList().toObservable();

        Observable<List<Market>> ethObservable = apiCrypto.getCoinData("eth")
                .map(result -> Observable.fromIterable(result.getTicker().getMarkets()))
                .flatMap(x -> x).filter(y -> {
                    y.setCoinName("eth");
                    return true;
                }).toList().toObservable();

        Observable.merge(btcObservable, ethObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);
    }

    private void handleError(Throwable t) {
        Toast.makeText(this, "error: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void handleResult(List<Market> markets) {
        if (markets != null && markets.size() > 0) {
            mAdapter.setData((ArrayList<Market>) markets);
            return;
        }
        Toast.makeText(this, "Tidak ada data", Toast.LENGTH_LONG).show();
    }

    private void setupRecycler() {
        mAdapter = new MainAdapter();

        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
