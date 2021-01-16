// Switching one enum on another.
package enumerated;
import static enumerated.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
	// 每个构造器中的参数，都是当前实例与enum中的三个实例，按定义次序逐个进行比较的结果
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);
	private Outcome vsPAPER, vsSCISSORS, vsROCK;
	RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
		this.vsPAPER = paper;
		this.vsSCISSORS = scissors;
		this.vsROCK = rock;
	}
	public Outcome compete(RoShamBo2 it) {
		switch(it) {
		default:
		case PAPER: return vsPAPER;
		case SCISSORS: return vsSCISSORS;
		case ROCK: return vsROCK;
		}
	}
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}
