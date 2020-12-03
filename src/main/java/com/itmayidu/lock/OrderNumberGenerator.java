package com.itmayidu.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xxx
 * @date 2020/12/1 16:30
 * @package com.itmayidu.lock
 * @description 生成订单号使用时间戳
 */
public class OrderNumberGenerator {

    // 区分不同的订单号
    private static  int count=0;

    /**
     * 单台服务器上，多个线程同时生成订单，线程安全。
     */
    public String getNumber() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }
}
