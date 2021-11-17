import java.util.HashMap;
import java.util.Map;

public class OrdbogDansk implements IOrdbog
{
    private static Map<String, String> fraser = new HashMap<>();

    @Override
    public String hentFrase(String nøgle)
    {
        return fraser.get(nøgle);
    }

    public OrdbogDansk()
    {
        fraser = Tools.indlæsOrdbog("fraserPåDansk.csv");
    }
}
