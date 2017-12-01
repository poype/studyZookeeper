package com.poype.zookeeper.one;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CreateSessionWatcher implements Watcher {

    public void process(WatchedEvent watchedEvent) {
        System.out.println("收到事件" + watchedEvent);
    }
}
