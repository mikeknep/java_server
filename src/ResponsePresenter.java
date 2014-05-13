/**
 * Created by mrk on 5/13/14.
 */
public class ResponsePresenter {
    public static String present(Response response) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateStatusLine(response));
        builder.append("\n");
        builder.append("\n");
        builder.append(response.getBody());

        return builder.toString();
    }


    public static String generateStatusLine(Response response) {
        return (response.getVersion() + " " + response.getStatus());
    }
}
