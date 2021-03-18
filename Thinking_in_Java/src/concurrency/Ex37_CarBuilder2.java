package concurrency;
import java.util.concurrent.*;
import static net.mindview.util.Print.print;


class Car {
	private final int id;
	private boolean engine = false, driveTrain = false, 
			wheels = false, exhaustSystem = false, 
			bodywork = false, bumper = false;
	public Car(int idn) {
		id = idn;
	}
	// Empty Car object:
	public Car() {
		id = -1;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void addEngine() {
		engine = true;
	}
	public synchronized void addDriveTrain() {
		driveTrain = true;
	}
	public synchronized void addWheels() {
		wheels = true;
	}
	public synchronized void addExhaustSystem() {
		exhaustSystem = true;
	}
	public synchronized void addBodywork() {
		bodywork = true;
	}
	public synchronized void addBumper() {
		bumper = true;
	}
	public synchronized String toString() {
		return "Car " + id + " [" + " engine: " + engine 
				+ " driveTrain: " + driveTrain 
				+ " wheels: " + wheels 
				+ " exhaustSystem: " + exhaustSystem 
				+ " bodywork: " + bodywork
				+ " bumper: " + bumper + " ]";
	}
}

class Assembler implements Runnable {
	private CarQueue chassisQueue, finishingQueue;
	private Car car;
	private CyclicBarrier barrier = new CyclicBarrier(7);
	private RobotPool robotPool;

	public Assembler(CarQueue cq, CarQueue fq, RobotPool rp) {
		chassisQueue = cq;
		finishingQueue = fq;
		robotPool = rp;
	}

	public Car car() {
		return car;
	}

	public CyclicBarrier barrier() {
		return barrier;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until chassis is available:
				car = chassisQueue.take();
				// Hire robots to perform work:
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelRobot.class, this);
				robotPool.hire(ExhaustSystemRobot.class, this);
				robotPool.hire(BodyworkRobot.class, this);
				robotPool.hire(BumperRobot.class, this);
				barrier.await(); // Until the robots finish
				// Put car into finishingQueue for further work
				finishingQueue.put(car);
			}
		} catch (InterruptedException e) {
			print("Exiting Assembler via interrupt");
		} catch (BrokenBarrierException e) {
			// This one we want to know about
			throw new RuntimeException(e);
		}
		print("Assembler off");
	}
}

class ExhaustSystemRobot extends Robot {
	public ExhaustSystemRobot(RobotPool pool) {
		super(pool);
	}
	protected void performService() {
		print(this + " installing ExhaustSystem");
		assembler.car().addExhaustSystem();
	}
}

class BodyworkRobot extends Robot {
	public BodyworkRobot(RobotPool pool) {
		super(pool);
	}
	protected void performService() {
		print(this + " installing Bodywork");
		assembler.car().addBodywork();
	}
}

class BumperRobot extends Robot {
	public BumperRobot(RobotPool pool) {
		super(pool);
	}
	protected void performService() {
		print(this + " installing Bumper");
		assembler.car().addBumper();
	}
}

public class Ex37_CarBuilder2 {

	public static void main(String[] args) throws Exception {
		CarQueue chassisQueue = new CarQueue(), 
				finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WheelRobot(robotPool));
		exec.execute(new ExhaustSystemRobot(robotPool));
		exec.execute(new BodyworkRobot(robotPool));
		exec.execute(new BumperRobot(robotPool));
		exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
		exec.execute(new Reporter(finishingQueue));
		// Start everything running by producing chassis:
		exec.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
	}

}
