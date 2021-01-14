package com.geektime.Javaconcurrentpractice.ConcurrencyUtils.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.concurrent.*;

@Slf4j
public class FutureDemo {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new FutureDemo().inquiry();
        long endTime = System.currentTimeMillis();
        log.info("耗时：{}s", (endTime - startTime) / 1000);
        //16:26:38.875 [main] INFO com.geektime.Javaconcurrentpractice.ConcurrencyUtils.future.FutureDemo - 耗时：1s
    }


    /**
     * 不久前听说小明要做一个询价应用，这个应用需要从三个电商询价，然后保存在自己的数据库里。核心示例代码如下所示，由于是串行的，所以性能很慢，你来试着优化一下吧。
     * <p>
     * 向电商S1询价，并保存
     * r1 = getPriceByS1();
     * save(r1);
     * 向电商S2询价，并保存
     * r2 = getPriceByS2();
     * save(r2);
     * 向电商S3询价，并保存
     * r3 = getPriceByS3();
     * save(r3);
     */
    @SneakyThrows
    public void inquiry() {
        //...
        ExecutorService es = Executors.newFixedThreadPool(3);
        FutureTask<BigDecimal> task1 = new FutureTask<>(this::getPriceByS1);
        FutureTask<BigDecimal> task2 = new FutureTask<>(this::getPriceByS2);
        FutureTask<BigDecimal> task3 = new FutureTask<>(this::getPriceByS3);
        es.submit(task1);
        es.submit(task2);
        es.submit(task3);
        log.info("price1:{}", task1.get());
        log.info("price2:{}", task2.get());
        log.info("price3:{}", task3.get());

        //16:26:38.851 [main] INFO com.geektime.Javaconcurrentpractice.ConcurrencyUtils.future.FutureDemo - price1:42.88180696976004
        //16:26:38.874 [main] INFO com.geektime.Javaconcurrentpractice.ConcurrencyUtils.future.FutureDemo - price2:64.53322080265255
        //16:26:38.875 [main] INFO com.geektime.Javaconcurrentpractice.ConcurrencyUtils.future.FutureDemo - price3:2.1304738800137004

    }

    @SneakyThrows
    private BigDecimal getPriceByS1() {
        BigDecimal price = BigDecimal.valueOf(RandomUtils.nextDouble(1, 100));
        Thread.sleep(1000);
        return price;
    }

    @SneakyThrows
    private BigDecimal getPriceByS2() {
        BigDecimal price = BigDecimal.valueOf(RandomUtils.nextDouble(1, 100));
        Thread.sleep(1000);
        return price;
    }

    @SneakyThrows
    private BigDecimal getPriceByS3() {
        BigDecimal price = BigDecimal.valueOf(RandomUtils.nextDouble(1, 100));
        Thread.sleep(1000);
        return price;
    }

}
