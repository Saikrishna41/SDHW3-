package main.java.SDHW3rd;


import org.apache.commons.cli.*;

public class Main implements Comparable {


    public static boolean binSearch(Comparable[] aList, Comparable key) {


        int low = 0;
        int high = aList.length - 1;
        int arr_size = aList.length;
        int s;
        int o[] = new int[100];
        int res = -1;
        int l;
        double temp = 0.0;
        for (int i = 0; i < arr_size; i++) {

            o[i] = i;

        }
        for (int i = 0; i < arr_size - 1; i++)
            for (int j = 0; j < arr_size - i - 1; j++)
                if (aList[j].compareTo(aList[j + 1]) < 0) {
                    // swapping the vals of temp and arr[i]
                    temp = Double.parseDouble(aList[j].toString());
                    aList[j] = aList[j + 1];
                    aList[j + 1] = temp;
                    l = o[j];
                    o[j] = o[j + 1];
                    o[j + 1] = l;

                }

        while (low <= high) {
            int middle = (high + low) / 2;


            int checkVal = aList[middle].compareTo(key);

            if (checkVal == 0) {

                res = middle;

                high = middle - 1;


            } else if (checkVal == -1) {

                low = middle + 1;

            } else if (checkVal == 1) {

                high = middle - 1;
            }

        }


        if (res >= 0) {
            System.out.println("output:" + o[res]);
        } else {
            System.out.println("The key not found!");

        }
        return false;
    }

    public int compareTo(Object o) {
        return 0;
    }

    public static void main(String[] args) {

        double aList[];

        double k = 0;
        double keyIndex = 0;

        Options opt = new Options();
        Option typeOp = Option.builder("t").longOpt("type").desc("specifying the types of input:“i” for integer and “s” for string").hasArg().numberOfArgs(1).required(true).build();
        opt.addOption(typeOp);
        Option keyOp = Option.builder("k").longOpt("key").desc("specifying the value of given key for searching").hasArg().numberOfArgs(1).required(true).build();
        opt.addOption(keyOp);
        Option listOp = Option.builder("l").longOpt("list").hasArg().numberOfArgs(Option.UNLIMITED_VALUES).desc("specifies the sorted list of integers or strings").valueSeparator(' ').required(true).build();
        opt.addOption(listOp);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(opt, args, true);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Main ml = new Main();

        boolean isFound = false;
        String type = cmd.getOptionValue("type");
        String key = cmd.getOptionValue("key");
        String[] list = cmd.getOptionValues("list");

        if (type.equals("i")) {
            int keyAsInt = Integer.parseInt(key);
            Integer[] listAsInt = new Integer[list.length];
            for (int i = 0; i < listAsInt.length; i++)
                listAsInt[i] = Integer.parseInt(list[i]);
            isFound = ml.binSearch(listAsInt, keyAsInt);
        } else if (type.equals("s")) {
            isFound = ml.binSearch(list, key);
        } else
            System.out.println("\nInvalid arguments.");

        if (isFound == true)
            System.out.println("1");
        else
            System.out.println("0");
    }

}


