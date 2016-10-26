/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri_check_file;

/**
 *
 * @author james
 */
public class RI_check_file {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "";
        for (String s : args) {
            input +=" "+ s;
        }
       
        try {
            FileChecker path = new FileChecker(input);
            path.checkpath();
        //    path.getpath();
            System.out.println("Success");

        } catch (Exception e) {
            System.out.println("expected 2 parameters; path_to_file/directory checksum");
        }
    }
    
}
