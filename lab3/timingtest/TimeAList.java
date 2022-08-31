package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        // 时间测试
        int count = 0;
        AList<Integer> lab = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> time = new AList<Double>();
        AList<Integer> opcounts = new AList<>();
        Stopwatch sw = new Stopwatch();
        for(int i = 0;i<1000;i++){
            lab.addLast(1);
            count++;
        }
        double timeInSeconds = sw.elapsedTime();
        Ns.addLast(1000);
        time.addLast(timeInSeconds);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<2000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(2000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<4000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(4000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<8000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(8000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<16000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(16000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<32000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(32000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<64000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(64000);
        opcounts.addLast(count);

        count = 0;
        sw = new Stopwatch();
        for(int i = 0;i<128000;i++){
            lab.addLast(1);
            count++;
        }
        timeInSeconds = sw.elapsedTime();
        time.addLast(timeInSeconds);
        Ns.addLast(128000);
        opcounts.addLast(count);
        printTimingTable(Ns, time, opcounts);

    }
}
