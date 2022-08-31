package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int count = 0;
        AList<Integer> lab = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> time = new AList<Double>();
        AList<Integer> opcounts = new AList<>();
        time_helper1(1000,lab);
        Stopwatch sw = new Stopwatch();
        time_helper2(10000,lab);
        double timeInSeconds = sw.elapsedTime();
        Ns.addLast(1000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(2000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(2000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(4000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(4000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(8000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(8000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(16000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(16000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(32000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(32000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(64000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(64000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;

        lab = new AList<Integer>();
        time_helper1(128000,lab);
        sw = new Stopwatch();
        time_helper2(10000,lab);
        timeInSeconds = sw.elapsedTime();
        Ns.addLast(128000);
        time.addLast(timeInSeconds);
        opcounts.addLast(10000);
        lab = null;
        printTimingTable(Ns, time, opcounts);

    }

    private static void time_helper2(int k2,AList<Integer> lab) {
        for(int i = 0;i<k2;i++){
            lab.getLast();
        }
    }

    public static void time_helper1(int k1,AList<Integer> lab){
        for(int i = 0;i<1000;i++){
            lab.addLast(1);
        }
    }

}
