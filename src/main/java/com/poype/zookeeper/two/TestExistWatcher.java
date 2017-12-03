package com.poype.zookeeper.two;

import org.apache.zookeeper.*;
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
                existAsync();
            } else if(watchedEvent.getType() == Event.EventType.NodeCreated) { //节点创建
                System.out.println("监控到创建了一个节点");
                existAsync();
            } else if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("监控到了一个节点变化");
                existAsync();
            } else if(watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("监控到了一个节点被删除");
                existAsync();
            }
        }
    }

    private void existSync() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/poype_node2", true);
        System.out.println(stat);
    }

    private void existAsync() {
        zooKeeper.exists("/poype_node2", true, new AsyncCallback.StatCallback() {
            public void processResult(int resultCode, String path, Object ctx, Stat stat) {
                System.out.println(resultCode);
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(stat);
            }
        }, "异步查看一个节点是否存在");
    }
}
