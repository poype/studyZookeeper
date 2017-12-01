package com.poype.zookeeper.one;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CreateSessionWatcher implements Watcher {

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { //判断是否已连接
            doSomething();
        }
    }

    private void doSomething() {
        System.out.println("已经与zk服务器建立好连接");
    }
}
