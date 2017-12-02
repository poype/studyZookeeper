package com.poype.zookeeper.one;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CreateSessionWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { //判断是否已连接
            if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                try {
                    getDataSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("监控到节点发生了变化");
                try {
                    watchChildrenNode();  // 事件响应只能生效一次，所以如果还想继续监视子节点的列表变化，就要重新watch一次
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("监控到节点的数据发生变化");
                try {
                    getDataSync();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(watchedEvent.getState());
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

    // 同步获取一个节点的子节点
    private void getChildrenSync() throws KeeperException, InterruptedException {
        List<String> childrenNode = zooKeeper.getChildren("/",false);
        for(String child : childrenNode) {
            System.out.println(child);
        }
    }

    // 异步获取一个节点的子节点列表
    private void getChildrenAsync() {
        zooKeeper.getChildren("/", false, new AsyncCallback.Children2Callback() {
            public void processResult(int resultCode, String path, Object ctx, List<String> children, Stat stat) {
                System.out.println(resultCode);  // 结果状态码，0代表成功
                System.out.println(path);        // 获取哪个节点下面的子节点
                System.out.println(ctx);
                for(String child : children) {
                    System.out.println(child);
                }
                System.out.println(stat);
            }
        }, "获取/下面的子节点");
    }

    private void watchChildrenNode() throws KeeperException, InterruptedException {
        zooKeeper.getChildren("/", true);
    }

    // 同步获取节点的数据
    private void getDataSync() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        // getData的返回值是该节点的数据值，节点的状态信息会赋值给stat对象
        byte[] data = zooKeeper.getData("/node_1",true, stat);
        System.out.println(new String(data));
        System.out.println(stat);
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
