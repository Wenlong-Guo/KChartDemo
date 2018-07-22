package me.guowenlong.kchartdemo.entity;

import java.util.List;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-18-1:00
 */
public class SymbolsEntity {

    /**
     * STATUS_OK : ok
     * data : ["hb10","usdt","btc","bch","eth","xrp","ltc","ht","ada","eos","iota","xem","xmr","dash","neo","trx","icx","lsk","qtum","etc","btg","omg","hsr","zec","dcr","steem","bts","waves","snt","salt","gnt","cmt","btm","pay","knc","powr","bat","dgd","ven","qash","zrx","gas","mana","eng","cvc","mco","mtl","rdn","storj","chat","srn","link","act","tnb","qsp","req","rpx","appc","rcn","smt","adx","tnt","ost","itc","lun","gnx","ast","evx","mds","snc","propy","eko","nas","bcd","wax","wicc","topc","swftc","dbc","elf","aidoc","qun","iost","yee","dat","theta","let","dta","utk","meet","zil","soc","ruff","ocn","ela","bcx","sbtc","etf","bifi","zla","stk","wpr","mtn","mtx","edu","blz","abt","ont","ctxc","bft","wan","kan","lba","poly","pai","wtc","box","bt1","bt2"]
     */

    private String status;
    private List<String> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SymbolsEntity{" +
                "STATUS_OK='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
