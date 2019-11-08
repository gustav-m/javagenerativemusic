// SinWave
// generate object of a byte stream that represents a note that has certain length/pitch/envelope

public class SinWave extends Oscillator{

    // constructor
    SinWave(int noteLength, double frequency, float volume){
	super(noteLength, frequency, volume);
    }


    public byte[] note(){
	for(int i=0; i < note.length; i++){
	    double angle = 2.0 * Math.PI * i / period;
	    double oneSample = Math.sin(angle) * volume;
	    note[i] = (byte)(oneSample);
	}	
	return note;
    }
    
}
