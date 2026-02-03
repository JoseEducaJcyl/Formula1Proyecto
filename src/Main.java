import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Piloto> pilotos = new ArrayList<>();
        cargarDatos(pilotos);
        aniadirPiloto(pilotos);
        mostrarDatos(pilotos);
        buscarPiloto(pilotos);
        guardarDatos(pilotos);
    }
    public static void cargarDatos(ArrayList<Piloto> pilotos) {
        try (BufferedReader reader = new BufferedReader(new
                FileReader("pilotos_f1.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(";");
                Piloto piloto = new Piloto(campos[0],campos[1], Integer.parseInt(campos[2]));
                pilotos.add(piloto);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " +
                    e.getMessage());
        }
    }

    public static void mostrarDatos(ArrayList<Piloto> pilotos) {
        for (Piloto piloto : pilotos) {
            System.out.println(piloto.getNombre());
            System.out.println(piloto.getEscuderia());
            System.out.println(piloto.getPuntos());
            System.out.println(" ");
        }
    }

    public static boolean comprobarPiloto(ArrayList<Piloto> pilotos, String piloto) {
        boolean encontrado = false;
        for (Piloto p : pilotos) {
            if (p.getNombre().equals(piloto)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public static void aniadirPiloto(ArrayList<Piloto> pilotos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nombre del piloto");
        String nombreP = sc.nextLine();
        if (comprobarPiloto(pilotos, nombreP)) {
            System.out.println("El piloto ya existe");
        }else{
            System.out.println("Escribe la escuderia del piloto");
            String escuderiaP = sc.nextLine();
            System.out.println("Escribe los puntos del piloto");
            int puntosP = sc.nextInt();
            pilotos.add(new Piloto(nombreP, escuderiaP, puntosP));
        }
    }

    public static void buscarPiloto(ArrayList<Piloto> pilotos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nombre del piloto");
        String nombreP = sc.nextLine();
        if (comprobarPiloto(pilotos, nombreP)) {
            System.out.println("El piloto " +  nombreP + " existe");
        }else {
            System.out.println("No existe el piloto");
        }
    }

    public static void guardarDatos(ArrayList<Piloto> pilotos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pilotos_f1_nuevo.txt"))) {
            for (Piloto piloto : pilotos) {
                writer.write(piloto.getNombre()+";"+piloto.getEscuderia()+";"+piloto.getPuntos());
                writer.newLine();
            }
        }catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo: " +
                    e.getMessage());
        }
    }

}