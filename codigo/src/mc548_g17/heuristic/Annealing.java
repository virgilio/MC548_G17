/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17.heuristic;

import java.util.ArrayList;
import mc548_g17.instance.Instance;
import mc548_g17.instance.Spot;
import mc548_g17.instance.Station;

/**
 *
 * @author ra036470
 */
public class Annealing {

    /**
     * Solução inicial ( Instancia, Solução)
     *      Enquanto o mínimo do vetor de cobertura é 0
     *          - Selecione dentre os pontos de cobertura igual a zero
     *          o ponto que tem menos estações segundo a Instancia.
     *          - Selecione a Estação que cobre o maior número de pontos
     *          não-Cobertos.
     *          - Adicione esta estação e atualize a cobertura de pontos.
     *
     * @param inst Instancia do problema
     * @param s Apontador para solução que será usado como solução inicial
     *
     */
    public static void solucaoInicial(Instance inst, Solution s) {
        int k = 0;
        ArrayList<Integer> spotsCovering = s.getSpotsCovering();

        while (spotsCovering.contains(Integer.valueOf(0))) {
            int minStations = Integer.MAX_VALUE, maxCovering = 0, currSpot = -1, currStation = -1;
            for (int i = 0; i < spotsCovering.size(); i++) {
                if (spotsCovering.get(i).equals(0)
                        && inst.getSpots().get(i).getStations().size() <= minStations) {
                    currSpot = i;
                    minStations = inst.getSpots().get(i).getStations().size();
                }
            }

            Spot spot = inst.getSpots().get(currSpot);

            for (int i = 0; i < spot.getStations().size(); i++) {
                if (spot.getStations().get(i).getCoveredSpots().size() >= maxCovering) {
                    currStation = i;
                    maxCovering = spot.getStations().get(i).getCoveredSpots().size();

                }
            }

            Station station = spot.getStations().get(maxCovering);

            s.getStationSet().add(station);
            for (int i = 0; i < station.getCoveredSpots().size(); i++) {
                int index = station.getCoveredSpots().get(i).getSpotNumber();
                spotsCovering.set(index, spotsCovering.get(index) + 1);
            }

            k++;
        }
        System.out.println(Annealing.class.getName()
                + "::solucaoInicial: Numero de iteracoes: "
                + k
                + ", Complexidade: O(n^2)");
    }

    /**
     * Vizinho Aleatório (Instancia, Solução, Vizinho)
       Selecione um ponto aleatoriamente de Solução.
       Copie as restrições de Solução que não cobrem
       este ponto para Vizinho.

       enquanto minimo do vetor de cobertura de Vizinho é 0
               Selecione aleatoriamente um ponto não coberto de Vizinho
               Selecione aleatoriamente uma restrição para cobrir este ponto
               Adicione estação e atualize cobertura.

       Remoção de redundância(Solução)
     * @param i
     * @param s
     * @param v
     */

    public static void gerarVizinhanca(Instance i, Solution s, Solution v) {
        

    }
}
