import java.io.*;
import java.lang.*;
import java.lang.String;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class countLinesFromFile {
    public int numLines;
    public String fileName;
	public Files files[] = new Files[1000];
	public int fileCount = 0;

    public void ResourceFile(String res){
        this.fileName = res;
    }


    public void main() throws IOException {

       String fName = fileName;
       numLines = line_counter(fName);
	   files[fileCount].name = fileName;
	   files[fileCount].numLines = numLines;
	   fileCount++;
	   
    }

    public static int line_counter(String filename) throws IOException {
        InputStream is;
        is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if(endsWithoutNewLine) {
                ++count;
            }
            return count;
        } finally {
            is.close();
        }
    }



}
