package com.itmayidu.lock;

/**
 * @author lkj
 * @date 2020/12/1 16:44
 * @package com.itmayidu.lock
 * @description
 */
public class OrderService implements Runnable {
    private OrderNumberGenerator orderNumberGenerator=new OrderNumberGenerator();
    private ExtLock lock=new ZookeeperDistrbuteLock();

    public void run() {
        getNumber();
    }

    /**
     * synchronized 使用的目的：保证线程安全，保证只有一个线程操作。
     */
    public  void getNumber(){
        try {
            lock.getLock();
            String number = orderNumberGenerator.getNumber();
            System.out.println(Thread.currentThread().getName()+"number:"+number);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) {
//        OrderService orderService=new OrderService();
        for(int i=0;i < 100 ;i++){
            new Thread(new OrderService()).start();
        }
    }
}
