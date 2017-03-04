
import java.sql.*;


//------------ เขียนแบบ Embedded มี user เดียว ------------
public class EmDb {

    private String database;    //ตารางที่เก็บข้อมูล attribute
    private String driver;      //ตัวกลางที่ไว้ติดต่อกับ database ต่างๆ
    private String url;
    private Connection conn;
    private Statement stm;      //สร้าง command ไว้ execute
    private ResultSet rs;       //ผลจาก database ที่ execute แล้ว
    private String sql;         //select insert update ที่ส่งผ่าน statement (stm)

    public EmDb() {
        database = "emDb";
        driver = "org.apache.derby.jdbc.EmbeddedDriver";
        //create=true ถ้า ไม่มี database ชื่อ emDb ให้สร้าง ถ้ามีก็เปิดใช้งาน
        url = "jdbc:derby:" + database + ";create=true";
        try {
            //step 1 load driver
            Class.forName(driver).newInstance();

        } catch (ClassNotFoundException cnfe) {
            //ในกรณีหา driver ไม่เจอ
            System.out.println("Driver not foune");
            System.exit(-1);
        } catch (InstantiationException ie) {
        } catch (IllegalAccessException iae) {
        }
        
        try {
            //step 2 connect to database
            conn = DriverManager.getConnection(url, "", "");

        } catch (SQLException ex) {
            System.out.println("cannot connect to database");
            System.exit(-1);
        }
        
        try{
            //step 3 craete statment
            stm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("cannot create stament");
            System.exit(-1);
        }
        
        try{
            //step 4 create sql command and execute
            
/*          ใช้สร้าง table (comment เพราะสร้างไปแล้ว)
            sql = "CREATE TABLE person(name varchar(15),lastname varchar(15))";
            stm.execute(sql);
*/
            
/*          ใช้ insert ข้อมูล
            sql = "INSERT INTO person values('sombat','Jaidee')";
            stm.executeUpdate(sql);
*/
            
            
/*          ใช้แสดงข้อมูลใน table
            sql = "SELECT * FROM person";
            rs = stm.executeQuery(sql);
            while(rs.next()){
                //จะมี before first และ after last ทุกครั้ง
                //next() ครั้งแรกเพื่อมา record ที่ต้องการ
                //next() ครั้งที่สอง จะเจอ after last จึงหลุดจาก loop
                
                System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
            }
*/ 
            sql = "UPDATE person SET name = 'Somchart' WHERE name = 'Somchai'";
            stm.executeUpdate(sql);
            sql = "SELECT * FROM person";
            rs = stm.executeQuery(sql);
  
            
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
            }
            
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            System.exit(-1);
        }
        
        
    }

    public static void main(String[] args) {
        new EmDb();
    }
}
