/**
 * Created by mrk on 5/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SettingsConfig config = new SettingsConfig(args);
        Server server = new Server(config.getPort(), config.getDirectory());
        server.run();
    }
}
