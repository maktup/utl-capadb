package pe.com.capacitacion.bean;
 
/**
 * Auditoria
 * @author cguerra
 **/ 
 public class Auditoria{
	
	    private String ipApp;
	    private String nombreApp; 
	    private String usuarioApp; 
	    private String mensajeApp;
        
		public String getIpApp() {
			return ipApp;
		}
		
		public void setIpApp(String ipApp) {
			this.ipApp = ipApp;
		}
		
		public String getNombreApp() {
			return nombreApp;
		}
		
		public void setNombreApp(String nombreApp) {
			this.nombreApp = nombreApp;
		}
		
		public String getUsuarioApp() {
			return usuarioApp;
		}
		
		public void setUsuarioApp(String usuarioApp) {
			this.usuarioApp = usuarioApp;
		}
		
		public String getMensajeApp() {
			return mensajeApp;
		}
		
		public void setMensajeApp(String mensajeApp) {
			this.mensajeApp = mensajeApp;
		}
   
 }
