import org.Toty.Server.Service.CreateDB;
import org.Toty.Server.Service.CreateTables;


/**
 *
 * @author Shivam Sood
 */
public class CreateDBTest {
    public static void main(String args[]){
        CreateDB a=new CreateDB();
        CreateTables b=new CreateTables();
        b.createTotyTables();
        b.createTotyAdminTables();
    }
}
