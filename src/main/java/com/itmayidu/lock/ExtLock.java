package com.itmayidu.lock;

/**
 * @author liukj
 * @date 2020/12/1 18:15
 * @package com.itmayidu.lock
 * @description 基于zk实现分布式锁
 */
public interface ExtLock {

    // ExtLock 基于zk实现分布式锁

    /**
     * 获取到锁的资源
     */
    public void getLock();

    /**
     * 释放锁
     */
    public void unLock();

}
