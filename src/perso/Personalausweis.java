package perso;

import java.time.LocalDate;
import java.util.HashMap;

public class Personalausweis {

	private String behoerdenkennzahl;

	private String laufendeNummer;

	private LocalDate geburtsdatum;

	private LocalDate ablaufdatum;

	private char staatsangehoerigkeit;

	private boolean vorlaeufig;

	private static HashMap<Character, Integer> map;

	private static final char[] okay = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'C', 'F',
			'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'T', 'V', 'W', 'X', 'Y', 'Z' };

	private static final char[] forbidden = { 'A', 'B', 'D', 'E', 'I', 'O', 'Q', 'S', 'U' };

	private static final char[] start = { 'L', 'M', 'N', 'P', 'R', 'T', 'V', 'W', 'X', 'Y' };

	static {
		map = new HashMap<Character, Integer>();
		map.put('0', 0);
		map.put('1', 1);
		map.put('2', 2);
		map.put('3', 3);
		map.put('4', 4);
		map.put('5', 5);
		map.put('6', 6);
		map.put('7', 7);
		map.put('8', 8);
		map.put('9', 9);
		map.put('A', 10);
		map.put('B', 11);
		map.put('C', 12);
		map.put('D', 13);
		map.put('E', 14);
		map.put('F', 15);
		map.put('G', 16);
		map.put('H', 17);
		map.put('I', 18);
		map.put('J', 19);
		map.put('K', 20);
		map.put('L', 21);
		map.put('M', 22);
		map.put('N', 23);
		map.put('O', 24);
		map.put('P', 25);
		map.put('Q', 26);
		map.put('R', 27);
		map.put('S', 28);
		map.put('T', 29);
		map.put('U', 30);
		map.put('V', 31);
		map.put('W', 32);
		map.put('X', 33);
		map.put('Y', 34);
		map.put('Z', 35);
	}

	private static int getNthDigit(int number, int n) {
		return (int) ((number / Math.pow(10, n)) % 10);
	}

	public Personalausweis(String behoerdenkennzahl, String laufendeNummer, LocalDate geburtsdatum,
			LocalDate ablaufdatum, char staatsangehoerigkeit, boolean vorlaeufig) {
		super();
		this.behoerdenkennzahl = behoerdenkennzahl.toUpperCase();
		this.laufendeNummer = laufendeNummer;
		this.geburtsdatum = geburtsdatum;
		this.ablaufdatum = ablaufdatum;
		this.staatsangehoerigkeit = staatsangehoerigkeit;
		this.vorlaeufig = vorlaeufig;
	}

	public String[] getErsteNummer() {
		String[] nummer = new String[6];
		nummer[0] = vorlaeufig ? "ITD" : "IDD";
		nummer[1] = "<<";
		nummer[2] = behoerdenkennzahl;
		nummer[3] = laufendeNummer;
		nummer[4] = Integer.toString(getPruefzifferSeriennummer());
		nummer[5] = "<<<<<<<<<<<<<<<";
		return nummer;
	}

	private int getPruefzifferSeriennummer() {
		return (
		//@formatter:off
				map.get(behoerdenkennzahl.charAt(0)) * 7
			  + map.get(behoerdenkennzahl.charAt(1)) * 3
			  + map.get(behoerdenkennzahl.charAt(2)) * 1
			  + map.get(behoerdenkennzahl.charAt(3)) * 7
			  + map.get(laufendeNummer.charAt(0)) * 3
			  + map.get(laufendeNummer.charAt(1)) * 1
			  + map.get(laufendeNummer.charAt(2)) * 7
			  + map.get(laufendeNummer.charAt(3)) * 3
			  + map.get(laufendeNummer.charAt(4)) * 1
		//@formatter:on
		) % 10;
	}

