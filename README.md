# wikibinator104

Newer version will be in https://github.com/benrayfield/wikibinator105 which is "A redesign of wikibinator104 to directly allow vararg, instead of putting it in linkedlists, for efficiency. This is a big change to the universal function so is its own project. TODO copy parts from wikibinator104's readme etc. See Op.java"

Scalable gaming-low-lag p2p wiki of 1 editable universal function, thats a universal lambda and pattern calculus function of 7 params, with vararg, GPU optimizable, turing-complete-challenge-response, godel-quality-self-reference (wiki can call itself recursively, emulate itself, etc), safely sandboxed across millions of untrusted computers. (TODO)

For ages 0 to expert. It will teach you how itself and every known kind of math works and to build fun games and useful tools and play and work together, from the basics of math counting on your fingers up to the most advanced AI and number crunching and scaleable systems deployed in realtime, and for millions of people to be able to do that together in p2p without anyone being above or below anyone else, just merges and forks in binary forest of immutable objects viewed any way you like as functions can be built to view functions in new ways. (TODO).

Later it will create its own graphics per pixel (around 60 times per second) and sounds per wave amplitude (around 20000 times per second), with drag-and-drop function onto function to find/create function, and other intuitive ways of using it. Here's how it might look when used as a programming language...

```
λ //is the universal function, also often named u or leaf or Λ. In java λ.java is the main object type.

//pair, typeval, l, r, t, f, i, isleaf, curry, wiki, ax are functions you can make with λ.

(x y) //is x called on y.

(x y z) //is ((x y) z)

{x y} //is (s x y)

{x y z} //is (s {x y} z)

[x y] //is (pair x y)

[x y z] //is [[x y] z]. If you want a linkedlist you have to write the λ like in [λ x y z].

<x y> //is (pair x y), but [x y] is how its normally displayed if theres only 2.

<w x y z> is [w <x y z>] is [w [x [y z]]].
If you want a linkedlist you have to write the λ like in <w x y z λ>.

,x means (t x). ,,,x means (t (t (t x))).

*x means (curry x), such as in (*[λ "add 3 doubles" {...func body...} ,,λ] 5 10 100.3) -> 115.3

+ddd = *[λ "add 3 doubles" {...func body...} ,,λ]
(+ddd 5) -> *[λ "add 3 doubles" {...func body...} 5 ,λ]
(+ddd 5 10) -> *[λ "add 3 doubles" {...func body...} 5 10 λ]
(+ddd 5 10 100.3) -> 115.3
(+ddd x y z) -> ({...func body...} [λ "add 3 doubles" {...func body...} x y z])
(l +ddd) -> curry
(r +ddd) -> [λ "add 3 doubles" {...func body...} ,,λ]
(r (r +ddd)) -> ,,λ
//FIXME some of these skipped the (pair x) in (pair x y), so you could use car and cdr.
(r (r (r +ddd))) -> ,λ
(l (r (r +ddd))) -> t
(l (r +ddd)) -> [λ "add 3 doubles" {...func body...}]
(l +ddd (r +ddd)) -> +ddd //(l x (r x)) -> x forall x,
	if x is clean, else you have to use L and R which handle clean and dirty.
	
There is no built in double or string math. Everything has to be derived
from the universal function called on itself in various combos,
such as emulating the bits of IEEE754 double multiply math and utf8/unicode, then optimizing
it in an Evaler.java (to use double hardware ops or LWJGL OpenCL GPU (in lazycl)) which is where
compiled functions of (long maxSpend, function func, function param)->longRemaining_and_function go.

(ax  115.3 (*[λ "add 3 doubles" {...func body...} ,,λ] 5 10) 100.3) //means ret<-(func param) is true.

//(wiki x) is defined only by the statements made by ax such as (ax "world" wiki "hello")
causes "world"<-(wiki "hello") to be true, but in theory it can also pattern match or have
things built into some of the VMs based on belief in those patterns (will be tested automatically
in realtime, that it doesnt have more than 1 unique returnVal for same function call anywhere in
the network) such as a "spend" call to limit compute resources nondeterministicly and recursively
or a "solve" call where (solve x) -> y where (x y)->λ for any y thats a solution of x
(though that will often not find solutions, so you'll probably want to use it inside a spend call
to limit how long it will look and how much memory it might use while looking etc.

"add 3 doubles" is (typeval "text/plain" bytes_of_the_string_add_3_doubles), except first param
of typeval can be just the bits without having to wrap it in a typeval.

115.3 is (typeval "double" those_64_bits), compared to if you just want those_64_bits for
efficiency it would be displayed some other way and without implying its a double.

//TODO some prefix that means clean vs dirty? Which should be the one that doesnt need a prefix?
//Maybe ?x means dirty x and /x means clean x
//and x by itself means whatever clean vs dirty its written inside
//such as ?[/+ddd (b c) /(y z) /200] is dirty but contains whatever clean /(y z) returns
//and clean /200 and clean /+ddd and whatever the ?(b c) dirty call returns.
//Inside a clean call, its either halted and clean or not halted
//and may have dirty things to truncate to clean before using them.
//λ takes 7 params. ax evals at 6 (verifies constraint) and 7 params, everything else just at 7.
//Clean calls start with (λ λ). Dirty calls start with (λ anything_except_λ) such as (λ (λ λ)).
//Theres 6 params after that,
//which choose between pair, typeval, l, r, t, f, i, isleaf, curry, wiki, ax etc,
//then the params of those opcodes, so the prefix and opcode params always sum to 7 params
//when its time to eval (or 6 params for ax to verify constraint), and less params is waiting.
//For example, s takes 3 params, and i takes 1 param, and (s i i) calls a param on itself,
//and (s i i) is waiting on 1 param, and (s i i wiki) -> (wiki wiki),
//and (s i i (s i i)) -> (s i i (s i i)) which is an infinite loop,
//but if you call some lazy form of (s i i (s i i)) inside a spend call it will
//limit the amount of compute resources (time, memory, num of compiles, network, etc)
//before giving up on (s i i (s i i)) or may know its not going to end within that limit
//so not even spend the compute resources on it as soon as it knows it cant do the requested work.
```

