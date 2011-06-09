/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17;

import mc548_g17.heuristic.Annealing;
import mc548_g17.heuristic.Solution;
import mc548_g17.instance.Instance;

/**
 *
 * @author virgilio
 */
public class Main {

    private Boolean testing;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Instance i = new Instance("instances");
        Solution c = new Solution();
        Solution n = new Solution();
        Solution b = new Solution();

        Annealing.solucaoInicial(i, c);
        Annealing.gerarVizinhanca(i, c, n);

        for (int k = 0; k < i.getStations().size(); k++) {
            //System.out.println(i.getStations().get(k).getStationCost());
        }

        for (int k = 0; k < i.getSpots().size(); k++){
            //System.out.println(i.getSpots().get(k).getStations().size());
        }
    }

    public Boolean getTesting() {
        return testing;
    }

    public void setTesting(Boolean testing) {
        this.testing = testing;
    }
}
