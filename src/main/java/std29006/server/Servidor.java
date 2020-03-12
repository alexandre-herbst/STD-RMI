package std29006.server;


import std29006.ContadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private static  String nomeServidor = "191.36.13.106";
    private static int porta = 12345;
    private  static final  String NOMEOBJDIST = "MeuContador";

    public static void main(String[] args) {
        try{

           if(args[0] != null){
               nomeServidor = args[0];
           }

           if(args[1] != null){
               porta =  Integer.parseInt(args[1]);
           }

           Contador c = new Contador();

           System.setProperty("java.rmi.server.hostname", nomeServidor);

           ContadorDistribuido stub = (ContadorDistribuido) UnicastRemoteObject.exportObject(c, 0);

            Registry registro = LocateRegistry.createRegistry(porta);

            registro.bind(NOMEOBJDIST, stub);

            System.out.println("Servidor pronto!\n");
            System.out.println("Pressione CRTL + C para encerrar...");



        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





}
