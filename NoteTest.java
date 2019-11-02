import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class NoteTest{
    private static final int SAMPLE_RATE = 32 * 1024;
    
    public static void main(String[] args) throws InterruptedException {
	    byte[] aNote = ( new SinWave(4000, 400, 100f)).note();
	    //	    play (aNote);	    


	    byte[] fadeOutNote = new Envelope(aNote).fadeOut();
	    byte[] fadeInNote = (new Envelope(aNote).fadeIn());
	    byte[] tremolo = (new Envelope(aNote).tremolo(25));
	    
	    	    // filter
	    Filter filter = new Filter(fadeInNote, 270, 400);
	    byte[] filteredNote = filter.cutOffSinCurve(30);
	    // play(tremolo);
	    play(aNote);
	    play(filteredNote);	    
		
    }

    private static void play(byte[] note){
	try{
	    final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
	    SourceDataLine line = AudioSystem.getSourceDataLine(af);
	    line.open(af, SAMPLE_RATE);
	    line.start();
	    int count = line.write(note, 0, note.length);
	    line.drain();
	    line.close();				   
				   
	}catch(Exception e){
	    System.out.println("err");
	    System.out.println(e);
	}

    }
}
