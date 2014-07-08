package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;


/**
 * This is a custom object input stream that ignores the serialVersionUID's of 
 * classes implementing Serializable. This is so that we can read for example,
 * a JPanel in 2 different versions of Java 7.
 * 
 * @see <a href='http://stackoverflow.com/a/1816711/3435718'>StackOverflow</a>
 */
public class CustomInputStream extends ObjectInputStream {
  
    public CustomInputStream(InputStream in) throws IOException {
        super(in);
    }
 
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor(); // initially streams descriptor
        Class localClass; // the class in the local JVM that this descriptor represents.
        try {
            localClass = Class.forName(resultClassDescriptor.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("No local class for " + resultClassDescriptor.getName());
            e.printStackTrace();
            return resultClassDescriptor;
        }
        ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup(localClass);
        if (localClassDescriptor != null) { // only if class implements serializable
            final long localSUID = localClassDescriptor.getSerialVersionUID();
            final long streamSUID = resultClassDescriptor.getSerialVersionUID();
            if (streamSUID != localSUID) { // check for serialVersionUID mismatch.
                final StringBuffer s = new StringBuffer("Overriding serialized class version mismatch: ");
                s.append("local serialVersionUID = ").append(localSUID);
                s.append(" stream serialVersionUID = ").append(streamSUID);
                Exception e = new InvalidClassException(s.toString());
                System.err.println("Potentially Fatal Deserialization Operation.");
                //e.printStackTrace();
                resultClassDescriptor = localClassDescriptor; // Use local class descriptor for deserialization
            }
        }
        return resultClassDescriptor;
    }
}