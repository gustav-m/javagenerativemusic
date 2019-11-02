// Filter

public class Filter {
    private static final int SAMPLE_RATE = 32 * 1024;    
    private byte[] originalNote;
    private double cutOffFreqMin;
    private double cutOffFreqMax;
    private byte[] filteredNote;
    
    Filter(byte[] originalNote, double cutOffFreqMin, double cutOffFreqMax){
	this.originalNote = originalNote;
	this.cutOffFreqMin = cutOffFreqMin;
	this.cutOffFreqMax = cutOffFreqMax;
	filteredNote = new byte[this.originalNote.length];
    }

    public byte[] increaseFreq(){
	double cutoffFreq = cutOffFreqMin;
	filteredNote[0] = originalNote[0];
        for(int i=1; i < originalNote.length - 1; i++){
	    cutoffFreq = cutOffFreqMin + ( (cutOffFreqMax - cutOffFreqMin) / originalNote.length) * (i-1);
	    double alpha = alpha(cutoffFreq);
	    filteredNote[i] = (byte) ((double)filteredNote[i - 1] + (alpha * ( (double)originalNote[i] - (double)filteredNote[i - 1])));
	}
	System.out.println(cutoffFreq);
	return filteredNote;
    }

    public byte[] cutOffSinCurve(double speed){
	double cutoffFreqAvg = (cutOffFreqMax + cutOffFreqMin) / 2;
	double cutoffRange = cutOffFreqMax - cutOffFreqMin;
	filteredNote[0] = originalNote[0];
        for(int i=1; i < originalNote.length - 1; i++){
	    double cutoffFreq = cutoffFreqAvg + ( (Math.sin( 2 * Math.PI * i / SAMPLE_RATE / 10 * speed) - 1 ) * cutoffRange);
	    double alpha = alpha(cutoffFreq);
	    filteredNote[i] = (byte) ((double)filteredNote[i - 1] + (alpha * ( (double)originalNote[i] - (double)filteredNote[i - 1])));
	}
	return filteredNote;
    }

    private double alpha(double cutoffFreq){
	double rc = 1.0/(cutoffFreq * 2 * Math.PI);
	double dt = 1.0 / SAMPLE_RATE;
	double alpha = dt / (rc + dt);
	return alpha;
    }

}
