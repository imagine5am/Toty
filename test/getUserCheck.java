
import org.Toty.Commons.User;
import org.Toty.Server.Service.SignUpApproveService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author imagine5am
 */
public class getUserCheck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SignUpApproveService s=new SignUpApproveService();
        User a=s.getUser("shivam");
        System.out.println(a);
    }
    
}
