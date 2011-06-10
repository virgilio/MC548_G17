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
            
            s.addStation(station);
            k++;
        }
        System.out.println(Annealing.class.getName()
                + "::solucaoInicial: Numero de iteracoes: "
                + k
                + ", Complexidade: O(n^2)");
    }

    /**
     * Vizinho Aleatório (Instancia, Solução, Vizinho)
     *        Selecione um ponto aleatoriamente de Solução.
     *        Copie as estação de Solução que não cobrem este ponto para Vizinho.
     *        enquanto minimo do vetor de cobertura de Vizinho é 0
     *                Selecione aleatoriamente um ponto não coberto de Vizinho
     *                Selecione aleatoriamente uma restrição para cobrir este ponto
     *                Adicione estação e atualize cobertura.
     *        Remoção de redundância(Solução)
     * 
     * @param inst
     * @param curr
     * @param neig
     */
    public static void gerarVizinhanca(Instance inst, Solution curr, Solution neig) {
        int randomSpot = curr.getRandomSpot();
        Spot spot = inst.getSpots().get(randomSpot);
        ArrayList<Station> stations = curr.getStationSet();

        for (int i = 0; i < stations.size(); i++) {
            ArrayList<Spot> spots = stations.get(i).getCoveredSpots();
            if (!spots.contains(spot)) {
                neig.addStation(stations.get(i));
            }
        }
        
        ArrayList<Integer> spots = neig.getSpotsCovering();
        while ((randomSpot = spots.indexOf(Integer.valueOf(0))) != -1) {
            neig.addStation(inst.getSpots().get(randomSpot).randomStation());
        }
    }

    /**
     * 
     * @param neig
     */
    private void removerRedundancia (Solution neig){
        
    }

}
