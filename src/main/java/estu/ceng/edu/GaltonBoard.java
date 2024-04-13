package estu.ceng.edu;

public class GaltonBoard implements Runnable {

    private final int[] accumulatedBallsInBinCells;
    private final int numBins;

    public GaltonBoard(int numBins) {
        this.numBins = numBins;
        this.accumulatedBallsInBinCells = new int[numBins];
    }

    @Override
    public void run() {
        int reachedBinIndex = getReachedBinIndex(this.numBins);
        int oldAcc = this.accumulatedBallsInBinCells[reachedBinIndex];
        this.accumulatedBallsInBinCells[reachedBinIndex] = oldAcc + 1;
    }

    private int getReachedBinIndex(int numBins) {
        int location = 0;
        for (int i = 1; i < numBins; i++)
            if (Math.random() < 0.5) location++;
        return location;
    }

    public int[] getAccumulatedBallsInBinCells() {
        return accumulatedBallsInBinCells;
    }
}
