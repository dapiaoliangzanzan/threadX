package com.threadx.communication.server.config;

import com.threadx.communication.common.DefaultMessageCommunicationConfig;
import com.threadx.communication.common.MessageCommunicationConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务端的配置项
 *
 * @author huangfukexing
 * @date 2023/4/7 13:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerConfig implements Serializable {
    private static final long serialVersionUID = -3332101060115635093L;
    /**
     * 启动配置项
     */
    private MessageCommunicationConfig messageCommunicationConfig = new DefaultMessageCommunicationConfig();

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口号
     */
    private Integer port;

    public ServerConfig(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public ServerConfig(Integer port) {
        this.port = port;
    }
}
