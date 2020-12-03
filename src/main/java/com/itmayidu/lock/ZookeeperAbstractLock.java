package com.itmayidu.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author liukj
 * @date 2020/12/1 18:18
 * @package com.itmayidu.lock
 * @description 将重复的代码抽象到子类中（使用设计模式中的模板设计模式）
 */
public abstract class ZookeeperAbstractLock implements ExtLock {

    private static final String  CONNECTION="192.168.1.68:2181";
    protected ZkClient zkClient=new ZkClient(CONNECTION);
    protected String lockPath="/lockPath";
    protected CountDownLatch countDownLatch = null;
    /**
     * 获取锁
     */
    public void getLock() {
        // 1、连接zkClient，在zk上创建一个/lock节点，类型为临时节点。
        if(tryLock()){
            System.out.println("----------  获取锁资源成功 -----------");
        }else {
            waitLock();
            getLock();
        }
        // 2、如果节点创建成功，直接执行业务逻辑。如果创建失败，则进行等待。

        // 3、使用事件通知监听该节点是否被删除，如果被删除的话重新进入获取锁资源。
    }

    /**
     * 获取锁资源，如果能够获取锁成功，返回true，否则返回false
     * @return
     */
    abstract   boolean tryLock();

    /**
     * 如果创建失败，则进行等待。使用事件通知监听该节点是否被删除，如果被删除的话重新进入获取锁资源。
     */
    abstract   void waitLock();


    /**
     * 释放锁
     */
    public void unLock() {
        // 当程序执行完毕之后，直接关闭连接
        if(zkClient!=null){
            zkClient.close();
            System.out.println("---------------- 释放锁成功 ---------------");
        }
    }

}
