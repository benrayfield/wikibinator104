package wikibinator104.impl;
import wikibinator104.spec.*;

public interface weakptr extends Comparable<weakptr>{
	
	/** unique, within the local computer, JVM, or other kind of namespace etc.
	If this is utcnano (number of nanoseconds since year 1970 or +1 to the last one whichever is bigger)
	then this can handle 1 billion objects per second until around year 2262 (no y2k-like bugs til then).
	*/
	public long localId();
	
	/** null if dont have the ptr anymore, such as wrapping a WeakReference<T> or Supplier<T> */
	public λ get();
	
	/** compare by long localId */
	public default int compareTo(weakptr o){
		return Long.compare(localId(), o.localId());
	}
	
	/** 
	public boolean equals(Object obj){
		if(!(obj instanceof ob)) return false;
		return localId()==((ob)obj).localId();
	}*/
	public boolean equals(Object obj);

}