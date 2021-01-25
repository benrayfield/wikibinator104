package wikibinator104.impl.optimize;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.impl.Abstractλ;
import wikibinator104.spec.$λ;
import wikibinator104.spec.Blob;
import wikibinator104.spec.λ;

/** Optimization of (curry [λ comment funcBody ... x y ,,,,λ]), which is the main way to define vararg lambdas,
and ,,,,λ means 5 more params before it evals (funcBody [λ comment funcBody ... p0 p1 p2 p2 p3 p4 p5 p6]).
Each next call, theres 1 more thing in [...] and the ,,,,λ at its end is 1 less , .
,,,x means (t (t (t x))) which unary counts down for number of params left to curry.
This optimization stores [λ comment funcBody ... x y] and the int number of curries left.
If theres more than Integer.MAX_VALUE curries (which is allowed) then use normal call pairs instead,
since in theory the system will support as many curries as you have memory or harddrive for in small pieces
and can even split a single function call's curries into an unlimited number of computers,
such as if you have 2^50 parameters of some ridiculously long function call
or maybe such a function keeps adding 1 more curry each time it evals like a terminal window
waiting for the next input, but normally functions have < 20 curries.
Otherwise, it may become a security flaw to have a limit on an otherwise pure math function???
Similarly, if java stack fills up,
then TODO it should retreat to emulating stack on heap like occamsfuncer callquads do.
*/
public class Curnode extends Abstractλ{
	
	/** the λ form of Op.curry (aka ImportStatic.curry or ImportStatic.Curry depending on clean/dirty) is l() */
	public final boolean isClean;
	
	/** this is the linkedlist of params (including funcBody and comment). This is r().l(). */
	public final λ list;
	
	/** number of curries left. This as a unary number is r().r().
	To lazyeval this, share a pool of such unary numbers or at least those up to some constant like 1<<16. */
	public final int cur;
	
	/** r() is lazyEvaled. That lazyEval is not triggered in g(todo which ints) to get r().l() or r().r() */
	protected λ r;
	
	public Curnode(boolean isClean, λ list, int cur){
		this.isClean = isClean;
		this.list = list;
		this.cur = cur;
	}
	
	public boolean isSkip(int binheapIndex){
		copied this commentedoutcode from Pairnode, but todo adjust it for theres more skips in Curnode 
		/**
		if(binheapIndex==1) return false; //cant skip self
		if(binheapIndex==2) return true; //skip l()
		if(binheapIndex==3) return false; //dont skip r()
		*/
		TODO call isSkip recursively, using binheapIndex*2 for left and binheapIndex*2+1 for right,
		but only from the childs directly stored.
	}
	
	public λ l(){
		return isClean ? curry : Curry;
	}
	
	//Wherever the lazy code goes, TODO move this comment there...
	//FIXME see FIXME in cp func about making dedup work with this code (similar code in Curnode and Pairnode)

	public boolean a(){
		return false;
	}
	
	public λ g(int binheapIndex){
		throw new RuntimeException("TODO");
	}

	public λ g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public λ G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public $λ e(long maxSpend, λ r){
		throw new RuntimeException("TODO");
	}

	public byte opByte(){
		throw new RuntimeException("TODO");
	}

	public Blob blob(){
		throw new RuntimeException("TODO");
	}

}
