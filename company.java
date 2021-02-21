import java.sql.*;
import java.util.Scanner;

public class company {

    public static Connection get_connection() {
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

    private static Connection connection;


    public static void delete(int id) {
        try {
            Statement statement;
            statement=connection.createStatement();
            statement.executeUpdate("update company set project_id=null where id="+id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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



    public static void projectMembersSelect(int id,int project_id){
            try {
                Statement statement;
                statement=connection.createStatement();
                statement.executeUpdate("update company set project_id="+project_id+" where id="+id);
            }catch (Exception e){
                System.out.println("Invalid value");
            }
    }

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

    public static void main(String[] args) throws SQLException {

        Scanner obj=new Scanner(System.in);
        Statement statement;
        connection=get_connection();

        try {

            String query="create table position(position_id int primary key,position varchar,salary int);" +
                    "create table project(project_id int primary key,project varchar);"+
                    "create table company(id int primary key,name varchar,surname varchar,position_id int references position(position_id),project_id int references project(project_id));";

            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tables have been created!");

        } catch (Exception e){
            System.out.println("You have already created same tables.");
        }
        try {

            String query="insert into position(position_id,position,salary) values(1,'CEO',10000);" +
                    "insert into position(position_id,position,salary) values(2,'Product Manager',4000);" +
                    "insert into position(position_id,position,salary) values(3,'Engineer',5000);" +
                    "insert into position(position_id,position,salary) values(4,'Marketing',3500);" +
                    "insert into position(position_id,position,salary) values(5,'Design Manager',2500);" +
                    "insert into position(position_id,position,salary) values(6,'Finance Manager',4500);";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted in table 'Position' ");

        } catch (Exception e){
            System.out.println("You have already inserted data in table 'Position'");
        }

        try {
            String query="insert into project(project_id,project) values(1,'Telegram');" +
                    "insert into project(project_id,project) values(2,'Instagram');" +
                    "insert into project(project_id,project) values(3,'Whatsapp');" +
                    "insert into project(project_id,project) values(4,'TikTok');";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted in table 'Project'");

        } catch (Exception e){
            System.out.println("You have already inserted data in table 'Project'");
        }

        try {

            String query="insert into company (id, name, surname, position_id) values (1, 'Zholdasbek', 'Shamshyrak', 1);\n" +
                    "insert into company (id, name, surname, position_id) values (2, 'Silvan', 'Verdie', 2);\n" +
                    "insert into company (id, name, surname, position_id) values (3, 'Roley', 'Kiehne', 3);\n" +
                    "insert into company (id, name, surname, position_id) values (4, 'Trisha', 'Gomar', 4);\n" +
                    "insert into company (id, name, surname, position_id) values (5, 'Fonz', 'Lander', 5);\n" +
                    "insert into company (id, name, surname, position_id) values (6, 'Chancey', 'Faltin', 6);\n" +
                    "insert into company (id, name, surname, position_id) values (7, 'Benedetta', 'Muat', 2);\n" +
                    "insert into company (id, name, surname, position_id) values (8, 'Flemming', 'Filpi', 2);\n" +
                    "insert into company (id, name, surname, position_id) values (9, 'Viki', 'Clutterham', 3);\n" +
                    "insert into company (id, name, surname, position_id) values (10, 'Hasty', 'Roath', 1);\n" +
                    "insert into company (id, name, surname, position_id) values (11, 'Benyamin', 'Ackwood', 5);\n" +
                    "insert into company (id, name, surname, position_id) values (12, 'Saunder', 'Slyne', 6);\n" +
                    "insert into company (id, name, surname, position_id) values (13, 'June', 'Mochan', 4);\n" +
                    "insert into company (id, name, surname, position_id) values (14, 'Lena', 'Treleaven', 2);\n" +
                    "insert into company (id, name, surname, position_id) values (15, 'Hesther', 'Mateja', 3);\n" +
                    "insert into company (id, name, surname, position_id) values (16, 'Lance', 'Mablestone', 4);\n" +
                    "insert into company (id, name, surname, position_id) values (17, 'Katharine', 'Manilow', 5);\n" +
                    "insert into company (id, name, surname, position_id) values (18, 'Cad', 'Derrett', 6);\n" +
                    "insert into company (id, name, surname, position_id) values (19, 'Lorant', 'Stollenwerck', 4);\n" +
                    "insert into company (id, name, surname, position_id) values (20, 'Kaile', 'Okker', 3);\n";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted in table 'company'!");

        } catch (Exception e){
            System.out.println("You have already inserted data in table 'Company'");
        }

        while (true){

            System.out.println("\nChoose your option:");
            System.out.println("1.Show all projects");
            System.out.println("2.Choose project");
            int n = obj.nextInt();

            if (n==1){
                projectNameOutput();
            }

            if (n==2){
                System.out.println("Enter id of project:");
                int prid=obj.nextInt();
                try {
                    Statement statement1;
                    statement1=connection.createStatement();
                    ResultSet rs = statement1.executeQuery("select * from project where project_id="+prid);
                    rs.next();
                    System.out.println("You have selected "+rs.getString("project"));
                }catch (Exception e){
                    System.out.println("Invalid value");
                }
                while (true){
                    System.out.println("OPTIONS:");
                    System.out.println("1.Show current members of this project");
                    System.out.println("2.Show available members to this project");
                    System.out.println("3.Select members to this project");
                    System.out.println("4.Show total cost of this project");
                    System.out.println("5.Delete member from project");
                    System.out.println("6.Exit to the main menu");

                    int SecondOption= obj.nextInt();

                    if (SecondOption==1){
                        System.out.println("CURRENT MEMBERS");
                        projectMembersCurrent(prid);
                    }
                    if (SecondOption==2){
                        System.out.println("AVAILABLE MEMBERS");
                        projectMembersAvailable();
                    }
                    if (SecondOption==3){
                        System.out.println("How many members do you want to add?");
                        int num = obj.nextInt();
                        while (num!=0){
                            System.out.println("Enter id of member that you want to add to this project:");
                            int memberId=obj.nextInt();
                            projectMembersSelect(memberId,prid);
                            num--;
                        }
                    }
                    if (SecondOption==4){
                        ProjectCost(prid);
                    }
                    if (SecondOption==5){
                        System.out.println("Enter id of member:");
                        int memid=obj.nextInt();
                        delete(memid);
                    }
                    if (SecondOption==6){
                        break;
                    }
                }
            }
        }
    }
}
