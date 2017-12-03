package com.poype.zookeeper.three;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class TestSetDataWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                setDataAsync();
            }
        }
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    private void setDataSync() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData("/poype_node2", "poype5211314".getBytes(), 1);
        System.out.println(stat);
    }

    private void setDataAsync() {
        zooKeeper.setData("/poype_node2", "poype5211314".getBytes(), 3, new AsyncCallback.StatCallback() {
            public void processResult(int resultCode, String path, Object ctx, Stat stat) {
                System.out.println(resultCode);
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(stat.getVersion()); // 获取数据节点版本号
            }
        }, "异步设置一个节点的数据");
    }
}
