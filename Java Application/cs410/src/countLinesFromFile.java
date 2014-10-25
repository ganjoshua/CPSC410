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

    public void ResourceFile(String res){
        this.fileName = res;
    }


    public void main() throws IOException {

       String fName = fileName;
       numLines = line_counter(fName);

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
