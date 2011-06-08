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

    public Instance(String file) {
        InstanceReader ir = new InstanceReader(file);
        this.numberOfSpots = ir.getNumberOfSpots();
        this.numberOfStations = ir.getNumberOfStations();
        this.stations  = ir.getStationList();
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
}
