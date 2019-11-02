// Envelope
//
// fade in, fade out, piano-like, tremolo

public class Envelope{
    private static final int SAMPLE_RATE = 32 * 1024;
    private byte[] givenNote;
    private int noteLength;
    private byte[] envelopedNote;

    Envelope(byte[] givenNote){
	this.givenNote = givenNote;
	noteLength = this.givenNote.length;
	envelopedNote = new byte[noteLength];
    }

    public byte[] fadeIn(){

	for(int i=0; i < noteLength; i++){
	    double multiplier = (i * 1 / (double)noteLength);	    
	    envelopedNote[i] = (byte)((double)givenNote[i] * multiplier) ;
	}
	return envelopedNote;
    }

    public byte[] fadeOut(){
	for(int i=0; i < noteLength; i++){
	    double multiplier = - 1 * (i * 1 / (double)noteLength) + 1;
	    envelopedNote[i] = (byte)( ((double)givenNote[i]) * multiplier );
	}
	return envelopedNote;	
    }

    public byte[] tremolo(){
	for(int i=0; i < noteLength; i++){
	    double angle = 1.5 * Math.PI * i / noteLength * 5;
	    double multiplier = Math.sin(angle);
	    envelopedNote[i] = (byte)( ((double)givenNote[i]) * multiplier );
	}
	return envelopedNote;		
    }
}
