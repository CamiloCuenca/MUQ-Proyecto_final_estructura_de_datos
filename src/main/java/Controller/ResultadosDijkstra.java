package Controller;

public class ResultadosDijkstra {

        private int[] distancias;
        private String[] rutas;

        public ResultadosDijkstra(int[] distancias, String[] rutas) {
            this.distancias = distancias;
            this.rutas = rutas;
        }

        public int[] getDistancias() {
            return distancias;
        }

        public String[] getRutas() {
            return rutas;
        }


}
