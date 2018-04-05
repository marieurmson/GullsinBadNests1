package datalayer;

import java.io.*;
import java.nio.file.Files;

class AppendObjectOutputStream extends ObjectOutputStream {

    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected void writeStreamHeader() throws IOException {

        reset();
    }

}