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

    /**
     * <h3>Sobre</h3>
     * InstanceReader(String filename)
     * A partir do arquivo dado como entrada para o problema, é criado um objeto
     * da classe Instance que conterá os dados fornecidos pela entrada:
     *      int numberOfSpots,
     *      int numberOfStations,
     *      ArrayList<Station> stations,
     *      ArrayList<Spot> spots e
     *      Um leitor para o arquivo: BufferedReader instances;
     *
     * O algoritmo lê linha por linha do arquivo e cria os devidos objetos:
     * <ul>
     * <li>Linha 0: Número n de pontos a serem cobertos. É atribuído valor n para o
     * atributo numberOfSpots e são criados os n pontos do vetor spots;</li>
     * <li>Linha 1: Número de estações m. É atribuído tal valor ao atributo
     * numberOfStations.</li>
     * </ul>
     * A partir da linha 1: Dados de cada estação: identificador, custo e 
     * pontos cobertos. Para cada linha lida é criado um objeto do tipo Station
     * com o identificador sendo a primeira informação fornecida na linha e
     * custo a segunda informação. Cada informação lida nessa linha a partir de
     * então representa o identificador de um ponto a ser coberto pela estação.
     * 
     * Para tratar tal fato, é adicionado o ponto do identificador à lista
     * coveredSpots da estação lida e adicionada a estação à lista de estações
     * de cobertura daquele ponto stations
     * 
     * A estação criada é adicionada à lista de estações da instância.
     *
     *
     *
     * @param filename
     */

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
                System.exit(-2);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstanceReader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-2);
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
