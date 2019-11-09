import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class InstrumentPart implements Runnable{
    private Thread t;

    
    private static final double THREEBILLION = (10 ^ 9) * 3;
    private static final int SAMPLE_RATE = 48 * 1024;

    private int[] noteNumbers;
    private double[] notes;
    private String threadName;
    private int baseDuration;
    private float volume;
    private Envelope envelope;
    private int conductor;
    private String[] tonalCenters;
    

    InstrumentPart(String tName, int[] givenNoteNumbers, String[] TonalCenters, int givenBaseDuration, float vol){
	noteNumbers = givenNoteNumbers;
	threadName = tName;
	tonalCenters = TonalCenters;
	baseDuration = givenBaseDuration;
	volume = vol;
	conductor = ThreeBillion.conductor;
    }

    public void run() {
	try{
	    final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
	    SourceDataLine line = AudioSystem.getSourceDataLine(af);
	    line.open(af, SAMPLE_RATE);
	    line.start();

	    double cnt = 0;
	    for(int i = 0; i < tonalCenters.length; i++){
		conductor = ThreeBillion.conductor;
		Note n = new Note(tonalCenters[i], "dorian");
		notes = getPartScale(n.getScale(), noteNumbers);
		while(ThreeBillion.conductor == conductor){
		    if(ThreeBillion.conductor > conductor){
			conductor++;
			break;
		    }
		    double frequency = notes[(int)(Math.random() * notes.length)];
		    int duration = (int)(Math.random() * 10 + 1)   * baseDuration;
		    byte [] toneBuffer = wave(frequency, duration);
		    int count = line.write(toneBuffer, 0, toneBuffer.length);
		    cnt = cnt + 0.01;
		}
	    }
	    line.drain();
	    line.close();
	}catch(LineUnavailableException e){
	}
    }

    private byte[] wave(double frequency, int ms){
	SinWave sinWave = new SinWave(ms, frequency, volume);
	byte[] wave;
	int harmonicsRandomNess = (int)(Math.random() * 20);
	if(harmonicsRandomNess > 7){
	    wave = sinWave.randomHarmonics((int)(Math.random() * 5));
	}else{
	    wave = sinWave.randomHarmonics((int)(Math.random() * 2));
	}
	byte[] processedWave = new byte[wave.length];

	// randomely alter the tone
	// filter
	int randFilterSwitch = (int)(Math.random() * 15);
	Filter fil = new Filter(wave, frequency * 0.7 , frequency);
	
	switch(randFilterSwitch){
	case 1:
	    //if(!threadName.equals("JAVA")){
	    processedWave = fil.increaseFreq();
		//}
	    break;
		
	case 2:
	    //	    if(!threadName.equals("JAVA")){	    
	    //processedWave = fil.cutOffSinCurve( (double)(Math.random() * 20) + 40);
		//	    }
	    break;
	default:
	    for(int i=0; i < wave.length; i++){
		processedWave[i] = wave[i];
	    }	    
	}

	// envelope
	int randEnvelopeSwitch = (int)(Math.random() * 13);
	Envelope en = new Envelope(processedWave);	
	switch(randEnvelopeSwitch){
	case 1:
	case 2:
	case 3:	    
	    processedWave = en.fadeIn();
	    break;
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
	    processedWave = en.fadeOut();
	    break;
	case 9:
	case 10:
	case 11:
	    processedWave = en.tremolo( (double)(Math.random() * 50));
	    break;
	default:
	    
	}
	
	return processedWave;
    }

    public void start () {
	if (t == null) {
	    t = new Thread (this, threadName);
	    t.start ();
	}
    }


    private double[] getPartScale(double[] scale, int[] indexes){
	double[] ret = new double[indexes.length];

	for(int i = 0; i < indexes.length; i++){
	    ret[i] = scale[indexes[i]];
	}

	return ret;
    }

}
