/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intentos;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Manu
 */
public class Intentos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        
        Intentos it = new Intentos();   
        
        Scanner sc = new Scanner(System.in);
        System.out.println("indique si quiere logearse(1), dar de alta(2) o dar de baja(3): \n");
        int menu = sc.nextInt();
       
       
        if(menu == 1){
            System.out.println("usted ha seleccionado logearse \n");
            System.out.println("introduzca ahora su dni: \n");
            
            Scanner logeador = new Scanner(System.in);
            String dni = logeador.nextLine();
            
            it.leeDatos(dni);
        }else{
            if(menu == 2){
                 
                 System.out.println("usted ha seleccionado dar de alta \n");
                 System.out.println("escriba ahora su registro en el siguiente orden, Dni, nombre, correo, telefono y contraseña: \n");     
                 
                 Scanner sc_d = new Scanner(System.in);
                 Scanner sc_n = new Scanner(System.in);
                 Scanner sc_c = new Scanner(System.in);
                 Scanner sc_t = new Scanner(System.in);
                 Scanner sc_p = new Scanner(System.in);
                                 
                 String _dni = sc_d.nextLine();
                 String _nombre = sc_n.nextLine();
                 String _correo = sc_c.nextLine();
                 String _tlf = sc_t.nextLine();
                 String _pwd = sc_p.nextLine();

                 it.grabaRegistro(_dni,_nombre,_correo,_tlf, _pwd);

                 
            }else{ if(menu == 3){
                 System.out.println("usted ha seleccionado dar de baja \n");
                 System.out.println("escriba ahora su registro: \n");
                 
                 Scanner borrador = new Scanner(System.in);
                 int b_registro = borrador.nextInt();
                 
                 it.borraDatos(b_registro);
            }
        } 
        }
    }
    
    public void grabaRegistro(String dni, String nombre, String correo, String tlf, String pwd)throws IOException{
            java.io.File ff = new java.io.File("clientes.txt");
            java.io.RandomAccessFile aa = new java.io.RandomAccessFile(ff,"rw");
            int longitud = (int)ff.length();
                aa.seek(longitud);
                
                char _dni[] = new char[9];
                char _nombre[] = new char[20];
                char _correo[] = new char[30];
                char _tlf[] = new char[9];
                char _pwd[] = new char[15];
                
                //Transformamos los Strings de datos almacenados via scanner y posteriormente pasados al void registro a array via bucle for.                
                
                for(int i=0; i<dni.length(); i++){
                   _dni[i] = dni.charAt(i);
                }
                for(int i=0; i<nombre.length(); i++){
                   _nombre[i] = nombre.charAt(i);
                }
                for(int i=0; i<correo.length(); i++){
                   _correo[i] = correo.charAt(i);
                }
                for(int i=0; i<tlf.length(); i++){
                   _tlf[i] = tlf.charAt(i);
                }
                for(int i=0; i<pwd.length(); i++){
                   _pwd[i] = pwd.charAt(i);
                }
                
                // escribir datos del registro en el archivo aleatorio
                
                 
                for (int i=0; i < _dni.length ; i++){
                 aa.writeChar(_dni[i]);
                }
                for (int i=0; i < _nombre.length ; i++){
                 aa.writeChar(_nombre[i]);
                }
                for (int i=0; i < _correo.length ; i++){
                 aa.writeChar(_correo[i]);
                }
                for (int i=0; i < _tlf.length ; i++){
                 aa.writeChar(_tlf[i]);
                }
                for (int i=0; i < _pwd.length ; i++){
                 aa.writeChar(_pwd[i]);
                }
                longitud = (int)ff.length();
                System.out.println("tamaño del archivo: " + longitud);
    }
    
    public void leeDatos(String usuario)throws IOException{
        java.io.File ff = new java.io.File("clientes.txt");
        java.io.RandomAccessFile aa = new java.io.RandomAccessFile(ff,"rw");
        
        int verificador = 0;
        int contador = 0;
        
        int longitud = (int)ff.length();
        int longRegistro = 166;
        
        int registros = longitud/longRegistro;
        System.out.println("Ahora mismo tienes " + registros + " registros \n");
        
       
        
        for(int i=0; i<registros; i++){
            aa.seek(i * 166);
            for(int j=0; j<18; j+=2){
                if(aa.readChar() == usuario.charAt(contador)){
                    verificador++; 
                }
                aa.seek(aa.getFilePointer() + 2);
                if(contador !=9){
                    contador++;
                }
                 
            }
            System.out.println(aa.readChar());
         if(verificador == 9){
             
             System.out.println("Usted se ha logeado con exito, con el usuario numero " + (i+1) + ".");
             
             i = registros;
         }else{
             verificador = 0;
         }  
    }
    }
     public void borraDatos(int usuario)throws IOException{
        java.io.File ff = new java.io.File("clientes.txt");
        java.io.RandomAccessFile aa = new java.io.RandomAccessFile(ff,"rw");
         
        
        
        int longitud = (int)ff.length();
        int longRegistro = 166;
        
        int registros = longitud/longRegistro;
        System.out.println("Ahora mismo tienes " + registros + " registros \n");
         
        char registrosnb[] = new char[registros - 1]; 
        
         for(int i=0; i<registros; i++){
            aa.seek(i * 166);
            if(usuario != i){ 
                 for(int j=0; j<166; j+=2){
               
                    registrosnb[i] = aa.readChar();
                    aa.seek(aa.getFilePointer() + 2);
                }
            }   
    }
         
     }
}
