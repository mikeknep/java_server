import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mrk on 5/19/14.
 */
public interface StreamPair {
    public InputStream getIn() throws Exception;
    public OutputStream getOut() throws Exception;
}
