package com.poype.zookeeper.one;

import org.apache.zookeeper.*;

public class CreateSessionWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { //判断是否已连接
            try {
                createNodeSync();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 同步创建一个节点
    private void createNodeSync() throws KeeperException, InterruptedException {
        String path = "/poype_node";
        String nodePath = zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodePath);
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
