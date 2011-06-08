/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17;

import mc548_g17.instance.Instance;

/**
 *
 * @author virgilio
 */
public class Main {

    private Boolean testing;

    public Boolean getTesting() {
        return testing;
    }

    public void setTesting(Boolean testing) {
        this.testing = testing;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Now doing...");
        Instance i = new Instance("instances");
        
        System.out.println(i.getNumberOfSpots());
    }

}