	public String[] getZweiteNummer() {
		String[] nummer = new String[12];
		nummer[0] = Integer.toString(geburtsdatum.getYear() % 100);
		nummer[1] = geburtsdatum.getMonthValue() < 10 ? "0" : "";
		nummer[1] += Integer.toString(geburtsdatum.getMonthValue());
		nummer[2] = geburtsdatum.getDayOfMonth() < 10 ? "0" : "";
		nummer[2] += Integer.toString(geburtsdatum.getDayOfMonth());
		nummer[3] = Integer.toString(getPruefzifferDatum(geburtsdatum));
		nummer[4] = "<";
		nummer[5] = Integer.toString(ablaufdatum.getYear() % 100);
		nummer[6] = ablaufdatum.getMonthValue() < 10 ? "0" : "";
		nummer[6] += Integer.toString(ablaufdatum.getMonthValue());
		nummer[7] = ablaufdatum.getDayOfMonth() < 10 ? "0" : "";
		nummer[7] += Integer.toString(ablaufdatum.getDayOfMonth());
		nummer[8] = Integer.toString(getPruefzifferDatum(ablaufdatum));
		nummer[9] = new String() + staatsangehoerigkeit;
		nummer[10] = "<<<<<<<<<<<<<";
		nummer[11] = Integer.toString(getEndPruefziffer());
		return nummer;
	}

	private int getPruefzifferDatum(LocalDate date) {
		return (
		//@formatter:off
				getNthDigit(date.getYear(), 1) * 7
			  + getNthDigit(date.getYear(), 0) * 3
			  + getNthDigit(date.getMonthValue(), 1) * 1
			  + getNthDigit(date.getMonthValue(), 0) * 7
			  + getNthDigit(date.getDayOfMonth(), 1) * 3
			  + getNthDigit(date.getDayOfMonth(), 0) * 1
		//@formatter:on
		) % 10;
	}

	private int getEndPruefziffer() {
		return (
		//@formatter:off
			map.get(behoerdenkennzahl.charAt(0)) * 7
			  + map.get(behoerdenkennzahl.charAt(1)) * 3
			  + map.get(behoerdenkennzahl.charAt(2)) * 1
			  + map.get(behoerdenkennzahl.charAt(3)) * 7
			  + map.get(laufendeNummer.charAt(0)) * 3
			  + map.get(laufendeNummer.charAt(1)) * 1
			  + map.get(laufendeNummer.charAt(2)) * 7
			  + map.get(laufendeNummer.charAt(3)) * 3
			  + map.get(laufendeNummer.charAt(4)) * 1
			  + getPruefzifferSeriennummer() * 7
			  + getNthDigit(geburtsdatum.getYear(), 1) * 3
			  + getNthDigit(geburtsdatum.getYear(), 0) * 1
			  + getNthDigit(geburtsdatum.getMonthValue(), 1) * 7
			  + getNthDigit(geburtsdatum.getMonthValue(), 0) * 3
			  + getNthDigit(geburtsdatum.getDayOfMonth(), 1) * 1
			  + getNthDigit(geburtsdatum.getDayOfMonth(), 0) * 7
			  + getPruefzifferDatum(geburtsdatum) * 3
			  + getNthDigit(ablaufdatum.getYear(), 1) * 1
			  + getNthDigit(ablaufdatum.getYear(), 0) * 7
			  + getNthDigit(ablaufdatum.getMonthValue(), 1) * 3
			  + getNthDigit(ablaufdatum.getMonthValue(), 0) * 1
			  + getNthDigit(ablaufdatum.getDayOfMonth(), 1) * 7
			  + getNthDigit(ablaufdatum.getDayOfMonth(), 0) * 3
	    	  + getPruefzifferDatum(ablaufdatum) * 1
		//@formatter:off
		) % 10;
	}
	
	public static String getZufallLaufendeNummer() {
		String nummer = new String();
		for (int i = 0; i < 5; i++) {
			nummer += okay[getRandomInt(okay.length)];
		}
		return nummer;
	}
	
	private static int getRandomInt(int max) {
		return (int) (Math.random() * max);		
	}
	
	public static boolean isLaufendeNummerKorrekt(String laufendeNummer) {
		if (laufendeNummer.length() != 5) {
			return false;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < forbidden.length; j++) {
				if (laufendeNummer.charAt(i) == forbidden[j]) {
					return false;
				}
			}
		}
		return true;
	}

}
