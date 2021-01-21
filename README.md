# wikibinator104
Scalable gaming-low-lag p2p wiki of 1 editable universal function, thats a universal lambda and pattern calculus function of 7 params, with vararg, GPU optimizable, turing-complete-challenge-response, godel-quality-self-reference (wiki can call itself recursively, emulate itself, etc), safely sandboxed across millions of untrusted computers. (TODO)

For ages 0 to expert. It will teach you how itself works and to build fun games and useful tools and play and work together, from the basics of math counting on your fingers up to the most advanced AI and number crunching and scaleable systems deployed in realtime, and for millions of people to be able to do that together in p2p without anyone being above or below anyone else, just merges and forks in binary forest of immutable objects viewed any way you like as functions can be built to view functions in new ways. (TODO).

This is made of 11 opcodes you can see in the Op enum farther below, which each do a certain lambda or pattern calculus or godel-like thing. This is a redesign of https://github.com/benrayfield/occamsfuncer which is a similar function of 15 params and has a working interpreter in which I derived an equals function (of shape of binary forest of call pairs down to the universal function as the shared leaf) and its testcases include (equals equals equals)->T and it has the basics of lisp like cons car cdr. Its also using some of the code from https://github.com/benrayfield/wikibinator101 and https://github.com/benrayfield/axiomforest and will be compatible with axiomforest in the (axiomforestLeaf ((axiomforestLeaf((axiomforestLeaf axiomforestLeaf)(axiomforestLeaf axiomforestLeaf))) "text/plain" "wikibinator104")) aka (axiomforestLeaf "wikibinator104") namespace where any binary forest node thats halted is TruthValue.yes, thats not halted is TruthValue.no, or TruthValue.unknown, or if error that its both (never happens in the math, but may in practice by those who dont follow the protocol) TruthValue.bull, and wikibinator104 will not be able to see outside its own namespace in axiomforest and will see its namespace as its own leaf, below which it cant see, but it could emulate axiomforest so things in other systems in axiomforest could in theory be translated to it that way.

Wikibinator is simpler and more advanced than occamsfuncer (15 param universal function). It takes 7 params. The first chooses strict vs loose. In strict mode, you cant call the wiki since its nondeterministic what edits and forks people might do and merge (at gaming-low-lag automatically), so it evals to (S I I (S I I)) which is an infinite loop. All calls will halt by limiting its compute resources recursively, and this process is planned to improve by https://en.wikipedia.org/wiki/Dovetailing_(computer_science) in ax (wikibinator104.Op.ax) about ways to prove that a certain func called on a certain param returns a certain value and never has more than 1 unique return value then use Compiled.java instances as optimizations of that exponential number of calculations to optimize it down to constant time (like a few microseconds) which is what the VM code is doing anyways and it just needs dovetailing, in some cases, to describe all the possible things the VM might be doing, so it can use that derived lambda function as an emulator of itself, built inside itself, then hook an optimization into it so its actually using the VM in a deterministic safe way, so that it can deterministicly do every compute step (debugStepInto and debugStepOver, counting GPU calls and other optimizations as 1 atomic block of calculations) in bigO(1) so deterministicly will always halt but will not always be turing-complete as its just better precision of can give up and back out of a calculation thats taking too long or that you or AIs dislike for whatever reason, to put more effort into the kinds of calculations we like and build things together.

/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104;
import java.util.function.UnaryOperator;

/** binary forest node, defined ONLY by its forest shape, with no data in each node
except caches which can be derived from that shape.
A 7 param universal pattern combinator
(aka combinator, universal lambda function, and pattern calculus function)
whose first param is isDirty (leaf is clean, any nonleaf means dirty, which will be cached to
swap between clean vs dirty in bigo1), and leaf itself is clean.
The other 6 params are mostly the same as in earlier wikibinator versions
but will reorder some opcodes and put in
(axiomop ret param func) being halted if (func param)->ret
and (axiomop ret param func ignore) does infloop and that "ignore" param is the 7th of 7 params,
so unlike earlier wikibinator versions, it can infloop at the second last param,
which is similar to occamsfuncerV2's curry op which used its second last param for types that way.
As of 2021-1-21 github.com/benrayfield/occamsfuncer is version 3 but doesnt say what its version is,
and it doesnt have that infloop in curry behavior cuz its an axiom related thing thats hard to prove things about. 
*/
public interface λ extends UnaryOperator<λ>{
	
	//TODO copy designs from "wikibinator102Designing2021-1-21+"
	//	wikibinator104_usesTheDesignsIn(wikibinator102Designing2021-1-21+)WhichIsBadlyNamed
	
	/** isLeaf */
	public boolean a();
	
	/** func/L child, of the 2 childs in binary forest */
	public λ l();
	
	/** param/R child, of the 2 childs in binary forest */
	public λ r();
	
	/** callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public λ p(λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? */
	public λ e(λ r);
	
	/** Returns the same return as e(λ),
	if it has enough gas (compute resources such as time and memory and num of compiles),
	and the $λ.gas is how much of that maxSpend remains (was not spent).
	FIXME how should it say theres lots of gas left but it didnt spend that part cuz it knew there
	wasnt enough to finisht he calculation? Should $λ.fn be null?
	<br><br>
	Nondeterministic, normally called thru wiki opcode, which will
	have various nondeterministic opcodes inside it such as spend and (solve salt x) -> any y where (x y)->leaf,
	and will deterministicly fork 1 of 2 ways recursively a salt param (128 or 256 bits?)
	in most nondeterministic calls so it becomes very unlikely to ever repeat those calls
	since the same func and param must always give the same return or its return may not be known,
	and it needs to do those calls multiple times in the forest, temporarily (such as in .00001 second),
	cuz the S lambda (Op.s) is the main kind of controlFlow and (s x y z)->((x z)(y z)) aka (x z (y z)),
	and those (x z) and (y z) often do the same call multiple times using the <func param return> caches
	which will be in (Op.ax ret param func) or in optimizations of that in hashtables in the VM.
	*/
	public $λ e(long maxSpend, λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? 
	UnaryOperator<λ>, but I want the func names used very often to have short names like e(λ) instead of apply(λ).
	*/
	public default λ apply(λ r){
		return e(r);
	}
	
	public Compiled compiled();
	
	/** c.prev()==compiled() must be true so can turn on and off various optimizations
	per λ or group of λs sharing a Compiled, to test if it does the exact same thing either way.
	*/
	public void setCompiled(Compiled c);

}
