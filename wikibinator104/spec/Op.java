/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.spec;

public enum Op{
	
	/** Anything thats not halted is deepLazy,
	other than if its l() and r() are halted but it is not cuz r() is its 7th param,
	that uses the same op as r()'s op.
	This is a low level of lazy that does not exist in the wikibinator104 prototype
	but may in other implementations of wikibinator104.
	<br><br>
	Wikibinator104 is (TODO after work out details) a specific pure math function not a specific system.
	<br><br>
	Wikibinator104 can (in theory) be mounted into (axiomforestLeaf "wikibinator104"),
	using axiomforest's kind of strings for that,
	where wikibinator104Leaf is (axiomforestLeaf "wikibinator104") and wikibinator104
	cant see anything outside of (axiomforestLeaf "wikibinator104"),
	and calling it on anything outside that is axiomforest.TruthValue.no,
	so for it to see other systems it would have to emulate axiomforest such as using
	(pair cbt16_axiomforestHeader (pair axiomforestLeftChild axiomforestRightChild)) for example.
	*/
	deeplazy(0),
	
	//First param is λ (aka u aka leaf) for clean, or anything else (such as (λ λ)) for dirty.
	//
	//OLD...
	//
	//First param chooses isClean vs !isClean. In choosing opcodes, leaf is 0, and anything else is 1.
	//isClean = !isDirty.
	//Wiki is the first opcode, so dirtyWiki = (λ λ λ λ λ λ λ),
	//and (dirtyWiki x) = (λ λ λ λ λ λ λ x) which has 7 params so must eval,
	//such as (λ λ λ λ λ λ λ "hello")->"world" might be in the dirtyWiki.
	//A cleanWiki is (λ (λ λ) λ λ λ λ λ), but cleanWiki is practically unuseable
	//cuz clean means deterministic, so no wiki edits are allowed,
	//so the wiki is the function (S (T (S I I)) (T (S I I))) which infinite loops for all possible params,
	//so (λ (λ λ) λ λ λ λ λ "hello") -> (S I I (S I I)) aka never responds to the statement "hello".
	//
	//Similarly, if cleanS or cleanPair etc tries to create a dirty, it evals to (S I I (S I I)).
	//Dirty can create clean, but clean cant create dirty. If a call starts as clean, it ends as clean,
	//but if that was started by a dirty then when the clean is returned to the dirty,
	//it can continue as dirty which contains some clean parts (still dirty as a whole).
	//
	//Theres a clean/deterministic vs dirty/nondeterministic form of everything.
	//(aClean bClean) -> thing_that_clean.
	//(aDirty bDirty) -> thing_that_clean_or_dirty.
	//(aDirty bClean) -> thing_that_clean_or_dirty.
	//(aClean bDirty) -> (aClean (recursivelyChangeFirstParamsToClean bDirty)) -> (aClean bClean) -> thing_that_clean.
	
	
	/*FIXME move the wiki and ax ops so that theres only 1 possible isdirty form of them,
	so that they use u instead of (u u) in their opcodes, or at least that way for wiki
	since they cant both have all u's as their prefix. the wiki is the most important
	to be that way cuz its the only nondeterministic thing in the system.
	*/
	
	
	/** if strict this is (S I I (S I I)), else is loose and (wiki x) == ret in (ax ret wiki x).
	<br><br>
	You can effectively have unlimited number of wikis by using a linkedlist whose deepest part is the name of the wiki,
	and parts after that you use whatever you want, for example,
	and to fork one of those, use axioms (dovetailing derived in params of Op.ax, for example)
	to imply that if something exists in some parts of wiki then it exists (or translating it / migration / etc)
	in certain other parts, though convergence on a set of axioms is intentionally left nondeterministic
	so various groups of people and systems or combos of them or the whole world together,
	however they like to organize things, have not created a lambda contradiction (TruthValue.bull)
	by selecting from the space of all finite but otherwise turing complete ways to organize the wiki,
	where TruthValue.bull occurs when the same lambda call (func param) has more than 1 unique return value
	such as (wiki "hello")->"world" and (wiki "hello")->42 cant both exist,
	but (wiki ["testxyz435" "hello"])->"world" and (wiki ["monkeyspace" "hello"])->42 can exist together,
	or (wiki "testxyz435.hello") would also work, however you want to do it, just dont create lambda contradictions.
	Wiki is a pure math function and can call itself recursively, emulate itself, etc,
	such as (wiki "lazyCallWikiOnItself") -> {,wiki ,wiki} aka (s (t wiki) (t wiki))
	so if that exists (check Op.ax for any returnVal func param)
	then (wiki "lazyCallWikiOnItself" u) -> (wiki wiki),
	and there might be (wiki wiki)->"this is the return value of calling wiki on itself"
	so in that case (wiki "lazyCallWikiOnItself" u) -> "this is the return value of calling wiki on itself",
	or you could also use (s i i x) -> (x x) forall x, so (s i i wiki) -> (wiki wiki) -> whatever that returns.
	You could also make a function locally named testxyz435
	where (testxyz435 x) -> (wiki ["testxyz435" x]). Functions are useful for many possible things
	including convenient shortcuts or building virtual worlds so big and detailed
	you might mistake it for a remote interaction with the real world. You can put anything
	in the wiki as long as it doesnt create any lambda contradictions with the other contents
	and if you can get other people and computers to go along with it in them
	trying to not create lambda contradictions anywhere in the wiki considering its whole contents.
	The system will automatically look for such contradictions and automatically fork and merge
	in whatever ways people want it to as defined by functions they can create
	to help them understand other functions and create new useful functions.
	Everything can be automated and the automatic processes
	(built by people and AIs while using other automated processes)
	can choose between people andOr AIs at the time and context for whatever each does best,
	and if that doesnt work out, try other combos automatically.
	Everything gets connected to everything if thats what those parts want at the time and context.
	*/ 
	wiki(1),
	
