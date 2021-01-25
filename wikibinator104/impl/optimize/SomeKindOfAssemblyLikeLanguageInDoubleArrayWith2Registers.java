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
	
	public static void addToSp(int add, double[] mem){
		mem[SP] = ptr(mem[SP]+add,mem);
	}
	
	public static double peek(double[] mem){
		return mem[ptr(mem[SP],mem)];
	}
	
	public static void nextIp(double[] mem){
		mem[IP] = ptr(mem[IP]+1,mem); //have to mask it so it doesnt overflow eventually, even though its masked on every read
	}
	
	private static byte op = 0;
	public static final byte
		noop = op++, //'_',
		getIp = op++, //'i',
		getSp = op++, //'s',
		doMask = op++, //'m',
		memMask = op++, //'M',
		memSize = op++, //'Z',
		mul = op++, //'*',
		neg = op++, //'-',
		add = op++, //'+',
		intAnd = op++, //'&',
		intOr = op++, //'|',
		intXor = op++, //'^',
		intNot = op++, //'~',
		intNand = op++, //'n',
		intNor = op++, //'N',
		intMinorityOf3 = op++, //'3', //like nand and nor, this is an NP op
		intShift = op++, //'TODO choose a symbol for shift, and does it need upshift and downshift op or 1 op for all of it, and should it have a rotate op vs just do it with shifts ands and ors etc',
		write = op++, //'w',
		read = op++, //'r',
		dup = op++,
		isNonzero = op++,
		jump = op++; //'j';
	
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
			push(-opcode, mem);
			nextIp(mem);
		}else{
			byte opcodeByte = (byte)opcode;
			switch(opcodeByte){
			case noop:
				nextIp(mem);
			break;
			case getIp:
				push(ip(mem),mem);
				nextIp(mem);
			break;
			case getSp:
				push(sp(mem),mem);
				nextIp(mem);
			break;
			case doMask:
				push(ptr(pop(mem),mem),mem);
				nextIp(mem);
			break;
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
			case isNonzero:
				push(pop(mem)==0?0:1,mem);
				nextIp(mem);
			break;
			case pop:
				addToSp(-1,mem);
				nextIp(mem);
			break;
			case dup:
				push(peek(mem),mem);
				nextIp(mem);
			break;
			case isNonzero:
				push(pop(mem)==0?0:1,mem);
				nextIp(mem);
			break;
			case jump:
				jump(pop(mem),mem);
			break;
			TODO a copy range op that calls System.arraycopy? What about the bigo1 per step?
				Maybe step should return the cost of the step and never let it get more than some small limit such max size 256 copied?
			default:
				//opcodeByte > 0.
				//The range 64..95 does TODO...
				//The range 96..127 does TODO...
				//or should it be 4 ranges of 16 each?
					copy to/from "local var" at mem[0..15]?
					and mem[sp(mem)-16..sp(mem)-1]?
					and swap vs copy?
				if()
				TODO around 32 local memory slots to read and write into from stack andOr to copy andOr swap between
				the top n parts of stack (all masked so all ops are valid for all possible double[] mem states)
			
			TODO other basic math ops etc.
			TODO a form of this that works with undomem and concat of cbts during it
			andOr just a form of it that runs n steps, or runs until a certain index contains value 0 (halt condition),
			then do concat and subrange as cbts then do more of this on such concats/subranges etc,
			and use that to build some simple graphics and game demos, proofOfconcepts that
			the lambdas can be optimized, even though there are far better optimizations to do later.
			TODO 4 or 6 1-byte ops per double, and the others can be noops if dont want to use like if one is a jump?
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

