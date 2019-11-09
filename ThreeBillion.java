
public class ThreeBillion {

    // sets of scales
    public static final int[] ORACLE = { 0, 3, 6 , 9, 0, 0 ,0 };
    public static final int[] JAVA = {7, 10, 9, 13, 16, 16, 20, 20 };
    public static final int[] YOU = { 7, 9, 11, 13, 14, 16, 18, 21, 22, 22};
    public static final int[] DOWNLOAD = { 7, 8, 10, 13, 12, 12, 14, 7, 10, 8};
    public static final int[] DOWNLOAD2 = { 7, 8, 10, 13, 12, 12, 14, 7, 10, 8};
    public static final int[] DOWNLOAD3 = { 7, 8, 10, 13, 12, 12, 14, 7, 10, 8};    
    public static final int[] DEVICE = { 15, 17, 18, 1, 21, 22, 23};
    public static final int[] DEVICE2 = { 14, 16, 17, 18, 20, 21, 22};
    public static final String[] TonalCenters = { "A", "D", "C", "F", "Bb", "Eb", "Ab", "D", "A", "E", "Ab" };


    public volatile static int conductor = 0;
    

    public static void main(String[] args) throws InterruptedException {

	InstrumentPart s1 = new InstrumentPart("JAVA", ORACLE, TonalCenters , 300, 16f);
	s1.start();
	InstrumentPart s2 = new InstrumentPart("YOU", JAVA, TonalCenters , 124, 8f);
	s2.start();
	InstrumentPart s3 = new InstrumentPart("DOWNLOAD", YOU, TonalCenters,  60, 9f);
	s3.start();
	InstrumentPart s4 = new InstrumentPart("ORACLE", DOWNLOAD, TonalCenters, 1500, 9f);
	s4.start();

	InstrumentPart s5 = new InstrumentPart("DEVICE", DEVICE, TonalCenters, 80, 5f);
	s5.start();
	InstrumentPart s6 = new InstrumentPart("DEVICE2", DEVICE2, TonalCenters, 80, 5f);
	s6.start();

	InstrumentPart s7 = new InstrumentPart("DOWNLOAD2", DOWNLOAD2, TonalCenters, 200, 9F);
	s7.start();
	InstrumentPart s8 = new InstrumentPart("DOWNLOAD3", DOWNLOAD3, TonalCenters, 200, 8F);
	s8.start();


	

	while(true){
	    try{
		Thread.sleep(25000);
		conductor++;
		System.out.println("next part");
	    }catch(InterruptedException e){
		System.out.println("interrupted");
	    }
	}
    }
}