	/** (isLeaf x) is t or f depending if x is the leaf which all paths in the binary forest of call pairs lead to
	aka the wikibinator104 universal function itself.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	isleaf(1),
	
	/** (l x) is left child of x in the binary forest of call pairs.
	Not the same as lispCar since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	l(1),
	
	/** (r x) is right child of x in the binary forest of call pairs.
	Not the same as lispCdr since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	r(1),
	
	//l and r differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** λy.λz.y aka true. (pair b c true) is b. Is the K lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	t(2),
	
	/** λy.λz.z aka false aka f. (fi λ) is identityFunc aka λz.z. (pair b c false) is c. */
	fi(2),
	
	//t and fi differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** UPDATE: the unaryNumber goes in linkedlist, and [a b c d] means [[[a b] c] d],
	and <a b c d> means [a [b [c d]]], and *x means (curry x), and ,,,x means (T (T (T x))).
	*[u "a fibonacci function ... comment..." funcBody u]
	*[u comment funcBody a b ,,,u]
	<br><br>
	OLD...
	λunaryNumber.λparamList.λnextParam.(...
	if (isLeaf unaryNumber)
	then (secondLastInLinkedlist paramList (pair nextParam paramList))
	else (curry (r unaryNumber) (pair nextParam paramsList))
	...)
	There is a way to write that in lambdas, like in OcfnUtil.equals() it uses an IF(condition,ifTrue,ifFalse) lambda etc,
	though occamsfuncer is still using java to call some of the lambdas on eachother to make lambdas,
	cuz the syntax is incomplete.
	*/
	curry(2), //will have to derive secondLast func
	//curry(3), //will have to derive secondLast func
	
	cleancall(2),
	
	//curry and cleanCall differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** λx.λy.λz.xz(yz) aka ((xz)(yz)). Is the S lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	s(3),
	
	/** λx.λy.λz.zxy. Is the church-pair lambda and lispCons. */
	pair(3),
	
	/** λx.λy.λz.zxy, same as pair except with the semantic like (typeval "image/jpeg" ...bytesOfJpg...).
	Other funcs can see the difference between typeval and pair using isLeaf, l, r, and a derived equals function.
	*/
	typeval(3),
	
	//pair and typeval differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** λret.λfunc.λparam.(λret.λfunc.λparam.(λret.λfunc.λparam.(...))) is halted if (func param)->ret,
	else evals to (S I I (S I I)) aka an infinite loop. 1 more param and it does...
	λret.λfunc.λparam.λignore.(S I I (S I I)) aka an infinite loop, to complete the 7 params of the universal func,
	but we dont normally call it all the way to λignore, just use the first 3.
	TODO explain λret.λfunc.λparam.(λret.λfunc.λparam.(λret.λfunc.λparam.(...))) differently
	cuz that way of writing it makes it appear that it takes 3 more params, instead of the 3 params already given.
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
	<br><br>
	OLD...
	Ok, updated comments (changed from λret.λparam.λfunc) for...
	should it be (ax func ret param) is halted if (func param)->ret?
		cuz that way ret could be leaf and func is like a type,
		such as (ax ifItsACatPicThenReturnLeaf leaf aPossibleCatPic) would be halted
		only if (ifItsACatPicThenReturnLeaf aPossibleCatPic)->leaf,
		and it could hook into axiomforest similar as the other way like
		(ifItsACatPicThenReturnLeafElseLeafofleaf aPossibleCatPic)->(leaf leaf)
			if its certainly not a cat pic, and any return other than leaf or (leaf leaf) could mean unknown
			and if it never halts thats also unknown.
		Its still the same 3 params as (ax func ret param), or (ax ret func param) would also work,
		but func cant be the last one cuz func has control.
	Similarly func could be something that calls its param on another func, if you want it to do some other order.
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
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	//FIXME need to get rid of some ops so it fits.
	public static void main(String[] args){
		int sum = 0;
		lg("Ops...");
		for(Op op : Op.values()){
			if(op.params != 0) sum += 1<<op.params;
			lg("Op."+op+"("+op.params+")");
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