------

TODO rewrite confusing text below...

Dovetailing is needed cuz (L x (R x)) equals x, forall x, and L and R are its childs in the binary forest of call pairs which is how all possible functions are made. Somewhere inside that is a bloom-filter, which is one of the ways of using the axiomOp (Op.ax), which accumulates true statements (and statements which can contradict them, but shouldnt ever generate contradictions (then have to fix them) if everyone follows the protocol), so cant let the lambdas just generate any statements they want in axiomOp, only true statements. But for every statement, you can call (L statement) to get its left child (a function), and call (R statement) to get its right child (a function), and can call any 2 functions on eachother to create (a or find an existing) function. If you take such a true statement twoPlusTwoEqualsFour then (L twoPlusTwoEqualsFour (R twoPlusTwoEqualsFour)) equals twoPlusTwoEqualsFour. Or similarly (L L (R L)) equals L. But what if you try to create a false statement by taking 2 things that dont seem to fit together? Dovetailing is a way to loop through all possible true statements which can be proven in any finite time and memory. An example of a statement which may take infinite time and memory to prove is does P equal NP or not. An example of a statement that takes finite time and memory to prove is what would the weather do next week based on its current movements and a given lambda based model of physics, or is this or that a picture of a cat, or whats the 1 trillionth prime number, or basically anything any computer can do in practice but only including parts of the deep abstractions that mathematicians get into. Wikibinator will actually do dovetailing, but it can only do a small amount of it cuz its like a chess game tree of the space of all possibilities, of lambda functions. The more important use of dovetailing in this case is to define, in abstract math at least, what calculation to do when there are many possible ways to generate the same true statement and you dont know which one the VM is optimized to nondeterministicly use, so that can be used as an id in the system for (eventually automatic but for now manual) formal-verification purposes to create a Compiled.java instance to look in the VM's hashtables for does it have the relevant objects needed to do the next debugStepInto (calculate recursively into detail) or debugStepOver (needs a cache of [func param return] for a given [func param] call, which many of those accumulate and get uncached in order of least recently used) or other proof based data structures where theres many possible ways to prove new true statements using combos of existing true statements (such as (paintMustacheOn picOfCat)->aPicOfACatWithAMustache) would be a [func param return] cache, similar to (paintMustacheOn 42)->someConstantMeaningItDoesntKnowWhatToDo is a [func param return] cache, and is a statement in axiomOp. So if you call (L aPicOfACatWithAMustache (R aPicOfACatWithAMustache)) it equals aPicOfACatWithAMustache, nomatter what kind of thing the aPicOfACatWithAMustache is, but if you try to fake the creation of true statements from other true statements, the dovetail proof wont be able to match the Compiled.java instance to it so would have to actually do the dovetailing or whatever lesser optimizations it can come up with, and would one way or another do the exact calculation that the universal function says to, normally by looking in VM's hashtables for the relevant other true statements or more often just simple halted lambdas derived from combos of other halted lambdas.

