package concurrency;
// {Exe: javap -c concurrency.Atomicity}

public class Atomicity {
    int i;
    void f1() { i++; }
    void f2() { i += 3; }   
}
