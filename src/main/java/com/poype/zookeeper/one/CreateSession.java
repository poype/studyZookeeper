package com.poype.zookeeper.one;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CreateSession {

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateSessionWatcher watcher = new CreateSessionWatcher();
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.108:2181", 5000, watcher);
        watcher.setZooKeeper(zooKeeper);
        TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
    }
}