This is made of 11 opcodes you can see in the Op enum farther below, which each do a certain lambda or pattern calculus or godel-like thing. This is a redesign of https://github.com/benrayfield/occamsfuncer which is a similar function of 15 params and has a working interpreter in which I derived an equals function (of shape of binary forest of call pairs down to the universal function as the shared leaf) and its testcases include (equals equals equals)->T and it has the basics of lisp like cons car cdr. Its also using some of the code from https://github.com/benrayfield/wikibinator101 and https://github.com/benrayfield/axiomforest and will be compatible with axiomforest in the (axiomforestLeaf ((axiomforestLeaf((axiomforestLeaf axiomforestLeaf)(axiomforestLeaf axiomforestLeaf))) "text/plain" "wikibinator104")) aka (axiomforestLeaf "wikibinator104") namespace where any binary forest node thats halted is TruthValue.yes, thats not halted is TruthValue.no, or TruthValue.unknown, or if error that its both (never happens in the math, but may in practice by those who dont follow the protocol) TruthValue.bull, and wikibinator104 will not be able to see outside its own namespace in axiomforest and will see its namespace as its own leaf, below which it cant see, but it could emulate axiomforest so things in other systems in axiomforest could in theory be translated to it that way.

Wikibinator is simpler and more advanced than occamsfuncer (15 param universal function). It takes 7 params. The first chooses strict vs loose. In strict mode, you cant call the wiki since its nondeterministic what edits and forks people might do and merge (at gaming-low-lag automatically), so it evals to (S I I (S I I)) which is an infinite loop. All calls will halt by limiting its compute resources recursively, and this process is planned to improve by https://en.wikipedia.org/wiki/Dovetailing_(computer_science) in ax (wikibinator104.Op.ax) about ways to prove that a certain func called on a certain param returns a certain value and never has more than 1 unique return value then use Compiled.java instances as optimizations of that exponential number of calculations to optimize it down to constant time (like a few microseconds) which is what the VM code is doing anyways and it just needs dovetailing, in some cases, to describe all the possible things the VM might be doing, so it can use that derived lambda function as an emulator of itself, built inside itself, then hook an optimization into it so its actually using the VM in a deterministic safe way, so that it can deterministicly do every compute step (debugStepInto and debugStepOver, counting GPU calls and other optimizations as 1 atomic block of calculations) in bigO(1) so deterministicly will always halt but will not always be turing-complete as its just better precision of can give up and back out of a calculation thats taking too long or that you or AIs dislike for whatever reason, to put more effort into the kinds of calculations we like and build things together.

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



/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.spec;
import java.util.function.UnaryOperator;

