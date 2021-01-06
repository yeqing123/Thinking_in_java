package enumerated;
import  net.mindview.util.*; // 通常使用Enums时，我们导入net.mindview.util包

enum Activity {
	SITTING, LYING, STANDING, HOPPING,
	RUNNING, DODGING, JUMPING, FALLING, FLYING
}

public class RandomTest {
	public static void main(String[] args) {
        for(int i = 0; i < 20; i++)
        	System.out.print(Enums.random(Activity.class) + ", ");
	}
}
