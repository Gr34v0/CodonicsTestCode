import java.lang.*;
import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        try {
            genRandFile();
        } catch(java.io.IOException e) {
            System.err.println("Wut");
        }

        char[] charVar = new char[0];

        try {
            charVar = readFile("RandFile.txt", charVar);
        } catch (java.io.IOException e) {
            System.err.println("IOException caught - File Name invalid");
            return;
        }


        try {
            prune(500, charVar);
        } catch(java.io.IOException e) {
            System.err.println("This shouldn't even be happening right now like what even");
        }
        return;
    }

    private static char[] readFile(String fileName, char[] charVar) throws IOException {

        File fileVar = new File(fileName);

        fileVar.createNewFile();

        FileReader readerVar = new FileReader(fileVar);

        charVar = new char[(int)fileVar.length()];

        readerVar.read(charVar);

        readerVar.close();

        return charVar;
    }

    private static void prune( int wav_len, char[] origCharVar) throws IOException {

        File finalFile = new File("prunedFile.txt");

        try {
            finalFile.createNewFile();
        } catch (java.io.IOException e) {
            System.err.println("wut again");
        }

        FileWriter writerVar = new FileWriter(finalFile);

        int divisor = 100;
        int divNum = (int)Math.ceil(wav_len/divisor);
        char[] newCharVar = new char[divisor];

        int i = 0;
        int counter = 0;

        while(i < wav_len){

            newCharVar[counter] = origCharVar[i];

            writerVar.append(newCharVar[counter]);
            i = i + divNum;
            counter = counter + 1;
        }
        writerVar.close();
    }

    public static void genRandFile() throws IOException{ //In an ideal world, this would be a Sine Wave. But alas...
        int MAX_INT = 100;
        int MIN_INT = 0;

        int arbitraryRandNumbLen = 500;

        File genFile = new File("RandFile.txt");

        try {
            genFile.createNewFile();
        } catch(java.io.IOException e) {
            System.err.println("This should never happen?");
        }

        FileWriter writerVar = new FileWriter(genFile);

        for(int i = 0; i < arbitraryRandNumbLen; i++){
            Random rand = new Random();
            int randNum = rand.nextInt(MAX_INT - MIN_INT + 1) + MIN_INT;
            writerVar.append(randNum + " ");
        }

        writerVar.close();
    }
}