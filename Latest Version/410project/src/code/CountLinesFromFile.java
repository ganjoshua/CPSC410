/* Code for the line_counter() taken from here:
 * http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
 */
package code;
import info.Files;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class CountLinesFromFile {
    public int numLines;
    public String fileName;
	public Files files[] = new Files[1000];
	public int fileCount = 0;

    public void ResourceFile(String res){
        this.fileName = res;
    }


    public int main() throws IOException {

       String fName = fileName;
       numLines = line_counter(fName);
       System.out.println(Integer.toString(numLines));
       return numLines;
	   //files[fileCount].name = fileName;
	   //files[fileCount].numLines = numLines;
	   //fileCount++;
	   
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
