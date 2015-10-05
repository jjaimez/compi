/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Clase abstracta para definicion de location
 */

package ir.ast;

public abstract class Location extends Expression {
	protected String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
        
        @Override
        public String toString(){
            return id;
        }


}
