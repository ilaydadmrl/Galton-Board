package estu.ceng.edu;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CmdLineException {
        Options options = getOptions(args);
        int numThread = options.numThread;
        int numBins = options.numBins;
        GaltonBoard galtonBoard = new GaltonBoard(numBins);
        startThreads(getThreads(numThread, galtonBoard));
        int sumOfBinValues = 0;
        int[] accBallsInBinCells = galtonBoard.getAccumulatedBallsInBinCells();
        for (int i = 0; i < accBallsInBinCells.length; i++) {
            int accBallsInBinCell = accBallsInBinCells[i];
            sumOfBinValues += accBallsInBinCell;
            System.out.printf("%d\t%d\n", i , accBallsInBinCell);
        }
        System.out.println("Number of requested threads: " + numThread);
        System.out.println("Sum of bin values: " + sumOfBinValues);
        System.out.println(numThread == sumOfBinValues ? "Nice work! Both of them are equal." : "They are NOT EQUAL :(");
    }

    public static Thread[] getThreads(int numThread, GaltonBoard galtonBoard) {
        Thread[] threads = new Thread[numThread];
        for (int i = 0; i < numThread; i++)
            threads[i] = new Thread(galtonBoard);
        return threads;
    }

    public static void startThreads(Thread[] threads) {
        Arrays.stream(threads).forEach(thread -> {
            thread.start();
            while (thread.isAlive()) {/*wait*/}
        });
    }

    public static Options getOptions(String[] args) throws CmdLineException {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);
        parser.parseArgument(args);
        return options;
    }

}
