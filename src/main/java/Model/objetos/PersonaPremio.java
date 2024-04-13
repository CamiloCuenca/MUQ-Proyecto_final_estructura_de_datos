package Model.objetos;

public class PersonaPremio implements Comparable<PersonaPremio>{
    private int prioridad;
    private int pais;
    private int secuencia;

    private Cliente cliente;

    private Producto premio;


    public PersonaPremio(int prioridad, int pais, int secuencia,Cliente cliente) {
        this.prioridad = prioridad;
        this.pais = pais;
        this.secuencia = secuencia;
        this.cliente=cliente;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public String toString() {
        return "PersonaPremio{" +
                "prioridad=" + prioridad +
                ", pais=" + pais +
                ", cliente=" + cliente +
                '}';
    }

    @Override
    public int compareTo(PersonaPremio o) {

        //Primero comparamos si es mujer, hombre o adulto mayor
        //El atributo prioridad es el que comparamos aca
        if (this.prioridad<o.getPrioridad()){
            return 1;
        }else if (this.prioridad>o.getPrioridad()){
            return -1;
        }else{
            //Segundo condicional si no se cumple y tienen el mismo sexo
            //se compara la prioridad por pais, si esta en conflicto o calamidad

            if(this.pais<o.getPais()){
                return 1;
            }else if(this.pais>o.getPais()){
                return -1;
            }else{

                if (this.secuencia>o.getSecuencia()){
                    return -1;
                }else if(this.secuencia<o.getSecuencia()){
                    return 1;
                }else{
                    return 0;
                }
            }
        }


    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
