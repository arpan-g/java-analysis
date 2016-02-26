package parseMatFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.pirEntry;
import parser.ParseMatFile;

public class EntryPoint {


	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
	ParseMatFile parse = new ParseMatFile();
	List<pirEntry> pirList = parse.parse();
	HashMap<Integer, List<pirEntry>> pirMacMap = new HashMap<>();
	int[] prevPacketArray = {0,0,0,0,0,0,0,0};
	int[] prevStartTime = null;
	int[] prevEndTime = null;
	for (pirEntry pirEntry : pirList) {
		int macID = pirEntry.getMacID();
		int packetNumber = pirEntry.getPacketNumber();
		int startTime = pirEntry.getStartTime();
		int endTime = pirEntry.getEndTime();
		if(pirMacMap.containsKey(macID)){
			int packetsLost = packetNumber-prevPacketArray[macID];
//			currentPacket=0;
			while(packetsLost>1){
				
//			pirMacMap
			}
			pirMacMap.get(macID).add(pirEntry);
			}else{
				List<pirEntry>pirLists = new ArrayList<pirEntry>();
				pirLists.add(pirEntry);
				pirMacMap.put(macID, pirLists);
			}
		prevPacketArray[macID-1]=packetNumber; 

		prevStartTime[macID-1]=startTime;
		prevEndTime[macID-1]= endTime;
	}
	
		 
		
	}



	


}
