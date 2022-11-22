package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread 
{
    ServerSocket server = null;
    Socket client;
    String messaggioDaClient;
    BufferedReader inDalCliente;
    DataOutputStream outVersoClient;
    Messaggio listaRichieste;
    Messaggio listaBiglietti;
    Messaggio venduti;
    boolean chiusura = true;
    ObjectMapper objectMapper = new ObjectMapper();


    public ServerThread(Socket s){
        client = s;
    }

    public void run(){      //Viene affidata la comunicazione al Thread 

        try{

            inDalCliente = new BufferedReader(new InputStreamReader (client.getInputStream()));     //Inizializzazione periferica per lettura messaggi dal client 
            outVersoClient = new DataOutputStream(client.getOutputStream());        //Inizializzazione periferica per inviare al client 

                System.out.println("Inoltro della lista degli acquisti");       //Messaggio per informare dell'inoltro della lista acquisti
                outVersoClient.writeBytes(objectMapper.writeValue(new File("listaVendite.json"), venduti));       //Invio al client della lista dei biglietti che è riuscito ad acquistare serializzato

                System.out.println("Grazie per aver usufruito del servizio");       //Saluto da parte del server e chiusura comunicazioni
            }

            if(chiusura = false){
                client.close();     //Chiusura del sockete e della comunicazione
            }
            
        }
        catch(Exception e){     //Errore
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
