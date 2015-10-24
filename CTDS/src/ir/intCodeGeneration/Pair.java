
package ir.intCodeGeneration;


public class Pair<T1,T2> {
	private T1 x;
	private T2 y;

	/* Pair(): Constructor of the class */	
	public Pair() {
		x = null;
		y = null;
	}

	/* Pair(Object, Object): Constructor of the class */	
	public Pair(T1 v1, T2 v2) {
		x = v1;
		y = v2;
	}

	/* ChangeFst: Updates the value of var. x */	
	public void changeFst(T1 v1) {
		x = v1;
	}

	/* ChangeSnd: Updates the value of var. y */		
	public void changeSnd(T2 v2) {
		y = v2;
	}

	/* fst: Returns the value of var. x */	
	public T1 fst() {
		return x;
	}

	/* snd: Returns the value of var. y */		
	public T2 snd() {
		return y;
	}
	
	public boolean equals(Pair<T1,T2> p2){
		return (this.x==p2.fst() && this.y==p2.snd());
	}
	
	public Pair<T1,T2> clone(){
		Pair<T1,T2> clone = new Pair<T1,T2>();
		clone.changeFst(this.fst());
		clone.changeSnd(this.snd());
		return clone;
	}
	
	public String toString(){
		return "("+ x.toString() +","+ y.toString() + ")"; 
	}
}