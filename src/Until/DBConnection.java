/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Until;
 
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String Driver="com.mysql.jdbc.Driver";
    private static final String Url="jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
    private static final String User="root";
    private static final String Password="123456";
    private Connection conn=null;
    
    //进行数据库连接
    public DBConnection() throws Exception{
        try{
            //用反射加载数据库驱动
            Class.forName(Driver);
            this.conn=DriverManager.getConnection(Url,User,Password);
        }
        catch (Exception e) {
            throw e;
        }
    }
    //取得数据库的连接
    public Connection getConnection(){
        return this.conn;        
    }
    //关闭数据库
    public void close() throws Exception{
        if(this.conn!=null){
            try {
                this.conn.close();
                
            } catch (Exception e) {
                throw e;
            }
        }
    }
    
}