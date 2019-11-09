// SinWave
// generate object of a byte stream that represents a note that has certain length/pitch/envelope

public class SinWave extends Oscillator{

    // constructor
    SinWave(int noteLength, double frequency, float volume){
	super(noteLength, frequency, volume);
	for(int i=0; i < note.length; i++){
	    double angle = 2.0 * Math.PI * i / period;
	    double oneSample = Math.sin(angle) * volume;
	    note[i] = (byte)(oneSample);
	}	
    }
    SinWave(byte[] note){
	super(note);
    }


    public byte[] note(){
	return note;
    }

    public SinWave addUpNotes(Oscillator addedOscillator){
	byte[] newNote = new byte[note.length];
	for(int i=0; i < note.length; i++){
	    newNote[i] = (byte) ( (double)note[i] + (double)addedOscillator.note()[i]);
	}
	return (new SinWave(newNote));
    }

    public byte[] randomHarmonics(int maxNumberOfOverTones){
	SinWave harmonics = this;
	for(int i = 0; i < maxNumberOfOverTones; i++){
	    harmonics = harmonics.addUpNotes(new SinWave(noteLength, frequency * (i+2), volume - (float)(Math.random() * (volume * 0.9))));
	}
	return harmonics.note();
    }
        
}
