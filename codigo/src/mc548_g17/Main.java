/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17;

import mc548_g17.heuristic.Annealing;
import mc548_g17.heuristic.Solution;
import mc548_g17.instance.Instance;
import mc548_g17.instance.Station;

/**
 *
 * @author Virgilio
 */

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Instance inst = new Instance("instances");
        Solution curr = new Solution(inst.getNumberOfSpots());
        Solution neig = new Solution(inst.getNumberOfSpots());
        Solution best = new Solution(inst.getNumberOfSpots());
        int L2 = 0, L1 = 0;
        double delta, T = 100000000010.00, k = 1, alfa = 0.8;
        int prob = 0;
        
        Annealing.solucaoInicial(inst, curr);
        best = curr.mclone();
        while (L1 < 100) {
            while (L2 < 100) {
                neig = new Solution(inst.getNumberOfSpots());
                Annealing.gerarVizinhanca(inst, curr, neig);
                delta = neig.getCusto() - curr.getCusto();
                if (delta < 0) {
                    curr = neig.mclone();
                    if (curr.getCusto() < best.getCusto()){
                        best = curr.mclone();
                    }
                } else if (Annealing.aceitarSolucao(delta, T, k)){
                    prob++;
                    curr = neig.mclone();
                }
                L2++;
            }
            L2 =  0;
            T = alfa*T;
            L1++;
        }

        System.out.println("Probabilidade de aceitar: " + prob);

        System.out.println("Valor: " + best.getCusto());
        System.out.println("Total: " + best.getStationSet().size());
        /*for (Station s : best.getStationSet()){
            System.out.println(s.getStationId());
        }*/
    }
}
