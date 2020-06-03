/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekka;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;
import java.util.*;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
/**
 *
 * @author vivek
 */
public class Wekka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\current.arff"));
        
        Instances train = new Instances(br);
        train.setClassIndex(train.numAttributes()-1);
        br.close();
        NaiveBayes nb =new NaiveBayes();
        nb.buildClassifier(train);
        Evaluation ev = new Evaluation(train);
        
        ev.crossValidateModel(nb, train, 10, new Random(1));
        System.out.println(ev.toSummaryString("result", true));
        try{
            FileWriter pw = new FileWriter("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\dataset.arff");
            pw.append(train+"\ndog,yes,no,no,no,no,yes,yes,yes,no,no,yes,'canine distemper'" +
"\n");
//            pw.flush();
            pw.close();
        }catch(Exception e){
            System.err.println(e);
        }
        BufferedReader b = null;
        b = new BufferedReader(new FileReader("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\dataset.arff"));
        Instances tr = new Instances(b);
        tr.setClassIndex(train.numAttributes()-1);
NaiveBayes nbt =new NaiveBayes();
        nbt.buildClassifier(tr);
        Evaluation evt = new Evaluation(tr);
        
        evt.crossValidateModel(nbt, train, 10, new Random(1));
        double predict = nbt.classifyInstance(tr.lastInstance());
        String disease = "";
        if(predict == 0.0){
            disease = "'canine distemper'";
        }else if(predict == 1.0){
            
        }
        /*
    File file = new File("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\current.csv"); 
    try { 
        // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 
  
        // create CSVWriter object filewriter object as parameter 
        CSVWriter writer = new CSVWriter(outputfile); 
  
        // adding header to csv 
        String[] header = { "Type of animal ","fever","rashes","low energy","weight loss","dehydration","paralysis","vomitnigs","coughing","pain","signs of yellow fever","runny eyes","Disease" }; 
        writer.writeNext(header); 
  
        // add data to csv 
        String[] data1 = { "dog","yes","no","no","no","no","yes","yes","yes","no","no","yes","canine distemper" }; 
        writer.writeNext(data1); 
  
        // closing writer connection 
        writer.close(); 
         CSVLoader loader = new CSVLoader();
 loader.setSource(new File("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\current.csv"));
 Instances data = loader.getDataSet();
 String destpath="C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\current.arff";
 // save ARFF
 ArffSaver saver = new ArffSaver();
 saver.setInstances(data);
 saver.setFile(new File(destpath));
 saver.setDestination(new File(destpath));
 saver.writeBatch();
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    }         
br = new BufferedReader(new FileReader("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\current.arff"));  
Instances tr = new Instances(br);
*/ 


        System.out.println(evt.fMeasure(1)+" "+evt.precision(1)+" "+evt.recall(1)+"\n"+nbt.classifyInstance(tr.lastInstance())+"\n\n"+tr.lastInstance());
//        String[] dff = new String[]{"dog","yes","no","no","no","no","yes","yes","yes","no","no","yes"};
//        Instance ins = new Instance(1, dff);
//        String res = nb.classifyInstance(instance)
    }
}
