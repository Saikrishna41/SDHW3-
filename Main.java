import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.Arrays;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;


public class Main implements Comparable{

	CommandLine cmd = null;
	String type;
	int middle,iKey;
	Integer[] iList;
	String aList[],key;
	boolean result;
	public Main(String[] args) {
			if(validateOptions(args)) {
				   validate();
			}
		}

	public int compareTo(Object o) {
		return 0;
	}	
	
	
	private boolean binSearch(Comparable[] aList, Comparable key) {
		int low=0;
		int high=aList.length-1;
		
		while(low<=high) {
			
			int middle=(low+high)/2;
			
			if(aList[middle].equals(key)) {
				output=true;
			}
			if(aList[middle].compareTo(key)<0) {
				low=middle+1;
			}
			else {
			high=middle-1;
			}
		}
		
		return result;
	}
	
	
		

	private boolean validateOptions(String[] args) {
        
		Options options = new Options();
		options.addOption(Option.builder("type").desc("Specifying type of elements").longOpt("type").numberOfArgs(1).build());
		options.addOption(Option.builder("key").desc("Specifying key to search").longOpt("key").numberOfArgs(1).build());
		options.addOption(Option.builder("list").desc("Specifying the list of elements").longOpt("list").numberOfArgs(Option.UNLIMITED_VALUES).build());
		CommandLineParser parser = new DefaultParser();
		try {
			cmd=parser.parse(options,args);
		}
		catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        
		type=cmd.getOptionValue("type");
		key=cmd.getOptionValue("key");
		aList =cmd.getOptionValues("list");
		
		return true;
	}
	
	//Method to validate
	private void validate() {
	

		if(type.equals("i"))
		    {
			try {
		        iKey = Integer.parseInt(key);
		        iList = new Integer[aList.length];
		        for (int i = 0; i < aList.length; i++)
		            iList[i] = Integer.parseInt(aList[i]);
		        }
				catch(Exception e) {System.out.println("Please enter integers to sort");}
			
				Arrays.sort(iList);
		        result = binSearch(iList,iKey);
		        printOutput();
		    }
		    else if(type.equals("s"))
		    {
		    	Arrays.sort(aList);
		        result = binSearch(aList,key);
		        printOutput();
		    }
		    else {System.out.println("Please enter a valid argument");}
		}
			

	private void printOutput() {
		System.out.print("Output:");
		if(result) {
			System.out.println("1");	
		}
		else {
			System.out.println("0");
		}
	}

	public static void main(String[] args) {
		
		new Main(args);
	}

	
}
