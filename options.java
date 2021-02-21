import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class options {
    /*
    * I made another class for the options and methods*/
    public static Connection get_connection() {
        /*
         * Connecting to my external DMBS (PostgreSQL) using Java
         */
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","shamiko");
            if (connection!=null){
                System.out.println("Connection OK");
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    public static Connection connection;
    //Method to kick Member from certain Project
    //Used Update Statement from SQL
    public static void delete(int id) {
        try {
            Statement statement;
            statement=connection.createStatement();
            statement.executeUpdate("update company set project_id=null where id="+id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Method to Output all Project's names in Company
    //Used Select Statement from SQL
    public static void projectNameOutput(){
        try {
            Statement statement;
            statement=connection.createStatement();
            System.out.println("Our company's projects:");
            for (int i=1;i<=4;i++){
                ResultSet rs = statement.executeQuery("select * from project where project_id="+i);
                rs.next();
                System.out.println(rs.getInt("project_id")+"."+rs.getString("project"));
            }
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    //Method to select Members to certain Project
    //Used Update statement from SQL
    public static void projectMembersSelect(int id,int project_id){
        try {
            Statement statement;
            statement=connection.createStatement();
            statement.executeUpdate("update company set project_id="+project_id+" where id="+id);
        }catch (Exception e){
            System.out.println("Invalid value");
        }
    }
    //Method to find all Available Members from Company that do not participate in any projects
    //Used Select statement from SQL
    public static void projectMembersAvailable(){
        try {
            Statement statement;
            statement=connection.createStatement();
            ResultSet rs = statement.executeQuery("select id,name,surname,position  from company join position on company.position_id=position.position_id where project_id is null");
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("surname")+" "+rs.getString("position"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Method to output Current Members of Project
    //Used "Select" from sql and Statement from Java
    public static void projectMembersCurrent(int project_id){
        try {
            Statement statement;
            statement=connection.createStatement();
            ResultSet rs = statement.executeQuery("select id,name,surname from company join project on company.project_id=project.project_id where company.project_id="+project_id);
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("surname"));
            }
        }catch (Exception e){
            System.out.println("There is no members in this project!");
        }
    }

    //Method to find TOTAL COST of one Project
    //I used "sum" operator from SQL language
    public static void ProjectCost(int project_id){
        try {
            Statement statement;
            statement=connection.createStatement();
            ResultSet rs = statement.executeQuery("select sum(salary) from position join company on position.position_id=company.position_id where project_id="+project_id);
            rs.next();
            System.out.println(rs.getInt("sum"));
        }catch (Exception e){
            System.out.println("Something went wrong :(");
        }
    }
}
