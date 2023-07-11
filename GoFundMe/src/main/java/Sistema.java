import InterfazTextual.InterfazUsuario;
public class Sistema {
    
    private InterfazUsuario intefazUserOBJ;
  
    public Sistema() {
        
    }
    
    
    public static void main(String[] args){
        
        System.out.println("Hola");
        InterfazUsuario intefazUserOBJ = new InterfazUsuario();
        intefazUserOBJ.MostrarInterfaz();
    
    }
}
