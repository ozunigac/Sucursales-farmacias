package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Isma
 */
public class Conexion {
    //recibir informacion del servidor
    private ObjectInputStream inObject;
    private BufferedReader in;
    //enviar consultas al servidor de la base de datos
    private PrintWriter out;
    private String server;
    private int puerto;
    private Socket socket;
    
    
    public Conexion(){
        try{
            //leemos los datos del archivo servers
            BufferedReader br = new BufferedReader(new FileReader("servers.txt"));
            //en este caso solo necesitamos el primer registro
            String[]datosArchivo = new String [10];
            //leemos la primera linea del archivo
            datosArchivo[0]=br.readLine();
            //cerramos el archivo
            br.close();
            //separamos el nombre para usar la direccion ip y el puerto
            datosArchivo=datosArchivo[0].split("=");
            //separamos la direccion y el puerto
            datosArchivo=datosArchivo[1].split(":");
            //agregamos el server de la sucursal
            server=datosArchivo[0];
            //y por que puerto nos conectaremos a el
            puerto=Integer.parseInt(datosArchivo[1]);
            //se cerrara la lectura del archivo
            
            //se iniciará la conexion al servidor de base de datos
            socket = new Socket(server, puerto);
            inObject = new ObjectInputStream(socket.getInputStream());
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Errror al conectar al servidor de la base de datos","ERROR",ERROR_MESSAGE);
        }
    }
    
    public String[][] consultaSelect(String consulta){
        String[][] resp;
        try {
            //enviar la consulta al servidor de la base de datos
            out.println(consulta);
            //recibe el objeto de la consulta
            resp = (String[][]) inObject.readObject();
            //imprimir
            imprimirMatriz(resp);
            //por si el objeto no regresa nada
            if (resp == null) {
                JOptionPane.showMessageDialog(null, "No se encontró nada","ERROR",ERROR_MESSAGE);
                return null;
            }
        } catch (Exception ex){
               return null;
        }
        return resp;
    }
    
    //mandar la consulta al servidor de la base de datos 
    public String consultaInsert(String consulta){
        String resp = "";
        try{
            out.println(consulta);
            resp=in.readLine();
            //por si el objeto no regresa nada
            if (resp.equalsIgnoreCase("Error u")||resp.equalsIgnoreCase("Error i")||resp.equalsIgnoreCase("Error d"))
                return resp;
            //En caso de marcar un error.
        } catch (Exception ex){
               return resp;
        }
        return "Exito";
    }
    
    
    //conexion temporal a otro servidor
    public String[][] ConexionTemp(String serverTemp, int puertoTemp,String consulta){
        String [][] respuesta;
        Socket socketTemp = null;
        try{
            InetAddress inet = InetAddress.getByName(serverTemp);
            if(!inet.isReachable(puertoTemp)){
                JOptionPane.showMessageDialog(null, "Error con la conexion del servidor","ERROR",ERROR_MESSAGE);
                return null;
            }
            //se iniciará la conexion al servidor de base de datos
            socketTemp = new Socket(serverTemp, puertoTemp);
            //creamos variable para retornar objetos
            ObjectInputStream retorno = new ObjectInputStream(socketTemp.getInputStream());
            //variable para enviar consulta al servidor
            PrintWriter envio = new PrintWriter(socketTemp.getOutputStream(), true);
            //enviamos la consulta
            envio.println(consulta);
            //recibimos la matriz de strings
            respuesta=(String[][]) retorno.readObject();
            imprimirMatriz(respuesta);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Errror al conectar a la base de datos externa","ERROR",ERROR_MESSAGE);
            return null;
        }
        return respuesta;
    }
    
    
    //-------------------------------------------------------------------------------
    //imprimir la matrizz que retorna
    public void imprimirMatriz(String[][] resp){
        //imprimimos el objeto en matriz
        for(int i=0;i<resp.length;i++){
            System.out.println(Arrays.toString(resp[i]));
        }   
    }
}