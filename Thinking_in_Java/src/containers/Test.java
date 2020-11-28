package containers;
// 用于执行容器定时测试的框架

public abstract class Test<C> {
    String name;
    public Test(String name) { this.name = name; }
    // 针对不同的测试覆盖此方法。返回测试的实际重复次数。
    abstract int test(C container, TestParam tp);
}
