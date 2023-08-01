import InterfazUsuario;

public class Sistema {
    
    private InterfazUsuario intefazUserOBJ;
  
    public Sistema() {
        
    }
    
    
    public static void main(String[] args){
        
		Conexion miConexion = new Conexion("jdbc:mysql://localhost:3306/MiProyectoDB","root","Magnolia182*");
		InterfazUsuario intefazUserOBJ = new InterfazUsuario(miConexion);
        intefazUserOBJ.MostrarInterfaz();
		
    }
}
