/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mc548_g17.instance;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ra036470
 */
public class Spot {
    int spotNumber;
    ArrayList<Station> stations;

    public Spot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.stations = new ArrayList<Station>();
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stationIds) {
        this.stations = stationIds;
    }

    public Station randomStation() {
        return stations.get((new Random()).nextInt(stations.size()));
    }
}
