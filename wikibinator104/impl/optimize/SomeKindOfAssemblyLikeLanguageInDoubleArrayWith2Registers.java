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
public class SomeKindOfAssemblyLikeLanguageInDoubleArrayWith2Registers {
	
	/** ((int)double[IP])&mask is instructinPointer */
	public static final int IP = 0;
	
	/** ((int)double[SP])&mask is stackPointer */
	public static final int SP = 1;
	
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
	
	/** masked safe ptr, IF mem.length is powOf2 */
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
		write = 'w',
		read = 'r',
		jump = 'j';
	
	/** Modifies double[] mem. Does a bigO(1) amount of work.
	mem.length must be a powOf2 and must be at least 2, normally much bigger.
	TODO check for that vs let it be caller's responsibility?
	*/
	public static void step(double[] mem){
		double opcode = opcode(mem);
		if(opcode <= 0){
			//If opcode is nonpositive then push it as literal on stack. If want to push a negative literal,
			//do that then use NEG opcode, so 2 opcodes.
			push(opcode, mem);
		}else{
			switch((byte)opcode){
			case memMask:
				push(mem.length-1,mem);
			break;
			case memSize:
				push(mem.length,mem);
			break;
			case mul:
				push(pop(mem)*pop(mem),mem);
				nextIp(mem);
			break;
			case neg:
				push(-pop(mem),mem);
			break;
			case add:
				push(pop(mem)+pop(mem),mem);
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
			}
		}
	}
	
	public static void steps(int steps, double[] mem){
		while(steps-- > 0) step(mem);
	}
	
	public static void main(String[] args){
		TODO test this by using it to compute low resolution mandelbrot, and display it in a window
		(use ScreenUtil andOr BufferedImage andOr StretchVideo I have that code somewhere)
	}

}
