package com.poype.zookeeper.one;

import org.apache.zookeeper.*;

public class CreateSessionWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { //判断是否已连接
            createNodeAsync();
        }
    }

    // 同步创建一个节点
    private void createNodeSync() throws KeeperException, InterruptedException {
        String path = "/poype_node";
        String nodePath = zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodePath);
    }

    // 异步创建一个节点，由于是异步，所以没有返回值
    private void createNodeAsync() {
        String path = "/poype_node2";
        zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            public void processResult(int resultCode, String path, Object ctx, String name) {
                System.out.println(resultCode);
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(name);
            }
        }, "创建");
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
