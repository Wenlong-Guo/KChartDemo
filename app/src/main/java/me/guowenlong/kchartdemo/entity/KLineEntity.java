package me.guowenlong.kchartdemo.entity;

import java.util.List;

/**
 * 某股的k线的实体类
 *
 * @author guowenlong
 * 创建时间:2018-07-18-0:38
 */
public class KLineEntity {

    /**
     * STATUS_OK : ok
     * ch : market.btcusdt.kline.5min
     * ts : 1531829937173
     * data : [{"id":1531829700,"open":6698.95,"close":6703.09,"low":6698.95,"high":6705.34,"amount":43.41836406990447,"vol":290979.4036877161,"count":622},{"id":1531829400,"open":6673.62,"close":6698.58,"low":6673.38,"high":6698.58,"amount":108.9104251617522,"vol":727868.976161,"count":1290},{"id":1531829100,"open":6685.03,"close":6673.62,"low":6671.75,"high":6691.27,"amount":55.43842478262782,"vol":370458.81808685855,"count":950},{"id":1531828800,"open":6679.43,"close":6685.03,"low":6669.54,"high":6693.46,"amount":113.94130220182727,"vol":761225.0529110492,"count":1102},{"id":1531828500,"open":6699,"close":6678.83,"low":6673.34,"high":6699.02,"amount":74.46086364040363,"vol":497694.67702791747,"count":804},{"id":1531828200,"open":6699.61,"close":6699.01,"low":6698.97,"high":6700.24,"amount":15.1703,"vol":101633.563101,"count":310},{"id":1531827900,"open":6704.6,"close":6699.61,"low":6698.41,"high":6704.6,"amount":15.8107,"vol":105944.089876,"count":370},{"id":1531827600,"open":6705.89,"close":6704.6,"low":6700,"high":6708.3,"amount":29.8908,"vol":200384.140391,"count":480},{"id":1531827300,"open":6702.79,"close":6705.67,"low":6702.12,"high":6707.49,"amount":25.290673866008017,"vol":169571.08415388383,"count":426},{"id":1531827000,"open":6707.31,"close":6702.79,"low":6702.1,"high":6709.46,"amount":13.7150027024131,"vol":91981.729091,"count":371},{"id":1531826700,"open":6706.69,"close":6707.31,"low":6705,"high":6714.9,"amount":41.54214910618298,"vol":278688.151475,"count":509},{"id":1531826400,"open":6710.95,"close":6706.69,"low":6705,"high":6711,"amount":42.9871,"vol":288368.594282,"count":469},{"id":1531826100,"open":6713.3,"close":6710.95,"low":6710.26,"high":6713.31,"amount":31.3377,"vol":210338.674021,"count":354},{"id":1531825800,"open":6716.64,"close":6713.3,"low":6713.3,"high":6718.06,"amount":30.499455692585364,"vol":204859.2970025163,"count":384},{"id":1531825500,"open":6719.32,"close":6716.8,"low":6716.64,"high":6720,"amount":22.1935,"vol":149116.5456009637,"count":378},{"id":1531825200,"open":6716.64,"close":6719,"low":6716.64,"high":6719.39,"amount":19.73288818400077,"vol":132553.00407558292,"count":259},{"id":1531824900,"open":6714.04,"close":6716.65,"low":6714.03,"high":6717.32,"amount":20.453867401018908,"vol":137365.74465644374,"count":448},{"id":1531824600,"open":6709.63,"close":6714.64,"low":6709.62,"high":6714.64,"amount":36.93234766045573,"vol":247935.49546323,"count":350},{"id":1531824300,"open":6712.04,"close":6710.24,"low":6709.59,"high":6712.06,"amount":18.709777119665503,"vol":125561.76824192,"count":287},{"id":1531824000,"open":6704.3,"close":6712.14,"low":6703.7,"high":6715,"amount":21.953104930257656,"vol":147280.84816241972,"count":469},{"id":1531823700,"open":6704.28,"close":6704.3,"low":6704.28,"high":6705.9,"amount":25.58170477544846,"vol":171536.14924033,"count":266},{"id":1531823400,"open":6699.24,"close":6704.28,"low":6699.23,"high":6704.71,"amount":14.544442537703596,"vol":97467.746508,"count":292},{"id":1531823100,"open":6698.9,"close":6699.24,"low":6698.23,"high":6699.24,"amount":15.131564750058532,"vol":101363.18857783,"count":276},{"id":1531822800,"open":6695.57,"close":6698.63,"low":6694.97,"high":6700,"amount":27.36262897157593,"vol":183261.30104669,"count":377},{"id":1531822500,"open":6704.42,"close":6694.97,"low":6691.16,"high":6704.48,"amount":36.8105,"vol":246544.560359,"count":395},{"id":1531822200,"open":6705.16,"close":6704.45,"low":6704.19,"high":6709.93,"amount":27.5154,"vol":184552.579429,"count":522},{"id":1531821900,"open":6704.08,"close":6705.16,"low":6703.03,"high":6706.93,"amount":26.61220401289056,"vol":178417.5913799357,"count":501},{"id":1531821600,"open":6697.31,"close":6704.08,"low":6696.22,"high":6704.76,"amount":24.41206782445029,"vol":163552.27707694238,"count":593},{"id":1531821300,"open":6692.87,"close":6697.39,"low":6691.78,"high":6698.88,"amount":52.79376931536061,"vol":353471.1799542682,"count":564},{"id":1531821000,"open":6698.1,"close":6692.87,"low":6692,"high":6698.1,"amount":29.9937,"vol":200810.749804,"count":502},{"id":1531820700,"open":6702.97,"close":6698.1,"low":6698.01,"high":6703.31,"amount":28.5805,"vol":191518.643573,"count":371},{"id":1531820400,"open":6698.13,"close":6702.97,"low":6697.45,"high":6704,"amount":21.195296495141704,"vol":142003.53649604,"count":416},{"id":1531820100,"open":6701.27,"close":6698.14,"low":6693,"high":6701.6,"amount":38.95454699374484,"vol":260838.386809,"count":587},{"id":1531819800,"open":6708.21,"close":6701.65,"low":6700.1,"high":6708.21,"amount":20.148408528109442,"vol":135054.29442299,"count":496},{"id":1531819500,"open":6711.26,"close":6708,"low":6706.84,"high":6711.82,"amount":16.566340172403052,"vol":111145.804158,"count":363},{"id":1531819200,"open":6709.27,"close":6711.28,"low":6709.27,"high":6715.55,"amount":47.98559639731131,"vol":322112.551908,"count":541},{"id":1531818900,"open":6702.9,"close":6709.82,"low":6702.21,"high":6714.4,"amount":42.44282007779469,"vol":284734.414655,"count":718},{"id":1531818600,"open":6693.09,"close":6702.89,"low":6692.33,"high":6705.44,"amount":57.25099611854901,"vol":383568.8191582726,"count":618},{"id":1531818300,"open":6703.31,"close":6693.04,"low":6691.39,"high":6704.79,"amount":24.30066690055758,"vol":162819.262572,"count":297},{"id":1531818000,"open":6719.43,"close":6702.64,"low":6700,"high":6719.43,"amount":91.5526304642555,"vol":614182.74053682,"count":758},{"id":1531817700,"open":6719.43,"close":6719.43,"low":6718.13,"high":6720.5,"amount":63.26649443074926,"vol":425104.2265961096,"count":436},{"id":1531817400,"open":6723.98,"close":6719.48,"low":6716.68,"high":6726.05,"amount":65.73867183251383,"vol":441829.7967758992,"count":608},{"id":1531817100,"open":6724.27,"close":6723.32,"low":6715.7,"high":6724.87,"amount":46.5711,"vol":312964.907683,"count":641},{"id":1531816800,"open":6713.92,"close":6724.3,"low":6710,"high":6725.36,"amount":84.43726870355454,"vol":567124.2456391874,"count":737},{"id":1531816500,"open":6712.62,"close":6713.97,"low":6710.21,"high":6715,"amount":44.4958,"vol":298683.53781,"count":628},{"id":1531816200,"open":6720.2,"close":6712.62,"low":6709.8,"high":6720.67,"amount":66.47525414794265,"vol":446349.40834347,"count":846},{"id":1531815900,"open":6722.73,"close":6720.67,"low":6718.57,"high":6730.34,"amount":43.760776695685266,"vol":294362.675608,"count":537},{"id":1531815600,"open":6722,"close":6722.39,"low":6721.24,"high":6731.95,"amount":54.3808,"vol":365730.0370431696,"count":646},{"id":1531815300,"open":6709.8,"close":6722,"low":6709.79,"high":6723.34,"amount":33.78967526448021,"vol":226931.73126561,"count":664},{"id":1531815000,"open":6713.66,"close":6709.8,"low":6708.47,"high":6713.66,"amount":46.58093332183797,"vol":312612.19896123,"count":549},{"id":1531814700,"open":6714.02,"close":6713.68,"low":6710.32,"high":6716.46,"amount":60.1973617295076,"vol":404122.0812817287,"count":674},{"id":1531814400,"open":6701.44,"close":6714.02,"low":6700,"high":6715.34,"amount":70.23582192973394,"vol":471037.4233471248,"count":814},{"id":1531814100,"open":6690.33,"close":6701.36,"low":6690.31,"high":6701.64,"amount":37.075800286866,"vol":248275.24083643037,"count":424},{"id":1531813800,"open":6693.22,"close":6690.31,"low":6689.55,"high":6693.23,"amount":32.40617792086738,"vol":216825.07760831,"count":375},{"id":1531813500,"open":6693.41,"close":6693.22,"low":6692.68,"high":6693.99,"amount":23.592817433172968,"vol":157909.89720158497,"count":292},{"id":1531813200,"open":6695.5,"close":6693.41,"low":6690,"high":6695.52,"amount":22.146611291409666,"vol":148210.90046294,"count":471},{"id":1531812900,"open":6698.01,"close":6695.52,"low":6694.14,"high":6698.17,"amount":24.76634979930626,"vol":165833.18486742512,"count":416},{"id":1531812600,"open":6697.66,"close":6697.83,"low":6697.04,"high":6698.64,"amount":25.426,"vol":170301.9986293005,"count":441},{"id":1531812300,"open":6702.15,"close":6697.62,"low":6696.13,"high":6702.2,"amount":40.04988061200066,"vol":268275.8196507,"count":508},{"id":1531812000,"open":6690,"close":6702.64,"low":6689.95,"high":6704.18,"amount":86.01233426974714,"vol":576210.4282394988,"count":788}]
     */

    private String status;
    private String ch;
    private long ts;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1531829700
         * open : 6698.95
         * close : 6703.09
         * low : 6698.95
         * high : 6705.34
         * amount : 43.41836406990447
         * vol : 290979.4036877161
         * count : 622
         */

        private int id;
        private float open;
        private float close;
        private float low;
        private float high;
        private double amount;
        private double vol;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getOpen() {
            return open;
        }

        public void setOpen(float open) {
            this.open = open;
        }

        public float getClose() {
            return close;
        }

        public void setClose(float close) {
            this.close = close;
        }

        public float getLow() {
            return low;
        }

        public void setLow(float low) {
            this.low = low;
        }

        public float getHigh() {
            return high;
        }

        public void setHigh(float high) {
            this.high = high;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", open=" + open +
                    ", close=" + close +
                    ", low=" + low +
                    ", high=" + high +
                    ", amount=" + amount +
                    ", vol=" + vol +
                    ", count=" + count +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "KLineEntity{" +
                "STATUS_OK='" + status + '\'' +
                ", ch='" + ch + '\'' +
                ", ts=" + ts +
                ", data=" + data +
                '}';
    }
}