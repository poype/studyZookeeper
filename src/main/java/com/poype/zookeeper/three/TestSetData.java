package com.poype.zookeeper.three;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestSetData {
    public static void main(String[] args) throws IOException, InterruptedException {
        TestSetDataWatcher watcher = new TestSetDataWatcher();
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.108:2181", 5000, watcher);
        watcher.setZooKeeper(zooKeeper);
        TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
    }
}
