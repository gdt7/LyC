package lyc.compiler.model;

public class SymbolTableStruct {

	private Object nombre;
	private String tipoDato;
	private Object valor;
	private int longitud;
	
	public SymbolTableStruct(Object nombre,String tipoDato,Object valor,int longitud) {
		this.nombre = nombre;
		this.tipoDato = tipoDato;
		this.valor = valor;
		this.longitud = longitud;
	}
	
	public Object getNombre() {
		return nombre;
	}
	public void setNombre(Object nombre) {
		this.nombre = nombre;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	
}
