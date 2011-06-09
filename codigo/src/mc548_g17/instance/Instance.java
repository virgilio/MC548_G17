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
public class Instance {
    private int numberOfSpots;
    private int numberOfStations;
    private ArrayList<Station> stations;
    private ArrayList<Spot> spots;

    public Instance(String file) {
        InstanceReader ir = new InstanceReader(file);
        this.numberOfSpots = ir.getNumberOfSpots();
        this.numberOfStations = ir.getNumberOfStations();
        this.stations  = ir.getStationList();
        this.spots = ir.getSpots();
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public void setNumberOfSpots(int numberSpots) {
        this.numberOfSpots = numberSpots;
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public void setNumberOfStations(int numberStations) {
        this.numberOfStations = numberStations;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public ArrayList<Spot> getSpots() {
        return spots;
    }

    public void setSpots(ArrayList<Spot> spots) {
        this.spots = spots;
    }

}
