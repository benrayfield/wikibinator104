/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.impl;
import wikibinator104.*;

public class Simpleλ implements λ{
	
	//TODO a λ which is a Blob, from lazycl's Blob interface
	
	public final λ l, r;
	
	protected Compiled compiled;
	
	public Simpleλ(λ l, λ r){
		this.l = l;
		this.r = r;
	}

	public boolean a(){ return false; }

	public λ l(){ return l; }

	public λ r(){ return r; }

	public λ p(λ r){
		return new Simpleλ(this,r);
		//FIXME instant partial dedup, and lazy perfect dedup
	}

	public λ e(λ r){
		return e(Long.MAX_VALUE, r).fn; //FIXME what if .fn is null
	}

	public $λ e(long maxSpend, λ r){
		throw new RuntimeException("TODO call compiled.apply(this,r) but fixme where to put maxSpend? is BinaryOperator the wrong interface?");
	}

	public Compiled compiled(){
		return compiled;
	}

	public void setCompiled(Compiled c){
		this.compiled = compiled;
	}

}
