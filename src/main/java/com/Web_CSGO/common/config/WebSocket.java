package com.Web_CSGO.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 连接人数
     */
    public static int onlineNumber = 0;

    /**
     * 以用户名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    /**
     * 会话
     */
    private Session session;

    /**
     * 用户名
     */
    private String username;

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        logger.info("现在来连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
        logger.info("有新连接加入！当前在线人数" + onlineNumber);
        try {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 1);//1表示上线，2表示下线，3表示在线名单，4表示普通消息
            map1.put("username", username);
            //给所有人发送通知，上线了
            sendMessageAll(JSON.toJSONString(map1));

            //把自己添加到map中
            clients.put(username, this);
            //给自己发送消息，告知都有谁在线
            Map<String, Object> map2 = new HashMap<>();
            map2.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (Exception e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发送了错误" + error.getMessage());
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(username);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("messageType", 2);
            map.put("onlineUsers", clients.keySet());
            map.put("username", username);
            sendMessageAll(JSON.toJSONString(map));
        } catch (Exception e) {
            logger.info(username + "下线时通知所有人发生错误");
        }
        logger.info("有连接关闭！当前在线人数" + onlineNumber);
    }

    /**
     * 接收客户端的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("来自客户端消息：" + message + "客户端的id：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String formUsername = jsonObject.getString("username");
            String toUsername = jsonObject.getString("to");
            //如果不是发给所有人，那就是发给某个人
            Map<String, Object> map = new HashMap<>();
            map.put("messageType", 4);
            map.put("textMessage", textMessage);
            map.put("fromusername", formUsername);
            if (toUsername.equals("All")) {
                map.put("tousername", "所有人");
                sendMessageAll(JSON.toJSONString(map));
            } else {
                map.put("tousername", toUsername);
                sendMessageTo(JSON.toJSONString(map), toUsername);
            }
        } catch (Exception e) {
            logger.info("发生错误");
        }
    }

    /**
     * 发送给所有人
     *
     * @param message
     */
    public void sendMessageAll(String message) {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageTo(String message, String toUsername) {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(toUsername)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public static synchronized int getOnlineNumber() {
        return onlineNumber;
    }
}
