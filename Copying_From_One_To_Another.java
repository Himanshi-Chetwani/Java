/*
 * Name:
 *  CopyAll.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
/**
 * This class copies contents from one directory to another sequentially and
 * in parallel
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */

import java.io.*;

public class CopyAll extends Thread{
    static String source;
    static String destination;
    static final int BUF_SIZE=1024;
    int indexm;

    /**
     * Default constructor
     */
    public CopyAll(){

    }

    /**
     * Thread number being assigned
     * @param indexm
     */
    public CopyAll(int indexm){
        this.indexm=indexm;
    }

    /**
     * Copies contents of the file
     * @param fileNames
     * @param index1
     */
    public void ThreadFunc(String[] fileNames,int index1){
        String modified_source;
        String modified_destination;
        byte[]  buffer = new byte[BUF_SIZE];
        int n;
        try{
            modified_source=source+"/"+fileNames[index1];
            DataInputStream in =
                    new DataInputStream(new FileInputStream(modified_source));
            modified_destination=destination+"/"+fileNames[index1];
            File file = new File(modified_destination);
            if(!file.exists()){
                file.createNewFile();
            }
            DataOutputStream out =
                    new DataOutputStream(
                            new FileOutputStream(modified_destination));

            while ((n=in.read(buffer))!=-1){

                out.write(buffer,0,n);
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Created directoried
     * @return Nmber of files in the directory
     */
    public  int copy(){
        int count = 0;
        File newDir = new File(destination);
        if(newDir.exists()){
            destination=destination+"_copy";
        }
        newDir = new File(destination);
        if (!newDir.exists()) {
            System.out.println("Creatig a new directory : " + newDir.getName());
            newDir.mkdir();
        }
        File originalDir = new File(source);
        if (!originalDir.exists()) {
            System.out.println("Directory doesn't exists");
        }
        File[] files = originalDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    count++;
                }

            }
        }
        System.out.println("Number of files to be copied : " + count);
        return count;
    }

    /**
     * Runs the copying of files parallely
     */
    public void run(){
        ThreadFunc(new File(source).list(),
                indexm);
    }
    /*
    Copy files from one directpry to another and compare them both parally
    and sequentially
     */
    public static void main(String[] args) {
        if(args.length!=2){
            System.err.println("Insufficient arguments");
            System.exit(1);
        }
        source=args[0];
        destination=args[1];
        int noOfFiles;
        long startTime,endTime,diffTimeS,diffTimeM;
        CopyAll objCopy=new CopyAll();
        System.out.println("Single Thread");
        startTime = System.currentTimeMillis();
        noOfFiles=objCopy.copy();
        for (int indexs = 0; indexs < noOfFiles; indexs++) {
            objCopy.ThreadFunc(new File(source).list(),
                    indexs);
        }
        endTime = System.currentTimeMillis();
        diffTimeS=endTime-startTime;
        System.out.println("Time taken for single thread "+diffTimeS);
        System.out.println("Multi Thread");
        startTime = System.currentTimeMillis();
        noOfFiles=objCopy.copy();
        for ( int indexm = 0; indexm < noOfFiles; indexm++) {
            new CopyAll(indexm).start();
        }
        endTime = System.currentTimeMillis();
        diffTimeM=endTime-startTime;
        System.out.println("Time taken for multi thread "+diffTimeM);
        System.out.println("Multi thread happened "+(diffTimeS-diffTimeM)+
                "ms faster");
    }
}

