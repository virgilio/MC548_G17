/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc548_g17.instance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author virgilio
 */
public class InstanceReader {

    private int numberOfSpots;
    private int numberOfStations;
    private ArrayList<Station> stations;
    private ArrayList<Spot> spots;
    private BufferedReader instances;

    public InstanceReader(String filename) {
        int lineNumber = 0;
        this.stations = new ArrayList<Station>();
        this.spots = new ArrayList<Spot>();
        this.numberOfSpots = 0;
        this.numberOfStations = 0;
        try {
            this.instances = new BufferedReader(new FileReader(filename));

            String lineData;
            try {
                while ((lineData = this.instances.readLine()) != null) {
                    if (lineNumber == 0) {
                        this.numberOfSpots = Integer.parseInt(lineData.split(" ")[1]);
                        for(int i = 0; i < this.numberOfSpots; i++){
                            this.spots.add(new Spot(i));
                        }
                        lineNumber++;
                    } else if (lineNumber == 1) {
                        this.numberOfStations = Integer.parseInt(lineData.split(" ")[1]);
                        lineNumber++;
                    } else {
                        String[] lineFields = lineData.split(" ");
                        
                        Station st = new Station(lineFields[0], Double.parseDouble(lineFields[1]));
                        
                        for (int i = 2; i < lineFields.length; i++) {
                            int idx = Integer.parseInt(lineFields[i]) - 1;
                            st.getCoveredSpots().add(spots.get(idx));
                            spots.get(idx).getStations().add(st);
                        }
                        stations.add(st);
                    }
                }
                instances.close();
            } catch (IOException ex) {
                Logger.getLogger(InstanceReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstanceReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberOfStations() {
        return this.numberOfStations;
    }

    public int getNumberOfSpots() {
        return this.numberOfSpots;
    }

    public ArrayList<Station> getStationList() {
        return this.stations;
    }

    ArrayList<Spot> getSpots() {
        return this.spots;
    }
}
