package utils;

public class DB {
    //todo 改成自己数据库的配置
    public static final String DB_URL = "jdbc:mysql://localhost:3306/keshe?useSSL=false";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";


    /**
     * 数据库驱动
     */
    public static void toSql(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
