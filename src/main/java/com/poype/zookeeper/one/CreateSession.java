package com.poype.zookeeper.one;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CreateSession {

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.108:2181", 5000,
                new CreateSessionWatcher());
        TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
    }
}