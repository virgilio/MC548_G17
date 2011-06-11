/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17.heuristic;

import java.util.ArrayList;
import java.util.Random;
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

            for (int i = 0; i < spot.getStations().size(); i++) { //TODO Pontos não cobertos
                if (spot.getStations().get(i).getCoveredSpots().size() >= maxCovering) {
                    currStation = i;
                    maxCovering = spot.getStations().get(i).getCoveredSpots().size();
                }
            }
            Station station = spot.getStations().get(currStation);

            s.addStation(station);
            k++;
        }
    }

    /**
     * Vizinho Aleatório (Instancia, Solução, Vizinho)
     *      - Selecione um ponto aleatoriamente de Solução.
     *      - Copie as estações de Solução que não cobrem este ponto para Vizinho.
     *      - enquanto minimo do vetor de cobertura de Vizinho é 0
     *          Selecione aleatoriamente um ponto não coberto de Vizinho
     *          Selecione aleatoriamente uma estação para cobrir este ponto
     *          Adicione estação e atualize cobertura.
     *      - Remoção de redundância(Solução)
     * 
     * @param inst
     * @param curr
     * @param neig
     */
    public static void gerarVizinhanca(Instance inst, Solution curr, Solution neig) {
        int spot;
        int trocas = 0;
        for (Station station : curr.getStationSet()){
            if(Math.random() < 0.9){
                neig.addStation(station);
                trocas++;
            }
        }
        ArrayList<Integer> spots = neig.getSpotsCovering();
        while ((spot = spots.indexOf(Integer.valueOf(0))) != -1) {
            neig.addStation(inst.getSpots().get(spot).randomStation());
        }

        removerRedundancia(neig);
    }

    /**
     * Remoção de redundância (Solução)
     *       Para toda estacao da Solução]
     *          Remova se não existe ponto com cobertura igual a 1
     * 
     * @param neig
     */
    private static void removerRedundancia(Solution neig) {
        ArrayList<Station> stations = neig.getStationSet();
        for (int i = 0; i < stations.size(); i++) {
            Boolean isRedundante = Boolean.TRUE;
            ArrayList<Spot> stationSpots = stations.get(i).getCoveredSpots();
            int k = 0;
            while (isRedundante && k < stationSpots.size()) {
                int index = stationSpots.get(k).getSpotNumber();
                if (neig.getSpotsCovering().get(index) == 1) {
                    isRedundante = Boolean.FALSE;
                }
                k++;
            }
            if (isRedundante) {
                neig.removeStation(stations.get(i));
            }
        }
    }

    public static Boolean aceitarSolucao(double delta, double T, double k) {
        return Math.exp(-(delta/(k*T))) > Math.random();
    }
}
