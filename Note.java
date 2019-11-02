public class Note {

    private double[] equalTemperament;
    private String[] noteNames = {"A", "B", "C", "D", "E", "F", "G" };
    private String[] modifiers = {"#", "b"};
    private String[] noteNamesSharp = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#" };
    private String[] noteNamesFlat = {"A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab" };

    private int[] dorianModeScale = {0, 2, 3, 5, 7, 9, 10 };
    
    private double[] scale;
    
    Note(String key, String modeName){
	setEqualTemerament();
	setMode(key, modeName);
    }

    private void setEqualTemerament(){
	equalTemperament = new double[60];
	for(int i = -24; i < 36; i++){
	    equalTemperament[i + 24] = 440.00 * Math.pow(2.00, ( (double)i) / 12.00);
	}
    }

    private void setMode(String key, String modeName){
	scale = new double[28];
	
	if(modeName.equals("dorian")){
	    //System.out.println("dorian mode");
	}
	int tonalCenter = 0; // default key in A
	for(int i = 0; i < 12; i++){
	    if(key.equals(noteNamesFlat[i]) || key.equals(noteNamesSharp[i])){
		tonalCenter = i;
		//System.out.println("tonal center");
		//System.out.println(tonalCenter);
	    }
	}

	if(modeName.equals("dorian")){
	    int scaleIndex = 0;
	    for(int octave = 0; octave < 4; octave++){
		for(int i = 0; i < 7; i++){
		    scale[scaleIndex] = equalTemperament[ dorianModeScale[i] + (12 * octave) + tonalCenter ];
		    scaleIndex++;
		}
	    }
	}
    }

    public double[] getEqualTemparament(){
	return equalTemperament;
    }

    public double[] getScale(){
	return scale;
    }

    /** TODO:
    public double getNote(String noteName){

    }
    */

}
