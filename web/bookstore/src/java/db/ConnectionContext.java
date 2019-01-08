package db;

import java.sql.Connection;
//用ThreadLocal来保证连接的一致性
public class ConnectionContext {
    private ConnectionContext(){};
    //单例
    private static ConnectionContext instace=new ConnectionContext();
    public static ConnectionContext getInstance(){
        return instace;
    }

    private ThreadLocal<Connection> connectionThreadLocal=new ThreadLocal<>();

    public void bind(Connection connection){
        connectionThreadLocal.set(connection);
    }
    public Connection get(){
        return connectionThreadLocal.get();
    }

    public void remove(){
        connectionThreadLocal.remove();
    }
}
