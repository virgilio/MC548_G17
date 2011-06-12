/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17.heuristic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import mc548_g17.instance.Station;

/**
 *
 * @author ra036470
 */
public class Solution {

    private double custo;
    private ArrayList<Station> stationSet;
    private ArrayList<Integer> spotsCovering;

    public Solution(int numberOfSpots) {
        this.stationSet = new ArrayList<Station>();
        this.spotsCovering = new ArrayList<Integer>();
        for (int i = 0; i < numberOfSpots; i++) {
            this.spotsCovering.add(i, 0);
        }

        this.custo = 0;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public ArrayList<Station> getStationSet() {
        return stationSet;
    }

    public void setStationSet(ArrayList<Station> stationSet) {
        this.stationSet = stationSet;
    }

    public ArrayList<Integer> getSpotsCovering() {
        return spotsCovering;
    }

    public void setSpotsCovering(ArrayList<Integer> spotsCovering) {
        this.spotsCovering = spotsCovering;
    }

    int getRandomSpot() {
        return spotsCovering.get((new Random()).nextInt(spotsCovering.size()));
    }

    /**
     * Adicionando uma Esta&ccedil;&atilde;o
     *
     * Ao adicionar uma Esta&ccedil;&atilde;o, &eacute; necess&aacute;rio a adi&ccedil;&atilde;o
     * da mesma no ArrayList de Station, atualizar o ArrayList de cobertura
     * e por fim atualizar o custo total da solução
     *
     * @param station
     */
    public void addStation(Station station) {
        stationSet.add(station);
        for (int i = 0; i < station.getCoveredSpots().size(); i++) {
            int index = station.getCoveredSpots().get(i).getSpotNumber();
            spotsCovering.set(index, spotsCovering.get(index) + 1);
        }
        custo += station.getStationCost();
    }

    /**
     *
     * Clonando uma solu&ccedil;&atilde;o
     *
     * &Eacute; necess&aacute;rio lembrar que a clonagem em Java &eacute; feita de forma rasa, ou seja
     * n&atilde;o é feita c&oacute;pia dos valores. A nossa c&oacute;pia &eacute feita
     * na profundidade da Inst&acirc;ncia, pe&ccedil;a imut&aacute;vel na estrutura de dados usada
     *
     * @return Um clone profundo do objeto Solution
     *
     * @see Object clone
     *
     */

    public Solution mclone() {
        Solution s = new Solution(this.spotsCovering.size());
        s.getStationSet().addAll(stationSet);
        s.getSpotsCovering().addAll(spotsCovering);
        s.setCusto(custo);
        return s;
    }

    /**
     * Remove a esta&ccedil;&atilde;o em quest&atilde;o realizando opera&ccedil;&otilde;es
     * similares (inversas) &agrave; adi&ccedil;&atilde;o de objeto similar.
     *
     *
     * @param station
     *
     * @see Solution addStation
     */

    public void removeStation(Station station) {
        stationSet.remove(station);
        for (int i = 0; i < station.getCoveredSpots().size(); i++) {
            int index = station.getCoveredSpots().get(i).getSpotNumber();
            spotsCovering.set(index, spotsCovering.get(index) -1);
        }
        custo -= station.getStationCost();
    }
}