/** immutable. binary forest node, defined ONLY by its forest shape, with no data in each node
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
	
	//"wikibinatorDovetailForDebugstepoverAndDebugstepintoCuzThoseAreManyPathsToTheSameFuncparamreturnAndNeedDovetailingSo(L x (R x))EqualsXForXIs(axiomOp ... stepstuff)"
	
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
	
	/** cache of 8 of (isLeaf this) .. (isLeaf this.l.l.l.l.l.l.l)
	or FIXME is it (isLeaf this.r) .. (isLeaf this.l.l.l.l.l.l.l.r)
	since a currylist is for example (u u (u u) u (u u) ...params...),
	and want to know which things before ...params... are` u vs anything other than u.
	Might need to pad a 1 bit to mark a bitstring size of 0..7 bits like did in an earlier wikibinator version
	called the "op byte"??? If so, rename this to opByte.
	*/
	public byte isLeafsByte();
	
	public default λ clean(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is leaf, unless it already is. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public default λ dirty(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is (leaf leaf), unless it already is something other than leaf. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public EvalerChain compiled();
	
	public void setCompiled(EvalerChain c);
	
	/** If this is a cbt (complete binary tree of pair of pair... of t vs f),
	either as pure interpreted lambdas or a wrapper of a byte, long, float, array, nio Buffer, etc,
	then view it as Blob (immutable).
	TODO can this be null?
	*/
	public Blob blob();

}



/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.impl.optimize;

/** opcode is double. all possible opcodes are valid for all possible double[] mem states.
This would only be an optimization of some combo of calls of the universal func which emulates this in a cbt,
and this is not part of the spec, nor does it contradict the spec to optimize what the spec does
only when it happens to match this, which it may often happen to match it when
user level code is designed knowing that this optimization is in some VMs and maybe not in other VMs.
Eventually, optimizations will be compiled automatically instead of manually written,
though this can optimize a huge variety of possible things as it is turingComplete,
just not nearly as sparse as the usual function pointers.
*/
public strictfp class SomeKindOfAssemblyLikeLanguageInDoubleArrayWith2Registers {
	
	/** ((int)double[IP])&mask is instructinPointer */
	public static final int IP = 0;
	
	/** ((int)double[SP])&mask is stackPointer */
	public static final int SP = 1;
	
	/** you may want to use double[HALT] as 0 to continue and any nonzero value (or cast to int is nonzero?) to continue,
	of at most maxSteps number of steps in param of steps func???
	*/
	public static final int HALT = 2;
	
	public static double get(double ptr, double[] mem){
		return mem[ptr(ptr,mem)];
	}
	
	public static double get(int ptr, double[] mem){
		return mem[ptr(ptr,mem)];
	}
	
	public static void put(double ptr, double val, double[] mem){
		mem[ptr(ptr,mem)] = val;
	}
	
	public static void put(int ptr, double val, double[] mem){
		mem[ptr(ptr,mem)] = val;
	}
	
	public static double opcode(double[] mem){
		return get(mem[IP],mem);
	}
	
	public static int ip(double[] mem){
		return ptr(mem[IP],mem);
	}
	
	public static int sp(double[] mem){
		return ptr(mem[SP],mem);
	}
	
	public static void jump(double ptr, double[] mem){
		mem[IP] = ptr;
	}
	
	/** masked safe ptr, IF mem.length is powOf2.
	<br><br>
	System.out.println("nan"+(0./0.)+" asint:"+(int)(0./0.));
	System.out.println("inf"+(1./0.)+" asint:"+(int)(1./0.));
	System.out.println("-inf"+(-1./0.)+" asint:"+(int)(-1./0.));
	System.out.println("ok");
	nanNaN asint:0
	infInfinity asint:2147483647
	-inf-Infinity asint:-2147483648
	ok        
	*/
	public static int ptr(double ptr, double[] mem){
		return ((int)ptr)&(mem.length-1);
	}
	
	/** masked safe ptr, IF mem.length is powOf2 */
	public static int ptr(int ptr, double[] mem){
		return ptr&(mem.length-1);
	}
	
	public static void push(double val, double[] mem){
		int sp = ptr(mem[SP],mem);
		int nextSp = ptr(++sp,mem);
		mem[SP] = nextSp;
		mem[nextSp] = val;
	}
	
	public static int popInt(double[] mem){
		return (int)pop(mem);
	}
	
	public static double pop(double[] mem){
		int sp = ptr(mem[SP],mem);
		double ret = mem[sp];
		mem[SP] = ptr(--sp,mem);
		return ret;
	}
	
	public static void nextIp(double[] mem){
		mem[IP] = ptr(mem[IP]+1,mem); //have to mask it so it doesnt overflow eventually, even though its masked on every read
	}
	
	public static final byte
		memMask = 'm',
		memSize = 'M',
		mul = '*',
		neg = '-',
		add = '+',
		intAnd = '&',
		intOr = '|',
		intXor = '^',
		intNot = '~',
		intNand = 'n',
		intNor = 'N',
		intMinorityOf3 = '3', //like nand and nor, this is an NP op
		intShift = 'TODO choose a symbol for shift, and does it need upshift and downshift op or 1 op for all of it, and should it have a rotate op vs just do it with shifts ands and ors etc',
		write = 'w',
		read = 'r',
		jump = 'j';
	
	/** a little slower than step(double[]) which doesnt check isHalted(double[]) */
	public static void stepIfNotHalted(double[] mem){
		if(!isHalted(mem)) step(mem);
	}
	
	/** Modifies double[] mem. Does a bigO(1) amount of work.
	mem.length must be a powOf2 and must be at least 2, normally much bigger.
	TODO check for that vs let it be caller's responsibility?
	*/
	public static void step(double[] mem){
		double opcode = opcode(mem);
		if(opcode <= 0){
			//If opcode is nonpositive then push (neg)it as literal on stack. If want to push the opposite literal,
			//do that then use NEG opcode, so 2 opcodes.
			push(-opcode, mem); //push positive
			//TODO should opcodes be neg and this be positive? I expect to push positives more often.
			//TODO should int ops have a signed and unsigned version?
		}else{
			switch((byte)opcode){
			case memMask:
				push(mem.length-1,mem);
				nextIp(mem);
			break;
			case memSize:
				push(mem.length,mem);
				nextIp(mem);
			break;
			case mul:
				push(pop(mem)*pop(mem),mem);
				nextIp(mem);
			break;
			case neg:
				push(-pop(mem),mem);
				nextIp(mem);
			break;
			case add:
				push(pop(mem)+pop(mem),mem);
				nextIp(mem);
			break;
			case intAnd:
				push(popInt(mem)&popInt(mem), mem);
				nextIp(mem);
			break;
			case intOr:
				push(popInt(mem)|popInt(mem), mem);
				nextIp(mem);
			break;
			case intXor:
				push(popInt(mem)|popInt(mem), mem);
				nextIp(mem);
			break;
			case intNot:
				push(~popInt(mem), mem);
				nextIp(mem);
			break;
			case intNand:
				push(~(popInt(mem)&popInt(mem)), mem);
				nextIp(mem);
			break;
			case intNor:
				push(~(popInt(mem)|popInt(mem)), mem);
				nextIp(mem);
			break;
			case intMinorityOf3:
				int x = popInt(mem), y = popInt(mem), z = popInt(mem);
				push(~(x&y)^(y&z)^(z&x), mem);
				//This is an NP op, similar to NAND and NOR. Its output is 1 vs 0 equally often, given random inputs.
				//NOT OF: "maj := (a and b) xor (a and c) xor (b and c)" -- https://en.wikipedia.org/wiki/SHA-2
				nextIp(mem);
			break;
			case write:
				put(pop(mem), pop(mem), mem); //FIXME reverse order of those 2 pops? Is address on top of stack or second from top?
				nextIp(mem);
			break;
			case read:
				push(get(pop(mem),mem),mem); //FIXME order of these ops?
				nextIp(mem);
			break;
			case jump:
				jump(pop(mem),mem);
			break;
			TODO other basic math ops etc.
			TODO ops (in range 1..127) for around 32 local memory slots to read and write into
				from stack andOr to copy andOr swap between
			the top n parts of stack (all masked so all ops are valid for all possible double[] mem states)
			TODO a form of this that works with undomem and concat of cbts during it
			andOr just a form of it that runs n steps, or runs until a certain index contains value 0 (halt condition),
			then do concat and subrange as cbts then do more of this on such concats/subranges etc,
			and use that to build some simple graphics and game demos, proofOfconcepts that
			the lambdas can be optimized, even though there are far better optimizations to do later.
			}
		}
	}
	
	public static boolean isHalted(double[] mem){
		return mem[HALT] != 0;
	}
	
	public static void steps(long steps, double[] mem){
		while(steps-- > 0) step(mem);
	}
	
	public static void stepsOrChoosesToHalt(long maxSteps, double[] mem){
		while(!isHalted(mem) && maxSteps-- > 0) step(mem);
	}
	
	/** its recommended to use stepsOrChoosesToHalt instead cuz this might never halt,
	but stepsOrChoosesToHalt(...) and steps(...) always halt.
	*/
	public static void stepsUntilChoosesToHalt(double[] mem){
		while(!isHalted(mem)) step(mem);
	}
	
	public static void main(String[] args){
		TODO test this by using it to compute low resolution mandelbrot, and display it in a window
		(use ScreenUtil andOr BufferedImage andOr StretchVideo I have that code somewhere)
	}

}
