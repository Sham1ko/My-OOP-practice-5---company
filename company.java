import java.sql.*;
import java.util.Scanner;
public class company extends options{
//Connecting with the second class "options"
    public static void main(String[] args) {
        //Using Scanner to choose Option in Console Menu
        Scanner scanner=new Scanner(System.in);
        Statement statement;
        //Connecting to my external DBMS - PostgreSQL
        connection=get_connection();
        /*
        * Creating three tables to my company
        * First table I used for the all employees in company
        * There we have Five columns with their ID,Name,Surname,Position_ID and Project_ID
        * But at the beginning every Employee have "null" in Project_ID column
        * Because I did this Java Console Application as Constructor
        *
        * Second table was used to positions in company
        * There are data about position_ID,position's name and salary for definite position
        * In company we have 6 positions(Including 1 CEO)
        *
        * Third table was used to Projects in company
        * There are data for Project_ID and its name
        * Here in company I did 4 projects
        * I have chosen 4 different names of Top Popular Social Medias
        */
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

        /*
        * HERE I INSERT DATA TO TABLES IN DBMS
        * AND IF YOU HAVE ALREADY INSERTED DATA SO IT WILL OUTPUT EXCEPTION*/
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
        //I have inserted 20 members in table "company"
        //I used Mockaroo.com to generate this data
        try {
            String query= """
                    insert into company (id, name, surname, position_id) values (1, 'Zholdasbek', 'Shamshyrak', 1);
                    insert into company (id, name, surname, position_id) values (2, 'Silvan', 'Verdie', 2);
                    insert into company (id, name, surname, position_id) values (3, 'Roley', 'Kiehne', 3);
                    insert into company (id, name, surname, position_id) values (4, 'Trisha', 'Gomar', 4);
                    insert into company (id, name, surname, position_id) values (5, 'Fonz', 'Lander', 5);
                    insert into company (id, name, surname, position_id) values (6, 'Chancey', 'Faltin', 6);
                    insert into company (id, name, surname, position_id) values (7, 'Benedetta', 'Muat', 2);
                    insert into company (id, name, surname, position_id) values (8, 'Flemming', 'Filpi', 2);
                    insert into company (id, name, surname, position_id) values (9, 'Viki', 'Clutterham', 3);
                    insert into company (id, name, surname, position_id) values (10, 'Hasty', 'Roath', 1);
                    insert into company (id, name, surname, position_id) values (11, 'Benyamin', 'Ackwood', 5);
                    insert into company (id, name, surname, position_id) values (12, 'Saunder', 'Slyne', 6);
                    insert into company (id, name, surname, position_id) values (13, 'June', 'Mochan', 4);
                    insert into company (id, name, surname, position_id) values (14, 'Lena', 'Treleaven', 2);
                    insert into company (id, name, surname, position_id) values (15, 'Hesther', 'Mateja', 3);
                    insert into company (id, name, surname, position_id) values (16, 'Lance', 'Mablestone', 4);
                    insert into company (id, name, surname, position_id) values (17, 'Katharine', 'Manilow', 5);
                    insert into company (id, name, surname, position_id) values (18, 'Cad', 'Derrett', 6);
                    insert into company (id, name, surname, position_id) values (19, 'Lorant', 'Stollenwerck', 4);
                    insert into company (id, name, surname, position_id) values (20, 'Kaile', 'Okker', 3);
                    """;
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted in table 'company'!");

        } catch (Exception e){
            System.out.println("You have already inserted data in table 'Company'");
        }

        while (true){
            /*
            * Here starts Console Menu of my application*/
            System.out.println("\nChoose your option:");
            System.out.println("1.Show all projects");
            System.out.println("2.Choose project");
            int n = scanner.nextInt();
            //Here I used method to output all Projects
            if (n==1){
                projectNameOutput();
            }
            //Here I did that if you selected this option you will be in the next page
            //You will have option to exit
            if (n==2){
                System.out.println("Enter id of project:");
                int projectId=scanner.nextInt();
                try {
                    Statement firstStatement;
                    firstStatement=connection.createStatement();
                    ResultSet rs = firstStatement.executeQuery("select * from project where project_id="+projectId);
                    rs.next();
                    System.out.println("You have selected "+rs.getString("project"));
                }catch (Exception e){
                    System.out.println("Invalid value");
                }
                while (true){
                    //Here I printed out options that users can choose
                    System.out.println("""
                            OPTIONS:
                            1.Show current members of this project
                            2.Show available members to this project
                            3.Select members to this project
                            4.Show total cost of this project
                            5.Delete member from project
                            6.Exit to the main menu
                            """);
                    //Scanner to choose second page's option
                    int secondOption= scanner.nextInt();
                    if (secondOption==1){
                        System.out.println("CURRENT MEMBERS");
                        projectMembersCurrent(projectId);
                    }
                    if (secondOption==2){
                        System.out.println("AVAILABLE MEMBERS");
                        projectMembersAvailable();
                    }
                    if (secondOption==3){
                        System.out.println("How many members do you want to add?");
                        int numMembers = scanner.nextInt();
                        while (numMembers!=0){
                            System.out.println("Enter id of member that you want to add to this project:");
                            int memberId=scanner.nextInt();
                            projectMembersSelect(memberId,projectId);
                            numMembers--;
                        }
                    }
                    if (secondOption==4){
                        ProjectCost(projectId);
                    }
                    if (secondOption==5){
                        System.out.println("Enter ID of member:");
                        int memberID=scanner.nextInt();
                        delete(memberID);
                    }
                    //Option to exit this page
                    if (secondOption==6){
                        break;
                    }
                }
            }
        }
    }
}