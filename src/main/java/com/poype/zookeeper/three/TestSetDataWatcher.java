package com.poype.zookeeper.three;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestSetDataWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                try {
                    setDataSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
}
