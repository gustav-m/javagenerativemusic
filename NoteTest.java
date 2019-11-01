import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class NoteTest{
    private static final int SAMPLE_RATE = 16 * 1024;
    
    public static void main(String[] args) throws InterruptedException {
	    byte[] aNote = ( new SinWave(800, 440, 70f)).note();
	    play (aNote);	    
	    byte[] tremolo = (new Envelope(aNote).tremolo());
	    play (tremolo);

	    byte[] fadeOutNote = new Envelope(aNote).fadeOut();
	    play (fadeOutNote);
	    byte[] fadeInNote = (new Envelope(aNote).fadeIn());
	    play (fadeInNote);
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
