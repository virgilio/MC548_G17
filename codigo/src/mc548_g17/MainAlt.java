/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17;

import java.util.ArrayList;
import java.util.HashMap;
import mc548_g17.heuristic.Annealing;
import mc548_g17.heuristic.Solution;
import mc548_g17.instance.Instance;
import mc548_g17.instance.Station;

/**
 *
 * @author Virgilio
 */
public class MainAlt {

    public static int lim1 = 50;
    public static int lim2 = 150;
    public static double temp = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<Long, Double> currCustos = new HashMap<Long, Double>();
        HashMap<Long, Double> bestCustos = new HashMap<Long, Double>();
        Instance inst = new Instance("instances");
        Solution curr = new Solution(inst.getNumberOfSpots());
        Solution neig = new Solution(inst.getNumberOfSpots());
        Solution best = new Solution(inst.getNumberOfSpots());
        long initTime, currTime;

        int L2 = 0, L1 = 0, n = 0, times = 0;
        double delta, T = temp, k = 1, alfa = 0.99;

        initTime = System.currentTimeMillis();
        Annealing.solucaoInicial(inst, curr);
        best = curr.mclone();
        currTime = System.currentTimeMillis();
        while (((currTime - initTime) < 60000)) {
            while (L1 < lim1) {
                while (L2 < lim2) {
                    neig = new Solution(inst.getNumberOfSpots());
                    Annealing.gerarVizinhanca(inst, curr, neig);
                    delta = neig.getCusto() - curr.getCusto();
                    if (delta < 0) {
                        curr = neig.mclone();
                        currCustos.put(System.currentTimeMillis(), curr.getCusto());
                        if (curr.getCusto() < best.getCusto()) {
                            best = curr.mclone();
                            bestCustos.put(System.currentTimeMillis(), best.getCusto());
                        }
                    } else if (Annealing.aceitarSolucao(delta, T, k)) {
                        curr = neig.mclone();
                        currCustos.put(System.currentTimeMillis(), curr.getCusto());
                    }
                    L2++;
                }
                L2 = 0;
                T = alfa * T;
                L1++;
            }
            n++;
            times++;
            L1 = 0;
            alfa = alfa * 0.99;
            T = temp * (10 ^ n);
            currTime = System.currentTimeMillis();
        }
        System.out.println("Numero de Execucoes: " + times);
        System.out.println("Valor: " + best.getCusto());
        System.out.println("Total: " + best.getStationSet().size());
        for (Station s : best.getStationSet()) {
            System.out.println(s.getStationId());
        }
        for(Long key : currCustos.keySet()){
            System.out.println(key + "," + currCustos.get(key));
        }

        System.out.println("----------------------------------------");

        for(Long key : bestCustos.keySet()){
            System.out.println(key + "," + bestCustos.get(key));
        }
    }
}
