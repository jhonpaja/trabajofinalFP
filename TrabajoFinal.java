package FundamentosProgramacion;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class TrabajoFinal {

    public static boolean ejecutaETL(LinkedList<String> colaCiclica){

        try {
            Thread.sleep(10000);
            while(!colaCiclica.isEmpty()){
                String ciclicaETL = (String)colaCiclica.peek();
                System.out.println("La ciclica a procesar es = " + ciclicaETL);
                //--------------------
                colaCiclica.remove();
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean consultaLegado(String fechaHoy){
        ArrayList<String> ciclicasBD =  getCiclicasPendientes();
        System.out.println("Ciclicas pendientes de BD - SIN ORDENAR = " + String.valueOf(ciclicasBD));
        ciclicasBD = ordenamientoBurbuja(ciclicasBD);
        System.out.println("Ciclicas pendientes de BD - ORDENADAS = " + String.valueOf(ciclicasBD));

        String ciclicaPendiente =  ciclicasBD.get(0);

        if (ciclicaPendiente.compareTo(fechaHoy) == 0)
            return true;
        else
            return false;
    }

    public static ArrayList<String> ordenamientoBurbuja(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size() - 1; j++) {
                String cadenaj = data.get(j);
                String cadenaj1 = data.get(j + 1);
                if(cadenaj.compareTo(cadenaj1) > 0){
                    String temp = cadenaj;
                    data.set(j, data.get(j + 1));
                    data.set(j+1, temp);
                }
            }
        }
        return data;
    }

    public static void generarColaETL(String ciclica){
        LinkedList colaCiclicasETL = new LinkedList();
        colaCiclicasETL.push(ciclica);
        String ciclicaETL;

        System.out.println("La cola con las ciclicas a ejecutar = "+colaCiclicasETL);

        if (!colaCiclicasETL.isEmpty()){
            ejecutaETL(colaCiclicasETL);
        }

        System.out.println("La cola después del procesamiento = "+colaCiclicasETL);


    }

    public static ArrayList<String> getCiclicasPendientes(){
        ArrayList<String> ciclicas = new ArrayList<String>();
        ciclicas.add("2022-07-13");ciclicas.add("2022-07-15");ciclicas.add("2022-08-15");ciclicas.add("2022-09-15");ciclicas.add("2022-10-15");ciclicas.add("2022-11-15");ciclicas.add("2022-12-15");
        ciclicas.add("2022-07-17");ciclicas.add("2022-08-17");ciclicas.add("2022-09-17");ciclicas.add("2022-10-17");ciclicas.add("2022-11-17");ciclicas.add("2022-12-17");
        ciclicas.add("2022-07-18");ciclicas.add("2022-08-18");ciclicas.add("2022-09-18");ciclicas.add("2022-10-18");ciclicas.add("2022-11-18");ciclicas.add("2022-12-18");
        ciclicas.add("2022-07-23");ciclicas.add("2022-08-23");ciclicas.add("2022-09-23");ciclicas.add("2022-10-23");ciclicas.add("2022-11-23");ciclicas.add("2022-12-23");
        ciclicas.add("2022-07-31");ciclicas.add("2022-08-31");ciclicas.add("2022-09-30");ciclicas.add("2022-10-31");ciclicas.add("2022-11-30");ciclicas.add("2022-12-31");
        ciclicas.add("2022-08-01");ciclicas.add("2022-09-01");ciclicas.add("2022-10-01");ciclicas.add("2022-11-01");ciclicas.add("2022-12-01");
        ciclicas.add("2022-08-05");ciclicas.add("2022-09-05");ciclicas.add("2022-10-05");ciclicas.add("2022-11-05");ciclicas.add("2022-12-05");
        return ciclicas;
    }

    public static boolean validaDiaCiclicas(String fechaHoy){
        String[] diaCiclicas = {"01", "05", "13", "15", "17", "18", "23", "30", "31"};
        String diaHoy = fechaHoy.substring(8,10);
        boolean validacion = false;
       // diaHoy = "05";


        for (int i = 0; i < diaCiclicas.length; i++){
            if(diaHoy.equals(diaCiclicas[i])){
                validacion = true;
                System.out.println("El día " + diaHoy + " corresponde a una ciclica");
            }
        }
        return validacion;
    }

    public static void main(String[] args) {
        System.out.println("------------  INICIO ------------");
        boolean pendienteDeCarga = true;
        int limite = 5;
        int iteraciones = 0;

        String fechaHoy = String.valueOf(LocalDate.now());

        if(validaDiaCiclicas(fechaHoy)){
            while(pendienteDeCarga){
                pendienteDeCarga = consultaLegado(fechaHoy);

                if(iteraciones == limite) {
                    System.out.println("Se ha pasado el límite de validaciones");
                    break;
                }

                if(pendienteDeCarga){
                    System.out.println("Realiza Importación de recibos");
                    generarColaETL(fechaHoy);
                    break;
                }else{
                    System.out.println("No se encuentra la ciclica = "+ fechaHoy);
                }

                iteraciones++;
            }
        }
        System.out.println("------------  FIN ------------");
    }
}
