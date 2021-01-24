package wikibinator104.impl;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.spec.λ;

/** a cache entry of (func param)->return and a mutable long garbcolOrder for least recently used garbcol.
the Op.ax form of this is (ax ret func param), but theres 2 ax (clean ax and dirty Ax),
same as every other opcode, and if any of ret or func or param are dirty then must use dirty Ax.
*/
public class ReturnFuncParamCache{
	
	public final λ ret, func, param;
	
	public final int hash;
	
	private static final int saltA = Rand.strongRand.nextInt();
	
	public ReturnFuncParamCache(λ ret, λ func, λ param){
		this.ret = ret;
		this.func = func;
		this.param = param;
		hash = func.hashCode()+saltA*param.hashCode();
	}
	
	/** utcnano time (or a simple counter) of when this cache entry was last used,
	and garbcol in order of least recently used.
	
	Before changing this, remove it from whatever sorting datastruct (such as a minheap or mutable treemap)
	its sorted by this.
	*
	protected long lastUsed;
	*/
	protected long garbcolOrder;
	
	public void touch(){
		FIXME remove from minheap before changing that, then add it back.
		FIXME things that cant be garbcoled dont go in the minheap,
			or they do but their garbcolOrder makes them never be the min?
		garbcolOrder = Time.timeId();
	}
	
	TODO equals and hashcode by just func and param?
			
	public λ reflect(){
		boolean dirty = func.isDirty()||param.isDirty(); //ret can only be dirty if func.isDirty(),
		//but even if func is clean, param may still be dirty
		//(is truncated to clean before func uses it, by func starting to use it).
		return cp(dirty?Ax:ax, ret, func, param);
	}
	
	public int hashCode(){
		return hash;
	}
	
	public boolean equals(Object obj){
		//if(obj == this) return true;
		if(!(obj instanceof ReturnFuncParamCache)) return false;
		ReturnFuncParamCache o = (ReturnFuncParamCache)obj;
		return func==o.func && param==o.param;
	}	
	

}
