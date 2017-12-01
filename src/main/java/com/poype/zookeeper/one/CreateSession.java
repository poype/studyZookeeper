package com.poype.zookeeper.one;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class CreateSession {

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.108:2181", 5000,
                new CreateSessionWatcher());
    }
}