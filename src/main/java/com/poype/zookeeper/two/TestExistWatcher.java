package com.poype.zookeeper.two;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestExistWatcher implements Watcher {
    private ZooKeeper zooKeeper;

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    /*
     调用exist方法时如果指定watch参数为true的话，就会同时接收到NodeCreated、NodeDataChanged、NodeDeleted这三个事件
     */
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                try {
                    existSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(watchedEvent.getType() == Event.EventType.NodeCreated) { //节点创建
                System.out.println("监控到创建了一个节点");
                try {
                    existSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("监控到了一个节点变化");
                try {
                    existSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("监控到了一个节点被删除");
                try {
                    existSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void existSync() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/poype_node2", true);
        System.out.println(stat);
    }
}
