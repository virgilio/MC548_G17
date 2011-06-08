/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17.instance;

import java.util.ArrayList;

/**
 *
 * @author ra036470
 */
public class Station {

    private String stationId;
    private double stationCost;
    private ArrayList<Integer> coveredSpots;

    public Station(String stationId, Double stationCost) {
        this.stationId = stationId;
        this.stationCost = stationCost;
        this.coveredSpots = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getCoveredSpots() {
        return coveredSpots;
    }

    public void setCoveredSpots(ArrayList<Integer> coveredSpots) {
        this.coveredSpots = coveredSpots;
    }

    public double getStationCost() {
        return stationCost;
    }

    public void setStationCost(double stationCost) {
        this.stationCost = stationCost;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
