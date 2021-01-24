/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.impl;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.spec.*;

public class InterpretedMode implements Evaler{
	
	public static final InterpretedMode instance = new InterpretedMode();
	
	public static final EvalerChain chain = new SimpleEvalerChain(instance);
	
	public $λ eval(long maxSpend, λ func, λ param){
		long gas = maxSpend;
		//FIXME subtract from gas, but before each subtract check if it would be nonnegative.
		//TODO should gas be static long (or static double) like I used in occamsfuncer?
		int isLeafsByte = nextIsleafsByte(func.isLeafsByte())&0xff;
		boolean isDirty = TODO; //FIXME get from isLeafsByte
		//FIXME just create callpair/cp if theres not enuf curries,
		//considering that Op.ax evals at 6 and 7 curries, and everything else only at 7.
		λ ret;
		Op op = null; //FIXME get this from isLeafsByte
		
		//TODO handle Op.ax with 5 or 6 curries here (Todo choose 5 or 6 that eval should do something)...
		//	Get its 3 params (ignoring its 4th, hasnt got it yet) and ret = call one on the other then call
		//a derived equals function on what that returns to compare it to the return value in ax's param
		//and if they dont equal then infloop else its halted and TODO cache that.
		//Or... consider the existence of the 6 param form to be that cache, and call it at 5 (of 7) params...
		//That seems the better way... todo that.
		
		/*Op.deepLazy(0)
		Op.wiki(1)
		Op.isleaf(1)
		Op.l(1)
		Op.r(1)
		Op.t(2)
		Op.fi(2)
		Op.curry(2)
		Op.cleancall(2)
		Op.s(3)
		Op.pair(3)
		Op.typeval(3)
		Op.ax(4)*/
		
		//TODO make sure none of this logic uses loops or recursion outside of calling eval recursively,
		//such as in Op.ax to check if the observed return value equals the correct return val,
		//use the derived equals function.
		//This logic needs to be ported to a lambda so it can be used in emulation, dovetailing, etc.
		
		switch(op){ //7 curries, normal eval
		case wiki:
			throw new RuntimeException("TODO (wiki x) == ret in (ax ret wiki x) if thats known and isDirty(isLeafsByte). (wiki x) infloops if isClean(isLeafsByte).");
		case isleaf:
			ret = param.a()?t:f; //FIXME cost 1 gas, do at top of this eval func.
		case l:
			ret = param.l(); //FIXME cost 1 gas, do at top of this eval func.
		case r:
			ret = param.r(); //FIXME cost 1 gas, do at top of this eval func.
		case t:
			ret = func.r(); //FIXME cost 1 gas, do at top of this eval func.
		case fi:
			ret = param; //FIXME cost 1 gas, do at top of this eval func.
		case curry:
			λ x = func.l().r();
			λ y = func.r();
			TODO choose order of linkedlist used in currying, and if want to reverse there vs reverse it in syntax,
			as in [a b c d] being [[[a b] c] d] vs [a [b [c d]]].
		case cleancall:
			TODO
		case s:
			λ x = func.l().r();
			λ y = func.r();
			ret = (x param (y param)); //FIXME use gas, the e(long,λ) func.
		case pair: case typeval:
			λ x = func.l().r();
			λ y = func.r();
			ret = (param x y); //FIXME use gas, the e(long,λ) func.
		case ax:
			infloop;
		}
		TODO
	}
}