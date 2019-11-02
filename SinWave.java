// SinWave
// generate object of a byte stream that represents a note that has certain length/pitch/envelope

public class SinWave {
    // sample rate
    private static final int SAMPLE_RATE = 32 * 1024;
    
    private byte[] note;
    private int noteLength;
    private double frequency;
    //private Envelope envelope;

    // constructor
    // TODO: add envelope as a parameter later
    SinWave(int noteLength, double frequency, float volume){
	this.noteLength = noteLength; // millisecond
	this.frequency = frequency;
	//this.envelope = envelope;

	int samples = noteLength * SAMPLE_RATE / 1000;
	double period = (double)SAMPLE_RATE / frequency;
	
	note = new byte[samples];

	for(int i=0; i < note.length; i++){
	    double angle = 2.0 * Math.PI * i / period;
	    double oneSample = Math.sin(angle) * volume;
	    note[i] = (byte)(oneSample);
	}
    }

    // second constructor - for a byte stream of a note that's already given
    SinWave(byte[] note){
	this.note = note;
    }

    public byte[] note(){
	return note;
    }
    
}
