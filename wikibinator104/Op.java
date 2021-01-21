package wikibinator104;

public enum Op{
	
	/*FIXME move the wiki and ax ops so that theres only 1 possible isdirty form of them,
	so that they use u instead of (u u) in their opcodes, or at least that way for wiki
	since they cant both have all u's as their prefix. the wiki is the most important
	to be that way cuz its the only nondeterministic thing in the system.
	*/
	
	
	/** if strict this is (S I I (S I I)), else is loose and (wiki x) == ret in (ax ret x wiki) */ 
	wiki(1),
	
	/** (isLeaf x) is t or f depending if x is the leaf which all paths in the binary forest of call pairs lead to
	aka the wikibinator104 universal function itself.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	isLeaf(1),
	
	/** (l x) is left child of x in the binary forest of call pairs.
	Not the same as lispCar since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	l(1),
	
	/** (r x) is left child of x in the binary forest of call pairs.
	Not the same as lispCdr since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	r(1),
	
	/** λy.λz.y aka true. (pair b c true) is b. Is the K lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	t(2),
	
	/** λy.λz.z aka false aka f. (fi λ) is identityFunc aka λz.z. (pair b c false) is c. */
	fi(2),
	
	/** λx.λy.λz.zxy. Is the church-pair lambda and lispCons. */
	pair(3),
	
	/** λx.λy.λz.zxy, same as pair except with the semantic like (typeval "image/jpeg" ...bytesOfJpg...).
	Other funcs can see the difference between typeval and pair using isLeaf, l, r, and a derived equals function.
	*/
	typeval(3),
	
	/** λx.λy.λz.xz(yz) aka ((xz)(yz)). Is the S lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	s(3),
	
	curry(3), //will have to derive secondLast func
	
	/** λret.λparam.λfunc.(λret.λparam.λfunc.(λret.λparam.λfunc.(...))) is halted if (func param)->ret,
	else evals to (S I I (S I I)) aka an infinite loop. 1 more param and it does...
	λret.λparam.λfunc.λignore.(S I I (S I I)) aka an infinite loop, to complete the 7 params of the universal func,
	but we dont normally call it all the way to λignore, just use the first 3.
	<br><br> 
	Theres a !isDirty and isDirty form of this. Theres nothing dirty about it, other than possibly its params.
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
