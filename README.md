# wikibinator104
Scalable gaming-low-lag p2p wiki of 1 editable universal function, thats a universal lambda and pattern calculus function of 7 params, with vararg, GPU optimizable, turing-complete-challenge-response, godel-quality-self-reference (wiki can call itself recursively, emulate itself, etc), safely sandboxed across millions of untrusted computers. (TODO)

This is a redesign of https://github.com/benrayfield/occamsfuncer which is a similar function of 15 params and has a working interpreter in which I derived an equals function (of shape of binary forest of call pairs down to the universal function as the shared leaf) and its testcases include (equals equals equals)->T and it has the basics of lisp like cons car cdr. Its also using some of the code from https://github.com/benrayfield/wikibinator101 and https://github.com/benrayfield/axiomforest and will be compatible with axiomforest in the (axiomforestLeaf ((axiomforestLeaf((axiomforestLeaf axiomforestLeaf)(axiomforestLeaf axiomforestLeaf))) "text/plain" "wikibinator104")) aka (axiomforestLeaf "wikibinator104") namespace where any binary forest node thats halted is TruthValue.yes, thats not halted is TruthValue.no, or TruthValue.unknown, or if error that its both (never happens in the math, but may in practice by those who dont follow the protocol) TruthValue.bull, and wikibinator104 will not be able to see outside its own namespace in axiomforest and will see its namespace as its own leaf, below which it cant see, but it could emulate axiomforest so things in other systems in axiomforest could in theory be translated to it that way.

Wikibinator is simpler and more advanced. It takes 7 params. The first chooses strict vs loose. In strict mode, you cant call the wiki since its nondeterministic what edits and forks people might do and merge (at gaming-low-lag automatically), so it evals to (S I I (S I I)) which is an infinite loop. All calls will halt by limiting its compute resources recursively, and this process is planned to improve by https://en.wikipedia.org/wiki/Dovetailing_(computer_science) in ax (wikibinator104.Op.ax) about ways to prove that a certain func called on a certain param returns a certain value and never has more than 1 unique return value then use Compiled.java instances as optimizations of that exponential number of calculations to optimize it down to constant time (like a few microseconds) which is what the VM code is doing anyways and it just needs dovetailing, in some cases, to describe all the possible things the VM might be doing, so it can use that derived lambda function as an emulator of itself, built inside itself, then hook an optimization into it so its actually using the VM in a deterministic safe way, so that it can deterministicly do every compute step (debugStepInto and debugStepOver, counting GPU calls and other optimizations as 1 atomic block of calculations) in bigO(1) so deterministicly will always halt but will not always be turing-complete as its just better precision of can give up and back out of a calculation thats taking too long or that you or AIs dislike for whatever reason, to put more effort into the kinds of calculations we like and build things together.

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


package wikibinator104;

public enum Op{
	
	/*FIXME move the wiki and ax ops so that theres only 1 possible isdirty form of them,
	so that they use u instead of (u u) in their opcodes, or at least that way for wiki
	since they cant both have all u's as their prefix. the wiki is the most important
	to be that way cuz its the only nondeterministic thing in the system.
	*/
	
	
	wiki(1),
	
	isLeaf(1),
	
	l(1),
	
	r(1),
	
	t(2),
	
	fi(2),
	
	pair(3),
	
	typeval(3), //same as pair except with the semantic like (typeval "image/jpeg" ...bytesOfJpg...)
	
	s(3),
	
	curry(3), //will have to derive secondLast func
	
