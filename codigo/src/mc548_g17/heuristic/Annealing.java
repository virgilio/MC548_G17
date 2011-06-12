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
     * <h3>Sobre</h3>
     * 
     * A partir da inst&acirc;ncia dada para o problema inst e sendo s um apontador
     * para  a solu&ccedil;&atilde;o inicial, temos que, inicialmente, a solu&ccedil;&atilde;o &eacute; um conjunto
     * vazio de esta&ccedil;&otilde;es. Assim, o vetor de cobertura que indica o n&uacute;mero de
     * esta&ccedil;&otilde;es que cobrem cada ponto &eacute; um vetor de zeros (nenhuma esta&ccedil;&atilde;o
     * utilizada, nenhum ponto coberto).
     *
     * A partir de ent&atilde;o, iniciamos um loop que, enquanto o menor valor do vetor
     * de cobertura for 0, ou seja, enquanto houver pontos sem cobertura:
     * &eacute; selecionado um ponto dentre os n&atilde;o cobertos que tem o menor n&uacute;mero de
     * poss&iacute;veis esta&ccedil;&otilde;es para cobr&iacute;-lo. Dentre estas poss&iacute;veis esta&ccedil;&otilde;es desse
     * ponto, &eacute; selecionada a esta&ccedil;&atilde;o que cobre o maior n&uacute;mero de pontos n&atilde;o
     * cobertos;
     *
     * A esta&ccedil;&atilde;o selecionada &eacute; adicionada &agrave; solu&ccedil;&atilde;o e o vetor de cobertura
     * &eacute; atualizado automaticamente;
     *
     * Terminado o loop, ou seja, n&atilde;o h&aacute; mais pontos descobertos, tem-se a
     * solu&ccedil;&atilde;o inicial para algoritmo Simulated-Annealing.
     *
     * <h3>Pseudo C&oacute;digo</h3>
     * Solu&ccedil;&atilde;o inicial ( Instancia, Solu&ccedil;&atilde;o)
     *      Enquanto o m&iacute;nimo do vetor de cobertura &eacute; 0
     *          - Selecione dentre os pontos de cobertura igual a zero
     *          o ponto que tem menos esta&ccedil;&otilde;es segundo a Instancia.
     *          - Selecione a Esta&ccedil;&atilde;o que cobre o maior n&uacute;mero de pontos
     *          n&atilde;o-Cobertos.
     *          - Adicione esta esta&ccedil;&atilde;o e atualize a cobertura de pontos.
     *
     * @param inst Instancia do problema
     * @param s Apontador para solu&ccedil;&atilde;o que ser&aacute; usado como solu&ccedil;&atilde;o inicial
     *
     * @see Solution
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

            for (int i = 0; i < spot.getStations().size(); i++) { //TODO Pontos n&atilde;o cobertos
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
     * <h3>Sobre</h3>
     * Vizinho Aleat&oacute;rio
     * Dados a inst&acirc;ncia do problema e uma solu&ccedil;&atilde;o, escolhe-se um ponto
     * da solu&ccedil;&atilde;o aleatoriamente. O vizinho ser&aacute; todas as esta&ccedil;&otilde;es da solu&ccedil;&atilde;o
     * parcial que n&atilde;o cobrem este determinado ponto aleat&oacute;rio somado &agrave; esta&ccedil;&otilde;es
     * novas que voltam a cobrir esse ponto e os demais n&atilde;o cobertos.
     *
     * Ao remover as esta&ccedil;&otilde;es que cobrem este ponto, pode ser que outros pontos
     * tornem-se descobertos. Inicia-se ent&atilde;o o loop e enquanto existirem pontos
     * n&atilde;o cobertos na solu&ccedil;&atilde;o vizinha, ou seja, enquanto o menor valor do vetor
     * de cobertura do vizinho for 0 &eacute; selecionado aleatoriamente um ponto
     * dentre os n&atilde;o cobertos e &eacute; selecionada uma esta&ccedil;&atilde;o para cobrir tal ponto
     * entre as esta&ccedil;&otilde;es que o cobrem. A esta&ccedil;&atilde;o selecionada &eacute; adicionada e o
     * vetor de cobertura &eacute; atualizado automaticamente.
     *
     * Terminado o loop, &eacute; removida as poss&iacute;veis redund&acirc;ncias na solu&ccedil;&atilde;o do
     * vizinho
     *
     * <h3>Pseudo C&oacute;digo</h3>
     * Vizinho Aleat&oacute;rio (Instancia, Solu&ccedil;&atilde;o, Vizinho)
     *      - Selecione um ponto aleatoriamente de Solu&ccedil;&atilde;o.
     *      - Copie as esta&ccedil;&otilde;es de Solu&ccedil;&atilde;o que n&atilde;o cobrem este ponto para Vizinho.
     *      - enquanto minimo do vetor de cobertura de Vizinho &eacute; 0
     *          Selecione aleatoriamente um ponto n&atilde;o coberto de Vizinho
     *          Selecione aleatoriamente uma esta&ccedil;&atilde;o para cobrir este ponto
     *          Adicione esta&ccedil;&atilde;o e atualize cobertura.
     *      - Remo&ccedil;&atilde;o de redund&acirc;ncia(Solu&ccedil;&atilde;o)
     *
     * @param inst
     * @param curr
     * @param neig
     */
    public static void gerarVizinhanca2(Instance inst, Solution curr, Solution neig) {
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

        removerRedundancia(neig);
    }

    /**
     *
     * 
     *
     * Vizinho Aleat&oacute;rio (Instancia, Solu&ccedil;&atilde;o, Vizinho)
     *      - Selecione um ponto aleatoriamente de Solu&ccedil;&atilde;o.
     *      - Copie as esta&ccedil;&otilde;es de Solu&ccedil;&atilde;o que n&atilde;o cobrem este ponto para Vizinho.
     *      - enquanto minimo do vetor de cobertura de Vizinho &eacute; 0
     *          Selecione aleatoriamente um ponto n&atilde;o coberto de Vizinho
     *          Selecione aleatoriamente uma esta&ccedil;&atilde;o para cobrir este ponto
     *          Adicione esta&ccedil;&atilde;o e atualize cobertura.
     *      - Remo&ccedil;&atilde;o de redund&acirc;ncia(Solu&ccedil;&atilde;o)
     * 
     * @param inst
     * @param curr
     * @param neig
     */
    public static void gerarVizinhanca(Instance inst, Solution curr, Solution neig) {
        int spot;
        int trocas = 0;
        for (Station station : curr.getStationSet()) {
            if (Math.random() < 0.9) {
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
     *
     * <h3>Sobre</h3>
     * Remo&ccedil;&atilde;o de redund&acirc;ncias
     *
     * Tal algoritmo tem como objetivo otimizar a solu&ccedil;&atilde;o do vizinho, removendo
     * esta&ccedil;&otilde;es desnecess&aacute;rias. Para tal, verifica se h&aacute; esta&ccedil;&atilde;o cujos pontos
     * j&aacute; s&atilde;o coberto por outras esta&ccedil;&otilde;es da solu&ccedil;&atilde;o. Caso isso ocorra, tal
     * esta&ccedil;&atilde;o &eacute; removida da solu&ccedil;&atilde;o.
     *
     * Esta an&aacute;lise &eacute; feita consultando cada esta&ccedil;&atilde;o e verificando se h&aacute;
     * pontos cobertos por ela com valor no vetor de cobertura (spotsCovering)
     * igual a 1. Se houver ponto com cobertura igual a 1, significa que esta
     * esta&ccedil;&atilde;o &eacute; estritamente necess&aacute;ria para cobrir tal ponto.
     *
     * Se nenhum ponto coberto pela esta&ccedil;&atilde;o tem valor de cobertura igual a 1,
     * significa que tal esta&ccedil;&atilde;o &eacute; desnecess&aacute;ria e com sua remo&ccedil;&atilde;o ainda teremos
     * uma solu&ccedil;&atilde;o v&aacute;lida j&aacute; que tais pontos continuar&atilde;o tendo cobertura.
     *
     * <h3>Pseudo C&oacute;digo</h3>
     * Remo&ccedil;&atilde;o de redund&acirc;ncia (Solu&ccedil;&atilde;o)
     *       Para toda estacao da Solu&ccedil;&atilde;o
     *          Remova se n&atilde;o existe ponto com cobertura igual a 1
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
        return Math.exp(-(delta / (k * T))) > Math.random();
    }
}
