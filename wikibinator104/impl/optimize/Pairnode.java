package wikibinator104.impl.optimize;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.impl.Abstractλ;
import wikibinator104.impl.ImportStatic;
import wikibinator104.spec.$λ;
import wikibinator104.spec.Blob;
import wikibinator104.spec.λ;

/** optimization of (pair x y) aka ((pair x) y) that stores x and y and lazyEvals (pair x).
Can get x by lr() or g(5).
*/
public class Pairnode extends Abstractλ{
	
	public final boolean isClean;
	
	public final λ lr, r;
	
	/** lazyEval this */
	protected λ l;
	
	public Pairnode(boolean isClean, λ lr, λ r){
		this.isClean = isClean;
		this.lr = lr;
		this.r = r;
	}

	public boolean a(){
		return false;
	}
	
	public boolean isSkip(int binheapIndex){
		if(binheapIndex==1) return false; //cant skip self
		if(binheapIndex==2) return true; //skip l()
		if(binheapIndex==3) return false; //dont skip r()
		TODO call isSkip recursively, using binheapIndex*2 for left and binheapIndex*2+1 for right,
		but call it from lr and r and not from l.
	}
	
	public λ l(){
		//FIXME see FIXME in cp func about making dedup work with this code (similar code in Curnode and Pairnode)
		if(l == null) l = cp(isClean?pair:Pair, lr);
		return l;
	}
	
	public λ lr(){
		return lr;
	}
	
	public λ r(){
		return r;
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