	/** Theres a !isDirty and isDirty form of this. Theres nothing dirty about it, other than possibly its params.
	(ax ret param func) will eventually halt (and cache that) if (func param)->ret (use derived equals func).
	(ax ret param func ignore) does infloop, which completes the 7 params of the universal func.
	<br><br>
	Axiom op, such as (ax (leaf leaf) leaf) can only halt on params that (ax leaf leaf) cant halt on,
	(cuz same func and param can have at most 1 returnVal, but theres 2 in that case: leaf and (leaf leaf)),
	which together are similar to axiomforest's TruthValue.yes and TruthValue.no and together would TruthValue.bull,
	and if neither are observed then thats TruthValue.unknown. But in this software, theres only binary forest shape
	with no TruthValue or other data at nodes, except caches of what can be derived only from binary forest shape,
	but "can be mounted into axiomforest" (search for that) as explained in comment near end of this class.
	<br><br>
	This will normally be used with dovetailing of fntape to search all possible axioms provable in finite steps,
	and if that does not find the axiom you're requesting (by calling it on ret param func)
	then it infloops by not finding that or appears to infloop cuz it takes longer than the universe --> heatdeath,
	aka it calls (func param) then checks if what it returns (if it ever returns) equals ret,
	and the dovetailing would be implemented inside that func, in some cases,
	as axiomforest-like bloom-filter of accumulating true statements in (ax (leaf leaf) leaf)
	and accumulating false statements in (ax leaf leaf), and checking for overlap between those 2.
	That dovetailing, which will be derived at user level (see "fntape" in occamsfuncer readme),
	is just an abstract math description of what it must do and will actually be
	computed exponentially faster in many cases using Compiled.java manually written optimizations,
	and later deriving some of those optimizations automatically (to gpu, javassist, etc).
	Its easy to make something exponentially faster if you already know what you want it to do
	but it just happens to take exponential time and memory to write it in the form of dovetailing
	as a recognizer function of the the thing which a Compiled.java would compute procedurally forward,
	those things being the various axiom-like statements or their outputs like in earlier wikibinator versions
	where that got too complex and I redesigned it to only need this one ax op with exponential optimizations.
	*/
	ax(4);
	
	
	
	/** is the first param leaf,
	considering there may be 0..6 params while halted, and it evals on 6th (ax only) or 7th param.
	*
	isDirty(1), //TODO derive at user level and optimize using Compiled.java
	*/
	
	//asClean(1), //TODO derive at user level and optimize using Compiled.java
	
	//asDirty(1), //TODO derive at user level and optimize using Compiled.java
	
	//cleanCall(2), //TODO derive at user level and optimize using Compiled.java
	
	//secondLast(1), //TODO derive at user level and optimize using Compiled.java
	
	public final int params;
	
	private Op(int params){
		this.params = params;
	}
	
	//FIXME need to get rid of some ops so it fits.
	public static void main(String[] args){
		int sum = 0;
		for(Op op : Op.values()){
			sum += 1<<op.params;
		}
		sum *= 2; //cuz first of 7 params chooses !isDirty (leaf) vs isDirty (anything except leaf)
		System.out.println("sum="+Integer.toBinaryString(sum));
		if(sum > 1<<7) throw new RuntimeException(
			"Opcodes dont fit in 7 params. 0..7 curries each being leaf vs nonleaf fits in a byte,"
			+" as a bitstring size 0..7 then a high 1 bit, aka the byte opcode"
			+" used in an earlier version of SimpleFn.interpretedMode's switch statement.");
	}
	
	/*
	Can be mounted into axiomforest.
	
	TODO hook into axiomforest at (a/axiomforestLeaf "wikibinator104"),
	of course using ((a a) a) as 1 and (a (a a)) as 0 from which bytes and bytestrings can be made
	like (((0 1)(1 1))((1 0)(0 0))) is the byte 01111000, and ((a a)(a a)) as identityFunc,
	and (a identityFunc) as axiomforestTypeval and (a 1) as Yes and (a 0) as No
	and (a "wikibinator104") would be (a (axiomforestTypeval "text/plain" "wikibinator104"))
	while the first param of typeval is either not a cbt or is interpreted as utf8 string,
	but other strings or pics videos pdfs or content of any type uses axiomforestTypeval,
	which is the standard way to use axiomforest between multiple systems, in theory, BUT
	wikibinator will not be able to see anything outside that namespace
	and will use (axiomforestLeaf "wikibinator104") as u/wikibinator104Leaf.
	Other systems built in axiomforest, such as maybe a wikibinator105 someday,
	or conwaysgameoflife experiments, etc, or tools built specificly for combining things
	inside the axiomforest, might be able to combine these parts that otherwise
	cant see outside themself, though you could emulate axiomforest in wikibinator104
	using pairs etc.
	
	A wikibinatorNode is a binary forest node without any data at it
	(other than caches derivable from that binary forest shape),
	but not all possible binary forest shapes are allowed cuz only those which are halted are allowed,
	and when viewed in axiomforest, TruthValue.yes means is halted, TruthValue.no means is not halted,
	and TruthValue.unknown means dont know if its halted or not (which may take infinite time/memory to know),
	and TruthValue.bull works as usual. They all work as usual.
	There will be some duplication of the YES and NO in
	(wikibinatorOp.ax (wikibinatorLeaf wikibinatorLeaf) wikibinatorLeaf x)
	and (wikibinatorOp.ax wikibinatorLeaf wikibinatorLeaf x) will never both exist for any x.
	Other first params of wikibinatorOp are allowed too, but those 2 can emulate all the others in axiomforest way.
	*/

}
