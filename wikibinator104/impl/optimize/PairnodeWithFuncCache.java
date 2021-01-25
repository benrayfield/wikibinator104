package wikibinator104.impl.optimize;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.impl.Abstractλ;
import wikibinator104.impl.ImportStatic;
import wikibinator104.spec.$λ;
import wikibinator104.spec.Blob;
import wikibinator104.spec.λ;

/** same as Pairnode plus a cache of the second last thing in [λ comment funcbody a b c ,,,λ].
That cache does not prevent garbcol of that funcbody, any more than normal call pairs of the linkedlist would,
since its already reachable from the linkedlist. Its the [...] kind of linkedlist instead of the <...> kind,
which differ in which end it starts at. [[a b] c] vs <a <b c>> aka <a [b c]>.
Currying uses []. Normal lisp uses <>.
<br><br>
TODO create an Evaler instance that looks for secondLast cache,
and ImportStatic.secondLast.setCompiled(Evaler),
and maybe create secondLast func in λ so dont have to cast that, and cuz its a calculation implied by Op.curry,
BUT also consider that all ops must finish in bigo1 other than if they're implemented in java stack etc
then they must create a lambda and call it instead of recursing on java stack OTHER THAN
they are allowed to recurse into Evaler.eval(long,λ,λ) (aka λ.e(long,λ) but anything else has to be bigo1,
so based on that, λ should NOT have a secondLast func but could still have a secondLastCacheElseNull func
that doesnt recurse and just returns it if it knows it already.
*/
public class PairnodeWithFuncCache extends Pairnode{
	
	public final λ secondLast;
	
	public PairnodeWithFuncCache(boolean isClean, λ lr, λ r){
		super(isClean, lr, r);
		TODO set secondLast. get it from lr.
	}
	
	//isSkip is same as in Pairnode

}
