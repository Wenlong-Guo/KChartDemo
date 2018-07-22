package me.guowenlong.kchartdemo.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import me.guowenlong.kchartdemo.App;
import me.guowenlong.kchartdemo.C;
import me.guowenlong.kchartdemo.R;
import me.guowenlong.kchartdemo.entity.SymbolsEntity;
import me.guowenlong.kchartdemo.http.Api;
import me.guowenlong.kchartdemo.http.RxSchedulers;
import me.guowenlong.kchartdemo.ui.kline.KLineActivity;
import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    private Api api;
    private RecyclerView mRecyclerView;
    private SymbolsAdatper mAdapter;
    private SymbolsEntity mSymbolsEntity;
    public static final String INTENT_SYMBOL = "symbol";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = App.getApi();
        mRecyclerView = findViewById(R.id.rv_symbols);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SymbolsAdatper();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setItemClickListener(new SymbolsAdatper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, KLineActivity.class);
                intent.putExtra(INTENT_SYMBOL,mSymbolsEntity.getData().get(position)+"usdt");
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSymbols();
    }
    /**
     * 获取币种列表
     */
    private void getSymbols() {
        final Observable<SymbolsEntity> symbols = api.getSymbols(C.ACCESS_KEY_ID).compose(RxSchedulers.<SymbolsEntity>io_main());
        ;
        symbols.subscribe(new Observer<SymbolsEntity>() {
            @Override
            public void onNext(SymbolsEntity symbolsEntity) {
                if (symbolsEntity != null && TextUtils.equals(C.STATUS_OK, symbolsEntity.getStatus())) {
                    symbolsEntity.getData().remove("usdt");
                    mSymbolsEntity = symbolsEntity;
                    mAdapter.setLists(symbolsEntity.getData());
                } else {
                    Toast.makeText(MainActivity.this, "异常", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "异常"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}