/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mc548_g17.heuristic;

import java.util.ArrayList;
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
        for(int i = 0; i < numberOfSpots; i++){
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
}
