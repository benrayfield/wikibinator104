/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.impl;
import wikibinator104.spec.*;

public class ImportStatic{
	
	public static String toString(Object o){
		return o==null ? "null" : o.toString();
	}
	
	public static void lg(Object o){
		lg(toString(o));
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	/** call pair */
	public static λ cp(λ func, λ param){
		//FIXME use hashtable for instant partial dedup (and ids for lazy perfect dedup)
		return new Simpleλ(func, param);
	}
	
	public static λ cp(λ... list){
		λ x = list[0];
		for(int i=1; i<list.length; i++) x = cp(x,list[i]);
		return x;
	}
	
	public static void x(String throwMessage){
		throw new RuntimeException(throwMessage);
	}
	
	public static λ bootOp(λ... params){
		λ x = u;
		for(λ param : params) x = cp(x,param);
		return x;
	}
	
	/** the isLeafsByte() for parent whose 2 childs are funcsIsleafsByte and anything. */
	public static byte nextIsleafsByte(byte funcsIsleafsByte){
		return (byte)((funcsIsleafsByte<<1)|1);		
	}
	
	/*FIXME??? u and (u u) must both be clean, so clean ops can be defined with those in their prefix.
	but FIXME I want there to be only 1 dirtyWiki (first param of u is the dirty one, then rest of prefix leads to wiki).
	Should (u (u u)) be the dirty prefix?
	That would make (u (u u) u u u u u) be dirtyWiki, but (u "hello" u u u u u) is also a dirtyWiki,
	if we go by the params being leaf vs nonleaf.
	Or could define wiki as infinitelooping if its first param (of 7) is not 1 of u or uu.
	Or could define that (u u) is the prefix of dirty things but u and (u u) are both clean.*/
	
	
	/*wiki(1)
	isLeaf(1)
	l(1)
	r(1)
	t(2)
	fi(2)
	pair(3)
	typeval(3)
	s(3)
	curry(3)
	ax(4)*/

	
	/** the universal function aka the leaf which all paths in binary forest of call pairs lead to */
	public static final λ u = Leaf.instance;
	//There is no dirty u.
	
	public static final λ uu = u.p(u);
	//There is no dirty (u u).
	
	//There is no dirty u or dirty (u u), but anything else has 2 forms: clean and dirty,
	//except FIXME what if theres a clean thing or dirty thing in first param of u other than u vs uu.
	//I dont plan to put anything but u or uu in first param of u, but it will happen in dovetailing
	//and in just randomly calling funcs on eachother, so the cleanness vs dirtyness of it must be defined.
	//Probably this will simply be (isClean x) means is its first param u or is it equal to u.
	
	
	
	//lowercase op name is clean, like wiki. Capital op name is dirty, like Wiki.
	//u is clean.
	//(u u) is clean.
	//(u (u u)) is dirty.
	//(u anythingExceptUAndUU) is dirty.
	
	//FIXME reverse order and swap u with uu, in the following?
	
	public static final λ wiki    = bootOp(u,	u,	u,	u,	u,	u);
	public static final λ Wiki    = wiki.dirty();
	
	public static final λ isLeaf  = bootOp(u,	u,	u,	u,	u,	uu);
	public static final λ IsLeaf  = isLeaf.dirty();
	
	public static final λ l       = bootOp(u,	u,	u,	u,	uu,	u);
	public static final λ L       = l.dirty();
	
	public static final λ r       = bootOp(u,	u,	u,	u,	uu,	uu);
	public static final λ R       = r.dirty();
	
	public static final λ t       = bootOp(u,	u,	u,	uu,	u);
	public static final λ T       = t.dirty();
	
	public static final λ fi      = bootOp(u,	u,	u,	uu,	uu);
	public static final λ Fi      = fi.dirty();
	
	public static final λ pair    = bootOp(u,	u,	uu,	u);
	public static final λ Pair    = pair.dirty();
	
	public static final λ typeval = bootOp(u,	u,	uu,	uu);
	public static final λ Typeval = typeval.dirty();
	
	public static final λ s       = bootOp(u,	uu,	u,	u);
	public static final λ S       = s.dirty();
	
	public static final λ curry   = bootOp(u,	uu,	u,	uu);
	public static final λ Curry   = curry.dirty();
	
	public static final λ ax      = bootOp(u,	uu,	uu);
	public static final λ Ax      = ax.dirty();
	
	
	
	/** identityFunc */
	public static final λ i       = cp(fi,u);
	public static final λ I       = i.dirty();
	
	public static final λ callParamOnItself = cp(cp(s,i),i);
	
	public static λ t(λ x){ return cp(t,x); }
	
	/** TODO also create lazig, which is a λfunc.λparam.λignore.(func param).
	FIXME handle clean vs dirty.
	*/
	public static λ lazy(λ func, λ param){
		return cp(s,t(func),t(param));
	}
	
	public static final λ funcThatInfloopsForAllPossibleParams = lazy(callParamOnItself,callParamOnItself);
	
	/*
	public static λ normedOp_slow(byte isLeafsByte){
		int i = isLeafsByte&0xff;
		if(i == 0) x("Must have high 1 bit in binheapIndex: "+i);
		λ x = u;
		while(i != 1){
			boolean z = i&
		}
	}
	
	public static λ normedOp_slow(boolean... ){
	*
	
	public static λ normedOp_slow(boolean isClean, Op op){
		λ ret = isClean ? uu : u;
		int sum = 0;
		for(Op x : Op.values()){
			if(x == op) break;
			sum += 1<<x.params;
		}
	}*/

}
