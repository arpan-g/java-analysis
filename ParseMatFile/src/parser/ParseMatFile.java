package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.pirEntry;

public class ParseMatFile {
	private static final int PACKET_NUMBER = 6;
	private static final int MAC_ID = 2;
	
public List<pirEntry> parse(){
	List<pirEntry> readingsList = new ArrayList<pirEntry>();
	String path ="C:/arpan/Thesis/occupancySensor/data/week7-2016-01-25/week7/data_02_02/file.txt";
	BufferedReader br = null;
	try {
		br = new BufferedReader(new FileReader(path));
	
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();
	    while (line != null) {
	    	pirEntry pir= new pirEntry(); 
	    	String[] split = line.split("   ");
	    	split[0].trim();
	    	
	    	int macID = getIntegerValue(split,MAC_ID);;
	    	int packetNumber = getIntegerValue(split,PACKET_NUMBER);
	    	int startTime = generateTime(split,3,4,5,14);
	    	int endTime=generateTime(split,11,12,13,14);
	    	char[] pirData = decimal2BinaryString(255,255,255,255);
	    	int[] timeArray = generateTimeArray(startTime, endTime);
			pir.setMacID(macID);
	    	pir.setPacketNumber(packetNumber);
			pir.setStartTime(startTime);
			pir.setEndTime(endTime);
			pir.setPirData(pirData);
			pir.setTimeArray(timeArray);
	    	readingsList.add(pir);
	    	
//	    	readingsList.add(line);
	        line = br.readLine();
	        
	    }
	    
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	    try {
			br.close();
			System.out.println("done");
			return readingsList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return readingsList;

}
private static int generateTime(String[] split, int i, int j, int k, int l) {
	
	
	
	return getIntegerValue(split,i)+getIntegerValue(split,j)*256+getIntegerValue(split,k)*65536+
			getIntegerValue(split,l)*16777216;
}

private static int getIntegerValue(String[] split, int pos) {
	// TODO Auto-generated method stub
	
	return (int) Double.parseDouble(split[pos].trim());
	
}
private static char[] decimal2BinaryString(int i, int j, int k, int l) {
	// TODO Auto-generated method stub
	Integer dec = (i<<24|j<<16|k<<8|l);
	
	return Integer.toBinaryString(dec).toCharArray();
	
}
@SuppressWarnings("null")
private static int[] generateTimeArray(int startTime, int endTime){
	
	int[] timeArray = null;
	int diff = (endTime-startTime)/32;
	for (int j = 0; j < 32; j++) {
		if(j==0)
		timeArray[j]=startTime;
		else if(j==31)
			timeArray[j]=endTime;
		else
			timeArray[j]=startTime+diff;
	}
	return timeArray;
	

}
}

