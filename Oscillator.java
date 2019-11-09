// Oscillator
// base class for types of waves

public  abstract class Oscillator {
    // sample rate
    private static final int SAMPLE_RATE = 32 * 1024;

    protected byte[] note;
    protected int noteLength;
    protected double frequency;
    protected double period;
    protected float volume;

    Oscillator(int noteLength, double frequency, float volume){
	this.noteLength = noteLength; // millisecond
	this.frequency = frequency;
	this.volume = volume;
	int samples = noteLength * SAMPLE_RATE / 1000;
        period = (double)SAMPLE_RATE / frequency;
	
	note = new byte[samples];
    }

    Oscillator(byte[] note){
	this.note = note;
    }
    
    public abstract byte[] note();
    public abstract byte[] randomHarmonics(int maxNumberOfOverTones);

    public double getFrequency() {
	return frequency;
    }


}
