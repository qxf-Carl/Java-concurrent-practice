package com.geektime.Javaconcurrentpractice.ConcurrencyUtils.lesson_24_completableFuture;

public class CompletableFutureDemo {


    /**
     * 创建采购订单的时候，需要校验一些规则，例如最大金额是和采购员级别相关的。
     * 有同学利用 CompletableFuture 实现了这个校验的功能，逻辑很简单，首先是从数据库中把相关规则查出来，然后执行规则校验。
     * 你觉得他的实现是否有问题呢？
     *
     * //采购订单
     * PurchersOrder po;
     * CompletableFuture<Boolean> cf =
     * CompletableFuture.supplyAsync(()->{
     * //在数据库中查询规则
     * return findRuleByJdbc();
     * }).thenApply(r -> {
     * //规则校验
     * return check(po, r);
     * });
     * Boolean isOk = cf.join();
     */
    public void homework() {
        //...答
        //1.数据库查询属于IO操作，应该分配单独的线程池，否则会造成默认线程饥饿
        //2.缺少异常处理
    }
}
