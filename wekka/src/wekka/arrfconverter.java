package wekka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
import java.io.File;
 
/**
 *
 * @author vivek
 */
public class arrfconverter {
    public static void Convert(String sourcepath,String destpath) throws Exception
 {
 // load CSV
 CSVLoader loader = new CSVLoader();
 loader.setSource(new File(sourcepath));
 Instances data = loader.getDataSet();
 
 // save ARFF
 ArffSaver saver = new ArffSaver();
 saver.setInstances(data);
 saver.setFile(new File(destpath));
 saver.setDestination(new File(destpath));
 saver.writeBatch();
 }
    public static void main(String args[]) throws Exception
 {
 Convert("C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\dataset.csv", "C:\\Users\\vivek\\OneDrive\\Documents\\NetBeansProjects\\wekka\\src\\wekka\\dataset.arff");
 
 }
}
