package wikibinator104.impl;

import java.lang.ref.WeakReference;

public class Cache{
	
	public static final class Entry{
		public final WeakReference<ReturnFuncParamCache> x;
		public long garbcolOrder;
	}
	
	public static ReturnFuncParamCache[] minheap = new ReturnFuncParamCache[1<<24];
	FIXME use WeakReferences inside ReturnFuncParamCache andOr here? Cuz dont want to prevent garbcol
	of the λs.
	
	/*
	TODO minheap of ReturnFuncParamCache instances,
	but FIXME how to make their ReturnFuncParamCache.garbcolOrder be Long.MAX_VALUE if they're not garbcolable?
	Or since ReturnFuncParamCache is not a λ then its actually ok to garbcol it,
	since no λ can have a ptr to it, but can have a ptr to the (Op.ax ret func param) form of it
	aka ReturnFuncParamCache.reflect().
	*/
	
	TODO represent this as 4 longs each, 3 of them long localIds of (not fully deduped) λs,
	and 1 of those garbcolOrder? andOr 1 as number of incoming ptrs? and where is large storage of blobs?
	and might also a need a ptr to its replacement such as if duplicates are found, choose one as norm
	and point the others at it to replace self with that.
	thats useful if wanted to store it in a file etc, or a long[] in memory, or combo of that.
	but for now should focus on efficient caching in memory.

}
