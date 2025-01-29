package a2;

import java.util.Random;

public class Waltz {
	
	/**
	 * The number of precomposed musical fragments making up a minuet
	 * sequence.
	 */
	public static final int MINUET_SEQUENCE_LENGTH = 16;

	/**
	 * The number of precomposed musical fragments making up a trio
	 * sequence.
	 */
	public static final int TRIO_SEQUENCE_LENGTH = 16;
	
	/**
	 * The available instrument recordings. 
	 */
	public static final String[] INSTRUMENTS = {
			"clarinet",
			"flute-harp",
			"mbira",
			"piano",
	};

	/**
	 * The random number generator used by this class.
	 */
	public static final Random RNG = new Random();

	public static String toPathname(String filename) {
		 return System.getProperty("user.dir") + "/mozart/" + filename + ".wav";
	}


	
	public static int roll(int n) {
		// Makes a random number between 1 and n
		if (n <= 0) {
	        return 0; 
	    }
	    
	    int sum = 0;
	    for (int i = 0; i < n; i++) {
	        sum += RNG.nextInt(6) + 1; 
	    }
	    
	    return sum; 
	}
	

	public static boolean hasInstrument(String instrument) {
		// Checks if the instrument is in the array
		for (String inst : INSTRUMENTS) {
			if (inst.equals(instrument)) {
				return true; 
			}
		}
	return false;
	}


	public static String[] makeMinuet(String instrument) {
		// Returns an array of length, each element is the name of a randomly chosen 
		// minuet musical fragment for the specified instrument
		if (!hasInstrument(instrument)) {
			throw new IllegalArgumentException("The instrument not found");
		}
		String[] sequence = new String[MINUET_SEQUENCE_LENGTH];
		for (int i = 0; i < MINUET_SEQUENCE_LENGTH; i ++) {
			int rollSum = roll(2); // Rolls 2 6 sided dice
			sequence[i] = instrument + "/minuet" + i + "-" + rollSum;
		}
		return sequence;
	}
	

	public static String[] makeTrio(String instrument) {
		// Returns each element is the name of a randomly chosen trioi musical
		if (!hasInstrument(instrument)) {
	        throw new IllegalArgumentException("Unknown instrument: " + instrument);
	    }

	    String[] sequence = new String[TRIO_SEQUENCE_LENGTH];
	    for (int i = 0; i < TRIO_SEQUENCE_LENGTH; i++) {
	        int rollValue = roll(1); 
	        sequence[i] = instrument + "/trio" + i + "-" + rollValue;
	    }
	    return sequence;
	}
	

	public static String[] makeRandomMinuet() {
		// Generates a sequence of minuet fragments with randomly chosen instruments
		String[] sequence = new String[MINUET_SEQUENCE_LENGTH];
	    
	    for (int i = 0; i < MINUET_SEQUENCE_LENGTH; i++) {
	        String instrument = INSTRUMENTS[RNG.nextInt(INSTRUMENTS.length)];  
	        int rollSum = roll(2);  
	        sequence[i] = instrument + "/minuet" + i + "-" + rollSum;  
	    }
	    
	    return sequence;
	}


	public static String[] makeRandomTrio() {
		// each element is the name of a randomly chosen trio musical fragment played by a randomly chosen instrument
		String[] sequence = new String[TRIO_SEQUENCE_LENGTH];
	    
	    for (int i = 0; i < TRIO_SEQUENCE_LENGTH; i++) {
	        String instrument = INSTRUMENTS[RNG.nextInt(INSTRUMENTS.length)];
	        int rollValue = roll(1);  
	        sequence[i] = instrument + "/trio" + i + "-" + rollValue;
	    }
	    
	    return sequence;
	}


	/**
	 * Plays a waltz on a piano.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		String[] minuet = Waltz.makeMinuet("piano");
		String[] trio = Waltz.makeTrio("piano");
		
		for (String f : minuet) {
			StdAudio.play(toPathname(f));
		}
		for (String f : trio) {
			StdAudio.play(toPathname(f));
		}

	}
}
