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

}
