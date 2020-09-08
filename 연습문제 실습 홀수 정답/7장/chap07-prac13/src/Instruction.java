import java.util.StringTokenizer;

public class Instruction {
	private String line;
	private String opcode;
	private String operand [] = new String [2];
	public Instruction(String line) {
		this.line = line;
		line = line.toUpperCase(); // 대문자로 만들기
		StringTokenizer st = new StringTokenizer(line);
		// line이 "ADD S J"
		opcode = st.nextToken(); // 첫 토큰, 명령, "ADD"
		operand[0] = st.nextToken(); // "S"
		operand[1] = st.nextToken(); // "J"
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public String getOperand(int index) {
		if(index < 0 || index > 2)
			return null;
		return operand[index];
	}
	
	public String toString() {
		return "[" + line + "] ";
	}
}
