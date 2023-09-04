package com.espol.gofundme.Aplicacion;

import com.espol.gofundme.Aplicacion.InterfazUsuario;
import com.espol.gofundme.Aplicacion.Conexion;
public class Sistema {
    
    private InterfazUsuario intefazUserOBJ;
  
    public Sistema() {
        
    }
    
    
    public static void main(String[] args){
        
	Conexion miConexion = new Conexion("jdbc:mysql://localhost:3306/Crowfounding?serverTimezone=UTC","root","1234");//Magnolia182*
	InterfazUsuario intefazUserOBJ = new InterfazUsuario(miConexion);
        intefazUserOBJ.MostrarInterfaz();
		
    }
}
