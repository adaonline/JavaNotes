package dao.impl;

import dao.Dao;
import db.ConnectionContext;
import db.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class BaseDao<T> implements Dao<T> {
    private static QueryRunner queryRunner=new QueryRunner();
    @Override
    public long insert(String sql, Object... args) {
        long id=0;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            connection= ConnectionContext.getInstance().get();
            preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(args!=null){
                for(int i=0;i<args.length;i++){
                    preparedStatement.setObject(i+1,args[i]);
                }
            }
            preparedStatement.executeUpdate();
            //获取主键值
            resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getLong(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement);
        }

        return id;
    }

    @Override
    public void update(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = ConnectionContext.getInstance().get();
            queryRunner.update(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T query(String sql, Object... args) {


        return null;
    }

    @Override
    public List<T> queryForList(String sql, Object... args) {
        return null;
    }

    @Override
    public <V> V getSingleVal(String sql, Object... args) {
        return null;
    }

    @Override
    public void batch(String sql, Object[]... params) {

    }
}
