package com.poype.zookeeper.acl;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TestAclWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { //判断是否已连接
            if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                setAcl();
            }
        }
    }

    private void setAcl() {
        try {
            ACL aclIp = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("ip","192.168.1.110"));
            // 这里用户名是poype，密码是123456
            String digestAuthStr = DigestAuthenticationProvider.generateDigest("poype:123456");
            ACL aclDigest = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest",digestAuthStr));
            ArrayList<ACL> acls = new ArrayList<ACL>();
            acls.add(aclIp);
            acls.add(aclDigest);
            // 创建节点时用acls作为权限信息
            String path = zooKeeper.create("/poype", "789".getBytes(), acls, CreateMode.PERSISTENT);
            System.out.println(path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}


