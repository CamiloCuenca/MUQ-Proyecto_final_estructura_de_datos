package Controller;

import Model.enums.Ciudades;
import Model.enums.Genero;
import Model.enums.Paises;
import Model.objetos.*;
import Model.utils.DataUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class Main extends Application {



    public void start(Stage primaryStage) {
        CoreMethod.mostrarLogin(primaryStage);

    }

    public static void main(String[] args) {
        //launch(args);
        //ejemplo de factura

        // Creo la factura y el cliente
        Direccion direccion = new Direccion(Paises.COLOMBIA, Ciudades.ARMENIA, 10, 10);
        Cliente cliente = new Cliente("Santiago", 20, Genero.HOMBRE, 1001361185, direccion);
        Direccion direccion2 = new Direccion(Paises.COLOMBIA, Ciudades.ARMENIA, 11, 10);
        Cliente cliente2 = new Cliente("Brandon", 85, Genero.HOMBRE, 1502994294, direccion2);
        Direccion direccion3 = new Direccion(Paises.COLOMBIA, Ciudades.ARMENIA, 12, 10);
        Cliente cliente3=new Cliente("Diego",90,Genero.HOMBRE,1382482349,direccion3);
        Direccion direccion4=new Direccion(Paises.COLOMBIA,Ciudades.ARMENIA,10,10);
        Cliente cliente4=new Cliente("Rogelia",35,Genero.MUJER,82818921,direccion4);

        ArrayList<Producto> productos = null;
        Date a = null;
        Factura f1=new Factura(productos,a,"02",cliente);
        Factura f2=new Factura(productos,a,"01",cliente2);
        Factura f3=new Factura(productos,a,"01",cliente3);
        Factura f4=new Factura(productos,a,"01",cliente4);

        PersonaPremio p1 = DataUtils.creadorPersonaPremio(f1,5);
        PersonaPremio p2=DataUtils.creadorPersonaPremio(f2,4);
        PersonaPremio p3=DataUtils.creadorPersonaPremio(f3,3);
        PersonaPremio p4=DataUtils.creadorPersonaPremio(f4,2);

        PriorityQueue<PersonaPremio>premioPriorityQueue=new PriorityQueue<>();
        premioPriorityQueue.add(p1);
        premioPriorityQueue.add(p4);
        premioPriorityQueue.add(p3);
        premioPriorityQueue.add(p2);

        PersonaPremio ob=premioPriorityQueue.poll();
        System.out.println(ob);
        PersonaPremio ob2=premioPriorityQueue.poll();
        System.out.println(ob2);
        PersonaPremio ob3=premioPriorityQueue.poll();
        System.out.println(ob3);
        PersonaPremio ob4=premioPriorityQueue.poll();
        System.out.println(ob4);

    }

}